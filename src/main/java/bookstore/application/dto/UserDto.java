package bookstore.application.dto;

import bookstore.application.enums.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private Long id = null;

    private String firstName;

    private String lastName;

    private Integer yearOfBirth;

    private Gender gender;

    private String email;

    private String phoneNumber;

    private String password;

    private String country;

    private Boolean verifiedAccount = false;

    private Long verificationCode;
}
