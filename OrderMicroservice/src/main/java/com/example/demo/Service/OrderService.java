package com.example.demo.Service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 
import com.example.demo.repo.Order;
import com.example.demo.repo.OrderRepository;
 
@Service
public class OrderService {
 
    @Autowired
    private OrderRepository orderRepository;
 
    @Autowired
    private RestTemplate restTemplate;
 
    // Place Order
    public String placeOrder(Long productId, int quantity) {
 
        try {
            // Call Product Microservice via API Gateway
            String productServiceUrl = "http://PRODUCT/Product/check/" + productId + "/" + quantity;
            String response = restTemplate.getForObject(productServiceUrl, String.class);
 
            // Save Order
            Order order = new Order();
            order.setProductId(productId);
            order.setQuantity(quantity);
            order.setStatus(response);
            orderRepository.save(order);
 
            return response;
 
        } catch (Exception e) {
            return "Error placing order: " + e.getMessage();
        }
    }
 
    // Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
 
    // Cancel Order
    public String cancelOrder(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
 
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
 
            // Restore quantity in Product Microservice
            try {
                String productServiceUrl = "http://PRODUCT/Product/restore/" + order.getProductId() + "/" + order.getQuantity();
                restTemplate.getForObject(productServiceUrl, String.class);
            } catch (Exception e) {
                return "Error restoring product quantity: " + e.getMessage();
            }
 
            orderRepository.deleteById(orderId);
            return "Order cancelled and quantity restored.";
        } else {
            return "Order not found.";
        }
    }
}
 