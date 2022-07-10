package com.pattanacode.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pattanacode.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
