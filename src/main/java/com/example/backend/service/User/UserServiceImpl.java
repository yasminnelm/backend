package com.example.backend.service.User;

import com.example.backend.jwt.JwtProvider;
import com.example.backend.model.enumeration.Roles;
import com.example.backend.repository.AgentRepository;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public Object findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Roles role = Roles.valueOf(jwtProvider.getRoleFromJwtToken(jwt));

        if (role == Roles.ROLE_AGENT) {
            return agentRepository.findAgentByEmail(email);
        } else if (role == Roles.ROLE_CLIENT) {
            return clientRepository.findClientByEmail(email);
        } else {
            throw new Exception("User role not recognized");
        }
    }

}
