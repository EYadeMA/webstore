package webshop.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.order.dto.OrderConfirmDTO;
import webshop.order.dto.OrderPlaceDTO;
import webshop.order.entity.Order;
import webshop.order.error.CustomErrorType;
import webshop.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody OrderPlaceDTO requestDTO) {
        Order order=new Order();
        try{order=orderService.createOrder(requestDTO);
        }catch (Exception ex){
            return ResponseEntity.ok(getCustomErrorType("Sorry, order cannot be placed"));
        }
        return ResponseEntity.ok(order);
    }



    @PostMapping("/confirm")
    public ResponseEntity<?> confirmOrder(@RequestBody OrderConfirmDTO requestDTO) {
        orderService.confirmOrder(requestDTO);
        return ResponseEntity.ok("Dear Customer.\n Your order has been confirmed.\n \n Thank you!");
    }

    public CustomErrorType getCustomErrorType(String message){
        return new CustomErrorType(message);
    }
}
