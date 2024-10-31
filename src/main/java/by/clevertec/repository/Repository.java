package by.clevertec.repository;

import by.clevertec.entity.User;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<User> findAll();
    Optional<User> findById(Integer id);
    void save(User user);
    void delete(Integer id);
}
