package com.timemanage.gigatomson.user.service;

import com.timemanage.gigatomson.user.domain.User;
import com.timemanage.gigatomson.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
     private  final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserByUserId(String userId) {

        User user = userRepository.findByUserId(userId);

        if(user == null){
            throw new IllegalArgumentException("해당 아이디가 없습니다" + userId);
        }
        return user;

    }

}
