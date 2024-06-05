package org.example.securitystudy.repository;

import org.example.securitystudy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);

}
