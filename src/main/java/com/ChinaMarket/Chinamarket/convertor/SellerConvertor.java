package com.ChinaMarket.Chinamarket.convertor;

import com.ChinaMarket.Chinamarket.Model.Seller;
import com.ChinaMarket.Chinamarket.RequestDTO.SellerRequestDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;


public class SellerConvertor {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }
}
