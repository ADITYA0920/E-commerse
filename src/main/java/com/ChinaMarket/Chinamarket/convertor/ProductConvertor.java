package com.ChinaMarket.Chinamarket.convertor;

import com.ChinaMarket.Chinamarket.Enum.ProductStatus;
import com.ChinaMarket.Chinamarket.Model.Product;
import com.ChinaMarket.Chinamarket.RequestDTO.ProductRequestDto;
import com.ChinaMarket.Chinamarket.ResponseDTO.ProductResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConvertor {

    public static Product productRequestDtotoProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
