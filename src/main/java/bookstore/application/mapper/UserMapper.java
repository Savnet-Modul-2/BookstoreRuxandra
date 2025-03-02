package bookstore.application.mapper;

import bookstore.application.dto.UserDto;
import bookstore.application.entity.User;

import java.util.function.Function;

public class UserMapper {
    public static final Function<User, UserDto> mapUserToUserDto = UserMapper::mapUserToUserDto;

    public static final Function<UserDto, User> mapUserDtoToUser = UserMapper::mapUserDtoToUser;

    private static User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .yearOfBirth(userDto.getYearOfBirth())
                .gender(userDto.getGender())
                .email(userDto.getEmail())
                .phoneNumber(String.valueOf(userDto.getPhoneNumber()))
                .password(userDto.getPassword())
                .country(userDto.getCountry())
                .verifiedAccount(userDto.getVerifiedAccount())
                .verificationCode(userDto.getVerificationCode())
                .build();
    }

    private static UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .yearOfBirth(user.getYearOfBirth())
                .gender(user.getGender())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .country(user.getCountry())
                .verifiedAccount(user.getVerifiedAccount())
                .verificationCode(user.getVerificationCode())
                .build();
    }
}
