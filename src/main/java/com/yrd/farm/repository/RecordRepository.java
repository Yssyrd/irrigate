package com.yrd.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yrd.farm.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {

}
