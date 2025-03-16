package bookstore.application.mapper;

import bookstore.application.dto.ReservationDto;
import bookstore.application.entity.Reservation;

import java.util.function.Function;

public class ReservationMapper {
    public static final Function<Reservation, ReservationDto> mapReservationToReservationDto = ReservationMapper::mapReservationToReservationDto;

    public static final Function<ReservationDto, Reservation> mapReservationDtoToReservation = ReservationMapper::mapReservationDtoToReservation;

    private static ReservationDto mapReservationToReservationDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .status(reservation.getStatus())
                .user(UserMapper.mapUserToUserDto.apply(reservation.getUser()))
                .exemplary(ExemplaryMapper.mapExemplaryToExemplaryDto.apply(reservation.getExemplary()))
                .build();
    }

    private static Reservation mapReservationDtoToReservation(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(reservationDto.getId())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .status(reservationDto.getStatus())
                .build();
    }
}
