package bookstore.application.controller;

import bookstore.application.dto.BookDto;
import bookstore.application.dto.ReservationDto;
import bookstore.application.dto.UserDto;
import bookstore.application.entity.Book;
import bookstore.application.entity.Reservation;
import bookstore.application.entity.User;
import bookstore.application.mapper.BookMapper;
import bookstore.application.mapper.ReservationMapper;
import bookstore.application.mapper.UserMapper;
import bookstore.application.service.BookService;
import bookstore.application.service.ReservationService;
import bookstore.application.service.UserService;
import bookstore.application.validator.ContactInformationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookService bookService;

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

    @PostMapping("/{userId}/wishlist")
    public ResponseEntity<?> addBookToWishlist(@PathVariable Long userId, @RequestBody BookDto book) {
        Book bookToAdd = BookMapper.mapBookDtoToBook.apply(book);
        User updatedUser = userService.addBookToWishlist(userId, bookToAdd);
        return ResponseEntity.ok(UserMapper.mapUserToUserDto.apply(updatedUser));
    }

    @GetMapping("/{userId}/wishlist")
    public ResponseEntity<?> getWishlist(@PathVariable Long userId) {
        List<BookDto> books = userService.getWishlist(userId)
                .stream()
                .map(BookMapper.mapBookToBookDto)
                .toList();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/verify-account")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam Long verificationCode) {
        User verifiedUser = userService.verifyCode(email, verificationCode);
        return ResponseEntity.ok(verifiedUser);
    }

    @DeleteMapping("/{userId}/wishlist/remove")
    public ResponseEntity<?> removeBookFromWishlist(@PathVariable Long userId, @RequestBody BookDto book) {
        Book bookToRemove = BookMapper.mapBookDtoToBook.apply(book);
        userService.removeBookFromWishlist(userId, bookToRemove);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> returnBook(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.mapReservationDtoToReservation.apply(reservationDto);
        reservationService.returnBook(reservation);
        return ResponseEntity.ok().build();
    }
}
