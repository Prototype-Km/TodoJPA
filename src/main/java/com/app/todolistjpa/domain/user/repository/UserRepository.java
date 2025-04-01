package com.app.todolistjpa.domain.user.repository;

import com.app.todolistjpa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
