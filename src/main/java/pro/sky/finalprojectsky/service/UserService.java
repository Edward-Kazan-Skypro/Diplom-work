package pro.sky.finalprojectsky.service;

import org.springframework.stereotype.Service;
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean setNewPassword(NewPasswordDto body){
        //Чтобы обновить пароль, проверим, правильно ли введен текущий пароль.
        //Получим текущий пароль
        String oldPassword = body.getCurrentPassword();
        //Как получить юзера?

        return false;
    }

}
