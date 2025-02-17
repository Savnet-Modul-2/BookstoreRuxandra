package bookstore.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Integer yearOfBirth;

    private String gender;

    private String email;

    private String phoneNumber;

    private String password;

    private String country;

    private Boolean verifiedAccount = false;

    private Long verificationCode;
}
