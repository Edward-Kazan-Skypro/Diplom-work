package pro.sky.finalprojectsky.service;

import pro.sky.finalprojectsky.dto.RegisterReq;
import pro.sky.finalprojectsky.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);
}
