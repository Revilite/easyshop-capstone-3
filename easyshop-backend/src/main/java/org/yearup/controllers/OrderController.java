package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.OrderDao;
import org.yearup.data.ProfileDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Order;


import java.security.Principal;


@CrossOrigin
@RestController
@RequestMapping("/orders")
@PreAuthorize("hasRole('USER')")
public class OrderController {
    ShoppingCartDao cartDB;
    OrderDao ordersDB;
    UserDao usersDB;
    ProfileDao profileDB;

    // Needs the cart for item info, user for user login and profile for user demographics
    @Autowired
    public OrderController(ShoppingCartDao cartDB, OrderDao ordersDB, UserDao usersDB, ProfileDao profileDB) {
        this.cartDB = cartDB;
        this.ordersDB = ordersDB;
        this.usersDB = usersDB;
        this.profileDB = profileDB;
    }

    //Needs an order data type with the list of lineItems
    @PostMapping
    public Order checkout(Principal principal) {
        String username = principal.getName();
        int userId = usersDB.getIdByUsername(username);

        Order order = ordersDB.createOrder(cartDB.getByUserId(userId), userId, profileDB.getUserById(userId));

        //Clears the cart after checkout
        cartDB.clearCart(userId);

        return order;
    }

}
