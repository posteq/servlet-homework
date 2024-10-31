package by.clevertec.repository;

import by.clevertec.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UserRepository implements Repository {

    private static final UserRepository INSTANCE = new UserRepository();
    private final Map<Integer,User> users;
    public static int usersCounter = 1;

    private UserRepository(){
        users= new HashMap<>();
        users.put(usersCounter,
                User.builder()
                        .id(usersCounter)
                        .username("Ivan")
                        .email("ivan@gmail.com")
                        .phoneNumber("+88000000000")
                        .build()
        );

        usersCounter++;

        users.put(usersCounter,
                User.builder()
                        .id(usersCounter)
                        .username("Petr")
                        .email("petr@gmail.com")
                        .phoneNumber("+88000000001")
                        .build()
        );
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void save(User user) {
        user.setId(++usersCounter);
        users.put(user.getId(), user);
    }

    @Override
    public void delete(Integer id) {
        users.remove(id);
    }
}
