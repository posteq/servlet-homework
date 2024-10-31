package by.clevertec.service;

import by.clevertec.dto.UserDto;
import by.clevertec.mapper.UserMapper;
import by.clevertec.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class UserService implements Service{

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserRepository userRepository = UserRepository.getInstance();

    private static final UserService INSTANCE = new UserService();

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToDto)
                .collect(toList());
    }

    @Override
    public Optional<UserDto> getById(Integer id) {
        return userRepository.findById(id).map(userMapper::userToDto);
    }

    @Override
    public void createUser(UserDto userDto) {
        userRepository.save(userMapper.DtoToUser(userDto));
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
