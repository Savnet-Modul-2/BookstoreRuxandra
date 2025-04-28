package bookstore.application.mapper;

import bookstore.application.dto.UserDto;
import bookstore.application.entity.User;
import bookstore.application.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserMapperTest {
    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("testFirstname");
        user.setLastName("testLastName");
        user.setYearOfBirth(2020);
        user.setGender(Gender.FEMALE);
        user.setEmail("test@email.com");
        user.setPhoneNumber("0123456789");
        user.setPassword("testPassword");
        user.setCountry("testCountry");
        user.setVerifiedAccount(true);
        user.setVerificationCode(12345L);

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("testFirstname");
        userDto.setLastName("testLastName");
        userDto.setYearOfBirth(2020);
        userDto.setGender(Gender.FEMALE);
        userDto.setEmail("test@email.com");
        userDto.setPhoneNumber("0123456789");
        userDto.setPassword("testPassword");
        userDto.setCountry("testCountry");
        userDto.setVerifiedAccount(true);
        userDto.setVerificationCode(12345L);
    }

    @Test
    public void givenUser_mapUserToUserDto_resultedUserDto() {
        UserDto mappedUserDto = UserMapper.mapUserToUserDto.apply(user);

        Assertions.assertEquals(userDto, mappedUserDto);
    }
}
