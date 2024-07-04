package com.dev.superior.dscommerce.services;

import com.dev.superior.dscommerce.dto.OrderDTO;
import com.dev.superior.dscommerce.dto.UserDTO;
import com.dev.superior.dscommerce.entities.Order;
import com.dev.superior.dscommerce.entities.Role;
import com.dev.superior.dscommerce.entities.User;
import com.dev.superior.dscommerce.projections.UserDetailsProjection;
import com.dev.superior.dscommerce.repositories.OrderRepository;
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

import java.util.List;

@Service
public class OrderService  {

	@Autowired
	private OrderRepository repository;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id){
		Order order = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}

}
