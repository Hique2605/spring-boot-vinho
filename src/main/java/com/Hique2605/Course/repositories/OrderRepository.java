package com.Hique2605.Course.repositories;

import com.Hique2605.Course.entities.Representante;
import com.Hique2605.Course.entities.User;
import org.aspectj.weaver.ast.Call;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Hique2605.Course.entities.Order;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByRepresentante(Representante representante);
    List<Order> findByClient(User client);
}

