/**
 * 
 */
package com.pattanacode.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pattanacode.models.Category;

/**
 * @author patismyname
 *
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
