package com.core.Hamasonr.data.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.core.Hamasonr.data.model.HamaProvider;



@Repository
public interface IHamaProviderRepository extends JpaRepository<HamaProvider, Long> {
	
	public List<HamaProvider> findAllByOrderByProviderName();

}
