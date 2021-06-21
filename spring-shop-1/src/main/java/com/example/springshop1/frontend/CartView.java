package com.example.springshop1.frontend;

import com.example.springshop1.entity.Cart;
import com.example.springshop1.entity.Product;
import com.example.springshop1.service.CartService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("cart")
public class CartView extends VerticalLayout {
    private final Grid<Product> grid = new Grid<>(Product.class);

    private final CartService cartService;

    public CartView(CartService cartService) {
        this.cartService = cartService;

        initCartGrid();
        add(grid, initCartButton());
    }
    private HorizontalLayout initCartButton() {
        var createOrder = new Button("Оформить заказ", items -> {
            var listProducts =  cartService.getProducts();
            for (Product product : listProducts) {

            }

            Cart cart = new Cart();
            cart.set


            Notification.show("Заказ успешно оформлен");
        });

        var toCartButton = new Button("Главный экран", item -> {
            UI.getCurrent().navigate("main");
        });

        return new HorizontalLayout(toCartButton, createOrder);
    }

    private void initCartGrid() {
        var products = cartService.getProducts();

        grid.setItems(products);
        grid.setColumns("name", "count");
        grid.setSizeUndefined();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ListDataProvider<Product> dataProvider = DataProvider.ofCollection(products);
        grid.setDataProvider(dataProvider);

        grid.addColumn(new ComponentRenderer<>(item -> {
            var plusButton = new Button("+", i -> {
                cartService.increaseProductCount(item);
                grid.getDataProvider().refreshItem(item);
            });

            var minusButton = new Button("-", i -> {
                cartService.decreaseProductCount(item);
                grid.getDataProvider().refreshItem(item);
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));
    }
}
