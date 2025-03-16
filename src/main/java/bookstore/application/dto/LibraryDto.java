package bookstore.application.dto;

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

    private String address;

    private String phoneNumber;
}
