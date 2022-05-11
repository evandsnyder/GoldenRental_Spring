package com.friendlygeek.goldenrental.services.repository;

import com.friendlygeek.goldenrental.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
