/**
 * 
 */
package com.pattanacode.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pattanacode.models.FileUploadTransaction;

/**
 * @author patismyname
 *
 */
@Repository
public interface FileUploadTransactionRepository extends MongoRepository<FileUploadTransaction, String> {

}
