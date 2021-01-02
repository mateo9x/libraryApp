package com.mateo9x.recruitmentapp.repository;

import com.mateo9x.recruitmentapp.model.Book;
import com.mateo9x.recruitmentapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query(value="SELECT * FROM RESERVATION WHERE reserved_at IS NOT NULL", nativeQuery= true)
    Optional<Reservation> FindAllWithQuery(@Query);

}
