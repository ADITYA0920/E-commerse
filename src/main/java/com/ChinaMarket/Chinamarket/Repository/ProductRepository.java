package com.ChinaMarket.Chinamarket.Repository;

import com.ChinaMarket.Chinamarket.Enum.ProductCategory;
import com.ChinaMarket.Chinamarket.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
