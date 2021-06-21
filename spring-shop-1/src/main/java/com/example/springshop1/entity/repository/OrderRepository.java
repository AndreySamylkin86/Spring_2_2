package com.example.springshop1.entity.repository;

import com.example.springshop1.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository  extends CrudRepository<Order, UUID> {
}
