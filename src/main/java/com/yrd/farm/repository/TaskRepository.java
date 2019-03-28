package com.yrd.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yrd.farm.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
