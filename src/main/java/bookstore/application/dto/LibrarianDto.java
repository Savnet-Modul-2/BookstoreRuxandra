package bookstore.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LibrarianDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private LibraryDto libraryDto;

//    private Boolean accountVerified;
}
