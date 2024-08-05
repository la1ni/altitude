package com.project.altitude.repositories;

import com.project.altitude.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {
    User findByUsername(String username);

    User findByCpf(String cpf);
}
