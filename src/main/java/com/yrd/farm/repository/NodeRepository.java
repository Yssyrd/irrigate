package com.yrd.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yrd.farm.entities.Node;

public interface NodeRepository extends JpaRepository<Node, Integer> {

}
