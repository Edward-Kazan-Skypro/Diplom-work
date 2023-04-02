package pro.sky.finalprojectsky.service;

import org.springframework.stereotype.Service;
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.mappers.UserMapper;
import pro.sky.finalprojectsky.model.User;
import pro.sky.finalprojectsky.repository.UserRepository;
import java.util.ArrayList;

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
        //Сначала сделаем список всех юзеров
        ArrayList<User> usersList = (ArrayList<User>) userRepository.findAll();
        //В цикле ищем юзера с таким паролем
        for (User user: usersList) {
            if (user.getPassword().equals(oldPassword)){
                //Если нашли, то сделаем копию юзера
                User newUser = userRepository.getReferenceById(user.getId());
                //Обновляем поле password
                newUser.setPassword(body.getNewPassword());
                //Сохраним обновленные данные
                userRepository.save(newUser);
                return true;
            }
        }
        return false;
    }

    public boolean updateUser (UserDto body){
        if (userRepository.existsById(body.getId())){
            User user = userRepository.getReferenceById(body.getId());
            user = UserMapper.INSTANCE.dtoToEntity(body);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}