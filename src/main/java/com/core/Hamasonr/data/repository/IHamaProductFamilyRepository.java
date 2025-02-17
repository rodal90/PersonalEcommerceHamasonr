package com.core.Hamasonr.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.Hamasonr.data.model.HamaOrderLine;
import com.core.Hamasonr.data.model.HamaProductFamily;

@Repository
public interface IHamaProductFamilyRepository extends JpaRepository<HamaProductFamily, Long> {
	
	    Optional<HamaProductFamily> findByFamilyName(String familyName);

}