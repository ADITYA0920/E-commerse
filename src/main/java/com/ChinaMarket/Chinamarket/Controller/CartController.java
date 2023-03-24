package com.ChinaMarket.Chinamarket.Controller;

import com.ChinaMarket.Chinamarket.Repository.CartRepository;
import com.ChinaMarket.Chinamarket.RequestDTO.OrderRequestDto;
import com.ChinaMarket.Chinamarket.ResponseDTO.OrderResponseDto;
import com.ChinaMarket.Chinamarket.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/add")
    public String addToCart(@RequestBody OrderRequestDto orderRequestDto){

        String response = "";
        try{
            response = cartService.addToCart(orderRequestDto);
        } catch (Exception e) {
            return e.getMessage();
        }

        return response;
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkoutCart(@PathVariable("customerId") int customerId){
        List<OrderResponseDto> orderResponseDtos;
        try{
            orderResponseDtos = cartService.checkout(customerId);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDtos,HttpStatus.ACCEPTED);
    }
}
