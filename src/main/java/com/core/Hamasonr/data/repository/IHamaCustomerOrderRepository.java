package com.core.Hamasonr.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.Hamasonr.data.model.HamaCustomerOrder;


@Repository
public interface IHamaCustomerOrderRepository extends JpaRepository<HamaCustomerOrder, Long> {

}
