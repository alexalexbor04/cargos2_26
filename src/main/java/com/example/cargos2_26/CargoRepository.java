package com.example.cargos2_26;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargos, Integer> {
    @Query("SELECT p FROM Cargos p WHERE  CONCAT(p.name, '', p.content, '', p.sending_city, '', p.sending_date," +
            " '', p.arrival_city, '', p.arrival_date) LIKE %?1%")
    List<Cargos> search(String keyword);

    @Query("SELECT p FROM Cargos p WHERE DATE(p.sending_date) = ?1")
    List<Cargos> filterBySendingDate(java.sql.Date sending_date);

    @Query("SELECT p FROM Cargos p WHERE DATE(p.arrival_date) = ?1")
    List<Cargos> filterByArrivalDate(java.sql.Date arrival_date);

    @Query("SELECT DATE(sending_date), COUNT(sending_date) FROM Cargos GROUP BY DATE(sending_date) ORDER BY DATE(sending_date)")
    List<Object[]> countByDayChart_send();

    @Query("SELECT DATE(arrival_date), COUNT(arrival_date) FROM Cargos GROUP BY DATE(arrival_date) ORDER BY DATE(arrival_date)")
    List<Object[]> countByDayChart_arr();
}
