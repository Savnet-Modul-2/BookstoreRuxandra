package bookstore.application.dto;

import bookstore.application.validator.ContactInformationSequence;
import bookstore.application.validator.ContactInformationValidator;
import bookstore.application.validator.InformationValidator;
import bookstore.application.validator.email.ValidEmail;
import bookstore.application.validator.password.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class LibrarianDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull(groups = InformationValidator.class)
    @ValidEmail(groups = ContactInformationValidator.class)
    private String email;

    @NotNull(groups = InformationValidator.class)
    @ValidPassword(groups = ContactInformationValidator.class)
    private String password;

    @NotNull(groups = {InformationValidator.class, ContactInformationValidator.class})
    @Valid
    private LibraryDto library;
}
