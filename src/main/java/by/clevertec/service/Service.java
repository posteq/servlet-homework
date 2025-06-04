package by.clevertec.service;

import by.clevertec.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<UserDto> getAllUsers();
    Optional<UserDto> getById(Integer id);
    void createUser(UserDto userDto);
    void delete(Integer id);

}
