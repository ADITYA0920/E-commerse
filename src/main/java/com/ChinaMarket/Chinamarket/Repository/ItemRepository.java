package com.ChinaMarket.Chinamarket.Repository;

import com.ChinaMarket.Chinamarket.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
