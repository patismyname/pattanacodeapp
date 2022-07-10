package com.pattanacode.repository;

import com.pattanacode.models.TrackingPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingPriceRepository extends MongoRepository <TrackingPrice, String> {

@Query("{'Product.id':?0}")
public List<TrackingPrice> findByProductId(String id);


}
