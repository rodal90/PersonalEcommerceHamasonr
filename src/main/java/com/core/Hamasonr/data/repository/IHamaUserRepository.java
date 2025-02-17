package com.core.Hamasonr.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.Hamasonr.data.model.HamaUser;

@Repository
public interface IHamaUserRepository extends JpaRepository<HamaUser, String> { /* solo le tenemos que decir para que tabla y que tipo
de dato es el primarykey de user*/ 

}
