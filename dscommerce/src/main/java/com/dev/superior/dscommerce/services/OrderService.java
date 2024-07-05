package com.dev.superior.dscommerce.services;

import com.dev.superior.dscommerce.dto.OrderDTO;
import com.dev.superior.dscommerce.dto.OrderItemDTO;
import com.dev.superior.dscommerce.dto.UserDTO;
import com.dev.superior.dscommerce.entities.*;
import com.dev.superior.dscommerce.projections.UserDetailsProjection;
import com.dev.superior.dscommerce.repositories.OrderItemRepository;
import com.dev.superior.dscommerce.repositories.OrderRepository;
import com.dev.superior.dscommerce.repositories.ProductRepository;
import com.dev.superior.dscommerce.repositories.UserRepository;
import com.dev.superior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService  {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id){
		Order order = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		authService.validateSelfOrAdmin(order.getClient().getId());
		return new OrderDTO(order);
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto){

		Order order = new Order();

		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);

		User user = userService.authenticated();
		order.setClient(user);

		for(OrderItemDTO itemDTO : dto.getItems()){
			Product product = productRepository.getReferenceById(itemDTO.getProductId());
			OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}

		repository.save(order);
		orderItemRepository.saveAll(order.getItems());

		return new OrderDTO(order);
	}
}
