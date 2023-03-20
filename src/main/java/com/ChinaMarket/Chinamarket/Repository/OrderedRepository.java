package com.ChinaMarket.Chinamarket.Repository;

import com.ChinaMarket.Chinamarket.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
