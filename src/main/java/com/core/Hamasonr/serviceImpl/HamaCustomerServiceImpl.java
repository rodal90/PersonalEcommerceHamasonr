package com.core.Hamasonr.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.core.Hamasonr.data.model.HamaCustomer;
import com.core.Hamasonr.data.repository.IHamaCustomerRepository;

import com.core.Hamasonr.service.IHamaCustomerService;


@Service
public class HamaCustomerServiceImpl implements IHamaCustomerService {

    @Autowired
    private IHamaCustomerRepository hamaCustomerRepository;
    

    @Override
    public HamaCustomer save(HamaCustomer customer) {
    	
        return this.hamaCustomerRepository.save(customer);
    }

    @Override
    public Optional<HamaCustomer> findById(Long id) {
        return this.hamaCustomerRepository.findById(id);
    }

    @Override
    public List<HamaCustomer> findAll() {
        return this.hamaCustomerRepository.findAll();
    }

    @Override
    public HamaCustomer newHamaCustomer() {
        HamaCustomer customer = new HamaCustomer();
        customer.setHamaCustomerOrders(new ArrayList<>()); // Inicializa la lista vacía
        return customer;
    }

    @Override
    @Secured({ "MANAGER" })
    public String deleteById(Long id) {
        if (!hamaCustomerRepository.existsById(id)) {
            return "No encontrado";
        }
        hamaCustomerRepository.deleteById(id);
        return "Borrado";
    }


    
}
