package com.example.backend.service.User;

import com.example.backend.model.entity.Agent;
import com.example.backend.model.entity.Client;

public interface UserService {
    Object findUserByJwtToken(String jwt) throws Exception;
}