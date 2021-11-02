//package ru.itis.kurguskina.service.Impl;
//
//import ru.itis.kurguskina.dao.impl.UserDaoJdbcImpl;
//import ru.itis.kurguskina.dto.UserDto;
//import ru.itis.kurguskina.model.User;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class UserServiceImpl {
//    @Override
//    public List<UserDto> getAll() {
//        List<User> users = UserDaoJdbcImpl.getAll();
//
//        return users.stream()
//                .map(user -> new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getBirthday(),
//                        user.getUsername(), user.getPassword()))
//                .collect(Collectors.toList());
//    }
//    @Override
//    public UserDto findUser(String username,String password) {
//        User user = UserDaoJdbcImpl.findUser();
//
//        return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getBirthday(),
//                user.getUsername(), user.getPassword());
//    }
//    @Override
//    public void save(User user) {
//        UserDaoJdbcImpl.addUser(new User(user.getId(), user.getFirstname(), user.getLastname(), user.getBirthday(),
//                user.getUsername(), user.getPassword()));
//    }
//
//    @Override
//    public void delete(int id) {
//        UserDaoJdbcImpl.delete(id);
//    }
//
//}
//
