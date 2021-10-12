package ru.itis.kurguskina.service;

import ru.itis.kurguskina.dto.UserDto;
import ru.itis.kurguskina.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(int id);
    void save(User user);
}
