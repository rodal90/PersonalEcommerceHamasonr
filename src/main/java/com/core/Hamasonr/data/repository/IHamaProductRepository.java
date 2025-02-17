package com.core.Hamasonr.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.core.Hamasonr.data.model.HamaProduct;

@Repository
public interface IHamaProductRepository extends JpaRepository<HamaProduct, Long> {

}