package com.ChinaMarket.Chinamarket.Service;

import com.ChinaMarket.Chinamarket.Model.Seller;
import com.ChinaMarket.Chinamarket.Repository.SellerRepository;
import com.ChinaMarket.Chinamarket.RequestDTO.SellerRequestDto;
import com.ChinaMarket.Chinamarket.convertor.SellerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepo;

    public String addSeller(SellerRequestDto sellerRequestDto){

        Seller seller = SellerConvertor.SellerRequestDtoToSeller(sellerRequestDto);
        sellerRepo.save(seller);

        return "Congrats! Now you can sell on Chine Market !!!";
    }
}
