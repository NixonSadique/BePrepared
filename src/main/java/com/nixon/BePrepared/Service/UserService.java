package com.nixon.BePrepared.Service;

import com.nixon.BePrepared.DTO.Response.StatsResponse;
import com.nixon.BePrepared.Model.User;

import java.util.List;

public interface UserService {

    String createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    StatsResponse getAllStats();
}
