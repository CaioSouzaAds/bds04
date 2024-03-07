package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public List<EventDTO> findAll() {
		List<Event> list = eventRepository.findAll();
		return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();

		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());

		if (dto.getCityId() != null) {
			City city = cityRepository.findById(dto.getCityId())
					.orElseThrow(() -> new RuntimeException("City not found")); // Ou uma exceção mais específica
			entity.setCity(city);
		}

		entity = eventRepository.save(entity);
		return new EventDTO(entity);
	}
}
