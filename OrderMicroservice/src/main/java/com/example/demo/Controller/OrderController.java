package com.example.demo.Controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import com.example.demo.Service.OrderService;
import com.example.demo.repo.Order;
 
@RestController
@RequestMapping("/order")
public class OrderController {
 
    @Autowired
    private OrderService orderService;
 
    @PostMapping("/place")
    public String placeOrder(@RequestParam Long productId, @RequestParam int quantity) {
        return orderService.placeOrder(productId, quantity);
    }
 
    @DeleteMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }
 
    @GetMapping()
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
 