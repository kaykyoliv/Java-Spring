package com.dev.superior.dscommerce.dto;

import com.dev.superior.dscommerce.entities.Order;
import com.dev.superior.dscommerce.entities.OrderItem;
import com.dev.superior.dscommerce.entities.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDTO clientDTO;

    private PaymentDTO paymentDTO;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO clientDTO, PaymentDTO paymentDTO) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.clientDTO = clientDTO;
        this.paymentDTO = paymentDTO;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        clientDTO = new ClientDTO(entity.getClient());
        paymentDTO = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for(OrderItem item : entity.getItems()){
            OrderItemDTO itemsDto = new OrderItemDTO(item);
            items.add(itemsDto);
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotal(){
        double sum = 0.0;
        for(OrderItemDTO item : items){
            sum += item.getSubTotal();
        }
        return sum;
    }
}
