package bookstore.application.mapper;

import bookstore.application.dto.ExemplaryDto;
import bookstore.application.entity.Exemplary;

import java.util.function.Function;

public class ExemplaryMapper {
    public static final Function<Exemplary, ExemplaryDto> mapExemplaryToExemplaryDto = ExemplaryMapper::mapExemplaryToExemplaryDto;

    public static final Function<ExemplaryDto, Exemplary> mapExemplaryDtoToExemplary = ExemplaryMapper::mapExemplaryDtoToExemplary;

    private static Exemplary mapExemplaryDtoToExemplary(ExemplaryDto exemplaryDto) {
        return Exemplary.builder()
                .id(exemplaryDto.getId())
                .publisher(exemplaryDto.getPublisher())
                .maximumReservationDuration(exemplaryDto.getMaximumReservationDuration())
                .build();
    }

    private static ExemplaryDto mapExemplaryToExemplaryDto(Exemplary exemplary) {
        return ExemplaryDto.builder()
                .id(exemplary.getId())
                .publisher(exemplary.getPublisher())
                .maximumReservationDuration(exemplary.getMaximumReservationDuration())
                .book(BookMapper.mapBookToBookDto.apply(exemplary.getBook()))
                .build();
    }
}
