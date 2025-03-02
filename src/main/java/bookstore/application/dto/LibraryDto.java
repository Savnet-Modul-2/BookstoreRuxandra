package bookstore.application.dto;

import lombok.*;

import java.util.List;

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
