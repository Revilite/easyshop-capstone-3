package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.OrderDao;
import org.yearup.models.*;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MysqlOrderDao extends MySqlDaoBase implements OrderDao {

    @Autowired
    public MysqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Order createOrder(ShoppingCart cart, int userId, Profile profile) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO orders(user_id, date, address, city, state, zip)
                    VALUES(?, ?, ? ,?, ?, ?);
                    """, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setString(2, now.format(dtf));
            statement.setString(3, profile.getAddress());
            statement.setString(4, profile.getCity());
            statement.setString(5, profile.getState());
            statement.setString(6, profile.getZip());
            //Inserts order into order table
            statement.executeUpdate();
            //Gets the generated key
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);

            //Creates the order in memory
            Order order = new Order(orderId, userId);
            //Calls the orderLineItem helper method
            List<OrderLineItem> orderItems = createOrderLineItem(cart, order);


            //Returns order items to postman
            return new Order(orderId, userId, new BigDecimal(0), now, profile.getAddress(), profile.getCity(), profile.getState(), createOrderLineItem(cart, order));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderLineItem> createOrderLineItem(ShoppingCart cart, Order order) {

        List<OrderLineItem> items = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO order_line_items(order_id, product_id, sales_price, quantity, discount)
                    VALUES(?, ?, ?, ?, ?);
                    """, PreparedStatement.RETURN_GENERATED_KEYS);

            //For loop for each item in the cart

            for (ShoppingCartItem item : cart.getItems().values()) {
                BigDecimal discountedPrice = item.getProduct().getPrice().multiply((item.getDiscountPercent().add(new BigDecimal(1))));


                statement.setInt(1, order.getOrderId());
                statement.setInt(2, item.getProductId());
                statement.setBigDecimal(3, discountedPrice);
                statement.setInt(4, item.getQuantity());
                statement.setBigDecimal(5, item.getDiscountPercent());

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();

                rs.next();

                int orderLineItemId = rs.getInt(1);

                items.add(new OrderLineItem(orderLineItemId, order.getOrderId(), item.getProductId(), discountedPrice, item.getQuantity(), item.getDiscountPercent()));
            }

            return items;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
