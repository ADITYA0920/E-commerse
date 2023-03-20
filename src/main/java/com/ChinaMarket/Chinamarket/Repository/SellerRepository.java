package com.ChinaMarket.Chinamarket.Repository;

import com.ChinaMarket.Chinamarket.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
}
