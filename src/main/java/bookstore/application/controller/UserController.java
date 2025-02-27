package bookstore.application.controller;

import bookstore.application.dto.UserDto;
import bookstore.application.entity.User;
import bookstore.application.mapper.UserMapper;
import bookstore.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto user) {
        User mappedUser = UserMapper.mapUserDtoToUser.apply(user);
        User createdUser = userService.create(mappedUser);
        UserDto createdUserDto = UserMapper.mapUserToUserDto.apply(createdUser);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/verify-account")
    public ResponseEntity<?> verifyCode(@RequestParam Long id, @RequestParam Long verificationCode) {
        User verifiedUser = userService.verifyCode(id, verificationCode);
        return ResponseEntity.ok(verifiedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        User mappedUser = UserMapper.mapUserDtoToUser.apply(user);
        User loginUser = userService.loginUser(mappedUser);
        UserDto loginUserDto = UserMapper.mapUserToUserDto.apply(loginUser);
        return ResponseEntity.ok(loginUserDto);
    }
}
