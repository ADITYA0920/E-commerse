package com.ChinaMarket.Chinamarket.Controller;

import com.ChinaMarket.Chinamarket.RequestDTO.CardRequestDto;
import com.ChinaMarket.Chinamarket.ResponseDTO.CardResponseDto;
import com.ChinaMarket.Chinamarket.Service.CardService;
import com.ChinaMarket.Chinamarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        CardResponseDto cardResponseDto;
        try{
        cardResponseDto = cardService.addCard(cardRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
    }

    // remove card

    // remove all cards for a particular customer id
}
