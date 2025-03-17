package bookstore.application.dto;

import bookstore.application.validator.ContactInformationValidator;
import bookstore.application.validator.InformationValidator;
import bookstore.application.validator.phoneNumber.ValidPhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class LibraryDto {
    private Long id;

    @NotNull
    private String address;

    @NotNull(groups = InformationValidator.class)
    @ValidPhoneNumber(groups = ContactInformationValidator.class)
    private String phoneNumber;
}
