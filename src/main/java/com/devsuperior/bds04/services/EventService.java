package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.controllers.execeptions.CityNotFoundException;
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
	public Page<EventDTO> findAll(Pageable pageable) {
	    Page<Event> page = eventRepository.findAll(pageable);
	    return page.map(EventDTO::new);
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();

		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());

		if (dto.getCityId() != null) {
			City city = cityRepository.findById(dto.getCityId())
					.orElseThrow(() -> new CityNotFoundException("City not found for ID " + dto.getCityId()));
			entity.setCity(city);
		}

		entity = eventRepository.save(entity);
		return new EventDTO(entity);
	}
}
