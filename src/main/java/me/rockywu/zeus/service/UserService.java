package me.rockywu.zeus.service;

import me.rockywu.zeus.domain.User;
import me.rockywu.zeus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-01-19 23:44
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int createUser(User user){
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userMapper.createUser(user);
    }

    public User getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    public List<String> getAllUserNames(){
        return userMapper.getAllUserNames();
    }

    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }
}
