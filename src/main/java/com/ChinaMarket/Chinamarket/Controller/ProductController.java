package com.ChinaMarket.Chinamarket.Controller;

import com.ChinaMarket.Chinamarket.Enum.ProductCategory;
import com.ChinaMarket.Chinamarket.Exception.SellerNotFoundException;
import com.ChinaMarket.Chinamarket.RequestDTO.ProductRequestDto;
import com.ChinaMarket.Chinamarket.ResponseDTO.ProductResponseDto;
import com.ChinaMarket.Chinamarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        ProductResponseDto productResponseDto;
        try{
            productResponseDto = productService.addProduct(productRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/category/{productCategory}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory){

        return productService.getProductsByCategory(productCategory);
    }
}
