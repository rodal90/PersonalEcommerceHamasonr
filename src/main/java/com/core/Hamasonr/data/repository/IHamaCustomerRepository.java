package com.core.Hamasonr.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.Hamasonr.data.model.HamaCustomer;

@Repository
public interface IHamaCustomerRepository extends JpaRepository<HamaCustomer, Long> {

}
