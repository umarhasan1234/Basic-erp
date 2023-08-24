package com.nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrt.entity.Catagory;

@Repository
public interface CatagoryRepository extends JpaRepository<Catagory, Long>{

}
