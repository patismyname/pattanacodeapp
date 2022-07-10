package com.pattanacode.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pattanacode.models.ERole;
import com.pattanacode.models.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
