package com.pattanacode.repository;

import com.pattanacode.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    public List<Product> findProductByProductNameLike(String productName);

	@Override
    void delete(Product deleted);
}
