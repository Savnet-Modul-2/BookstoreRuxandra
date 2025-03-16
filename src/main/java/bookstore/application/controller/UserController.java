package bookstore.application.controller;

import bookstore.application.dto.ReservationDto;
import bookstore.application.dto.UserDto;
import bookstore.application.entity.Reservation;
import bookstore.application.entity.User;
import bookstore.application.mapper.ReservationMapper;
import bookstore.application.mapper.UserMapper;
import bookstore.application.service.ReservationService;
import bookstore.application.service.UserService;
import bookstore.application.validator.ContactInformationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> create(@Validated(ContactInformationSequence.class)
                                    @RequestBody UserDto user) {
        User mappedUser = UserMapper.mapUserDtoToUser.apply(user);
        User createdUser = userService.create(mappedUser);
        UserDto createdUserDto = UserMapper.mapUserToUserDto.apply(createdUser);
        return ResponseEntity.ok(createdUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        User mappedUser = UserMapper.mapUserDtoToUser.apply(user);
        User loginUser = userService.login(mappedUser);
        UserDto loginUserDto = UserMapper.mapUserToUserDto.apply(loginUser);
        return ResponseEntity.ok(loginUserDto);
    }

    @PutMapping("/verify-account")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam Long verificationCode) {
        User verifiedUser = userService.verifyCode(email, verificationCode);
        return ResponseEntity.ok(verifiedUser);
    }

    @DeleteMapping
    public ResponseEntity<?> returnBook(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.mapReservationDtoToReservation.apply(reservationDto);
        reservationService.returnBook(reservation);
        return ResponseEntity.ok().build();
    }
}
