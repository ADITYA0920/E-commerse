package com.ChinaMarket.Chinamarket.Service;

import com.ChinaMarket.Chinamarket.Model.Cart;
import com.ChinaMarket.Chinamarket.Model.Customer;
import com.ChinaMarket.Chinamarket.Repository.CustomerRepository;
import com.ChinaMarket.Chinamarket.RequestDTO.CustomerRequestDto;
import com.ChinaMarket.Chinamarket.convertor.CustomerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto){

        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        // allocate a cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);

        // if we have oneTo one relation then we should set from both end
        // set cart in customer
        cart.setCustomer(customer);
        customer.setCart(cart);

        customerRepository.save(customer);
        return "Congrats !! Welcome to China Market.";
    }
}
