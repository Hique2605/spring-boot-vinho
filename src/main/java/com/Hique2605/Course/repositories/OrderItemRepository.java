package com.Hique2605.Course.repositories;

import com.Hique2605.Course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Hique2605.Course.entities.OrderItem;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

