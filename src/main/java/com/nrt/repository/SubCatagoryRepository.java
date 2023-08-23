package com.nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrt.entity.SubCatagory;

@Repository
public interface SubCatagoryRepository extends JpaRepository<SubCatagory,Long>{

}
