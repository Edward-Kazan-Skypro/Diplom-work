package pro.sky.finalprojectsky.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.finalprojectsky.dto.CreateUserDto;
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.dto.Role;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.mapper.UserMapper;
import pro.sky.finalprojectsky.service.impl.UserServiceImpl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {


    private final MockMvc mockMvc;


    private final ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserMapper userMapper;


    @Autowired
    UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void addUser() throws Exception {
        UserDto userDto = new UserDto();
        CreateUserDto createUserDto = new CreateUserDto();

        userDto.setEmail("a@mail.ru");
        userDto.setFirstName("Ivan");
        userDto.setLastName("Ivanov");
        userDto.setPhone("+79991254698");

        createUserDto.setEmail("a@mail.ru");
        createUserDto.setFirstName("Ivan");
        createUserDto.setLastName("Ivanov");
        createUserDto.setPassword("12345678");
        createUserDto.setPhone("+79991254698");

        when(userService.createUser(any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(createUserDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.phone").value(userDto.getPhone()));
    }

    @Test
    void getUsers() throws Exception {
        UserDto userDto = new UserDto();

        userDto.setEmail("a@mail.ru");
        userDto.setFirstName("Ivan");
        userDto.setLastName("Ivanov");
        userDto.setPhone("+79991254698");

        when(userService.getUserMe(any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.phone").value(userDto.getPhone()));
    }

    @Test
    void update() throws Exception {
        UserDto userDto = new UserDto();

        userDto.setEmail("a@mail.ru");
        userDto.setFirstName("Ivan");
        userDto.setLastName("Ivanov");
        userDto.setPhone("+79991254698");

        when(userService.updateUser(any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.phone").value(userDto.getPhone()));
    }

    @Test
    void setPassword() throws Exception {

        NewPasswordDto passwordDto = new NewPasswordDto();
        passwordDto.setCurrentPassword("12345678");
        passwordDto.setNewPassword("87654321");

        doNothing().when(userService).newPassword(anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passwordDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPassword").value(passwordDto.getCurrentPassword()))
                .andExpect(jsonPath("$.newPassword").value(passwordDto.getNewPassword()));
    }

    @Test
    void getUser() throws Exception {
        UserDto userDto = new UserDto();

        userDto.setId(1);
        userDto.setEmail("a@mail.ru");
        userDto.setFirstName("Ivan");
        userDto.setLastName("Ivanov");
        userDto.setPhone("+79991254698");

        when(userService.getUserById(anyLong())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.phone").value(userDto.getPhone()));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    void updateRoleUser() throws Exception {
        UserDto userDto = new UserDto();

        userDto.setId(1);
        userDto.setEmail("a@mail.ru");
        userDto.setFirstName("Ivan");
        userDto.setLastName("Ivanov");
        userDto.setPhone("+79991254698");



        when(userService.updateRole(anyLong(), any(Role.class))).thenReturn(userDto);

        when(userMapper.toDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/1/updateRole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Role.ADMIN))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}