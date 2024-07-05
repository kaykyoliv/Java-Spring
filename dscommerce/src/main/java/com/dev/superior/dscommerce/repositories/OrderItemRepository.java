package com.dev.superior.dscommerce.repositories;

import com.dev.superior.dscommerce.entities.Order;
import com.dev.superior.dscommerce.entities.OrderItem;
import com.dev.superior.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
