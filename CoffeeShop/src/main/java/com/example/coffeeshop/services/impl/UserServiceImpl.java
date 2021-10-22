package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.models.entityModels.User;
import com.example.coffeeshop.models.serviceModels.LoginServiceModel;
import com.example.coffeeshop.models.serviceModels.RegisterServiceModel;
import com.example.coffeeshop.repositories.UserRepo;
import com.example.coffeeshop.services.UserService;
import com.example.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public LoginServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, LoginServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(LoginServiceModel loginServiceModel) {
        currentUser.setId(loginServiceModel.getId())
                .setUsername(loginServiceModel.getUsername());
    }

    @Override
    public boolean register(RegisterServiceModel registerServiceModel) {
        User user = modelMapper.map(registerServiceModel, User.class);

        try {
            userRepo.save(user);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void logout() {
        currentUser.setUsername(null).setId(null);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
