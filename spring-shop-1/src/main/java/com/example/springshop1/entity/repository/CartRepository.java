package com.example.springshop1.entity.repository;

import com.example.springshop1.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart, UUID> {
}
