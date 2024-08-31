package com.accounts.user.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {
	Boolean existsByUsername(String username);

	Optional<Account> findByUsername(String username);
}
