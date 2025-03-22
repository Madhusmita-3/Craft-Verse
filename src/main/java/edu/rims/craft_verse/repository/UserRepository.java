package edu.rims.craft_verse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserEmail(String username);

}
