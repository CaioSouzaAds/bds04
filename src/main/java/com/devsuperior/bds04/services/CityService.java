package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
    private CityRepository repository;
	
	public List<CityDTO> findAll() {
        List<City> list = repository.findAll(); 
        
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }
	

	
	public CityDTO insert(CityDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
