package org.yearup.data;

import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;

import java.util.List;

public interface OrderDao {

    Order createOrder(ShoppingCart cart, int userId, Profile profile);

}
