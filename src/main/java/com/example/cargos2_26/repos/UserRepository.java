package com.example.cargos2_26.repos;

import com.example.cargos2_26.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.id > :idMin")
    List<Users> usergetList(Long idMin);
}


