package com.fundamentosPlatzi.springboot.fundamentos.repository;

import com.fundamentosPlatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users u where u.email=:email", nativeQuery = true)
    Optional<User> findByUserEmail(@Param("email")String email);

    @Query(value = "select * from users u where u.name like :name% order by u.id_user desc", nativeQuery = true)
    Optional<User> findAndSort(@Param("name") String name, Sort sort);

    List<User> findByName(String name);
}
