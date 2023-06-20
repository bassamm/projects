package com.sample.trainingacademy.repository;

import com.sample.trainingacademy.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query(value = "SELECT * FROM training WHERE name=:training_name ORDER BY id ASC", nativeQuery = true)
    List<Training> findAllByName(@Param("training_name") String name);
    @Query(value = "SELECT * FROM training WHERE name=:training_name AND start_date=:start AND end_date=:end ORDER BY id ASC", nativeQuery = true)
    List<Training> findAllByNameWithExactTime(@Param("training_name") String name, @Param("start") Date start, @Param("end") Date end);
    @Query(value = "SELECT * FROM training WHERE (start_date<=:start AND end_date>=:start) OR (start_date<=:end AND end_date>=:end) ORDER BY id ASC", nativeQuery = true)
    List<Training> findAllBetweenTimeframe( @Param("start") Date start, @Param("end") Date end);
}
