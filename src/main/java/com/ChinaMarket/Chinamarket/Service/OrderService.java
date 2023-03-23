package com.ChinaMarket.Chinamarket.Service;

import com.ChinaMarket.Chinamarket.Enum.ProductStatus;
import com.ChinaMarket.Chinamarket.Exception.CustomerNotFoundException;
import com.ChinaMarket.Chinamarket.Exception.ProductNotFoundException;
import com.ChinaMarket.Chinamarket.Model.*;
import com.ChinaMarket.Chinamarket.Repository.CustomerRepository;
import com.ChinaMarket.Chinamarket.Repository.OrderedRepository;
import com.ChinaMarket.Chinamarket.Repository.ProductRepository;
import com.ChinaMarket.Chinamarket.RequestDTO.OrderRequestDto;
import com.ChinaMarket.Chinamarket.ResponseDTO.ItemResponseDto;
import com.ChinaMarket.Chinamarket.ResponseDTO.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderedRepository orderedRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        // Prepare Order
        int totalCost = orderRequestDto.getRequiredQuantity()*product.getPrice();
        int deliveryCharge = 0;
        if(totalCost<500){
            deliveryCharge = 50;
            totalCost += deliveryCharge;
        }
        Ordered order = Ordered.builder()
                .totalCost(totalCost)
                .deliveryCharge(deliveryCharge)
                .build();

        // prepare the Card String;
        Card card = customer.getCards().get(0);
        String cardUsed = "";
        int cardNo = card.getCardNo().length();
        for(int i = 0;i<cardNo-4;i++){
            cardUsed += 'X';
        }
        cardUsed += card.getCardNo().substring(cardNo-4);
        order.setCardUsedForPayment(cardUsed);

        // update customer's current order list
        customer.getOrders().add(order);
        order.setCustomer(customer);
        order.getOrderedItems().add(item);

        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        // update the quantity of product left in warehouse
        int leftQuantity = product.getQuantity()- orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        // update item
        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .build();

        // update item in orde

        item.setOrder(order);
        product.setItem(item);
        item.setProduct(product);

        // save product-item and customer-order
        customerRepository.save(customer);


        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getProductName())
                .orderDate(order.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardUsed)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(deliveryCharge)
                .build();

        return orderResponseDto;
    }
}
