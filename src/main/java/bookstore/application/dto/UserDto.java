package bookstore.application.dto;

import bookstore.application.entity.Book;
import bookstore.application.enums.Gender;
import bookstore.application.validator.ContactInformationValidator;
import bookstore.application.validator.InformationValidator;
import bookstore.application.validator.email.ValidEmail;
import bookstore.application.validator.password.ValidPassword;
import bookstore.application.validator.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class UserDto {
    private Long id = null;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Integer yearOfBirth;

    private String gender;

    @NotNull(groups = InformationValidator.class)
    @ValidEmail(groups = ContactInformationValidator.class)
    private String email;

    @NotNull(groups = InformationValidator.class)
    @ValidPhoneNumber(groups = ContactInformationValidator.class)
    private String phoneNumber;

    @NotNull(groups = InformationValidator.class)
    @ValidPassword(groups = ContactInformationValidator.class)
    private String password;

    private String country;

    private Boolean verifiedAccount = false;

    private Long verificationCode;

    private List<BookDto> wishlist = new ArrayList<>();
}
