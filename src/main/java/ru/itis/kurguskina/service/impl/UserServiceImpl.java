package ru.itis.kurguskina.service.impl;
import ru.itis.kurguskina.dao.Dao;
import ru.itis.kurguskina.dao.impl.UserDaoImpl;
import ru.itis.kurguskina.dto.UserDto;
import ru.itis.kurguskina.helper.PasswordHelper;
import ru.itis.kurguskina.model.User;
import ru.itis.kurguskina.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final Dao<User> dao = new UserDaoImpl();

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = dao.getAll();
        return users.stream()
                .map(u -> new UserDto(u.getName(), u.getLogin()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        dao.save(new User(
                user.getName(),
                user.getLogin(),
                PasswordHelper.encrypt(user.getPassword())
        ));
    }
}
