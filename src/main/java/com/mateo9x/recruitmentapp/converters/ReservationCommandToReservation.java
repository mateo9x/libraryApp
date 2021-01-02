package com.mateo9x.recruitmentapp.converters;

import com.mateo9x.recruitmentapp.commands.ReservationCommand;
import com.mateo9x.recruitmentapp.model.Reservation;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ReservationCommandToReservation implements Converter<ReservationCommand, Reservation> {

        @Synchronized
        @Nullable
        @Override
        public Reservation convert (ReservationCommand source){
            if (source == null) {
                return null;
            }
            final Reservation reservation = new Reservation();
            reservation.setId(source.getId());
            reservation.setReservedAt(source.getReservedAt());
            reservation.setUserId(source.getUserId());
            return reservation;
        }

    }
