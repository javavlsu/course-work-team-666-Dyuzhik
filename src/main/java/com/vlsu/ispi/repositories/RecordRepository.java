package com.vlsu.ispi.repositories;

import com.vlsu.ispi.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    Record findById(int id);
    List<Record> findRecordByClientId(int id);
    List<Record> findRecordByServiceId(int id);
    List<Record> findRecordByServiceIdAndDate(int id, Date date);
    List<Record> findRecordByDate(Date date);
    List<Record> findRecordByDateAndTime(Date date, Date time);
}
