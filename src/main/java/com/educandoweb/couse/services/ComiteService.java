package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Comite;
import com.educandoweb.couse.repositores.ComiteRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

@Service
public class ComiteService {
	
	@Autowired
	private ComiteRepository repository;

	public List<Comite> findAll(){
		return repository.findAll();	

	}
	
	public Comite findById(Long id) {
		Optional<Comite> obj = repository.findById(id);
		return obj.get();
	}
	
	public Comite insert(Comite obj) {
		return repository.save(obj);
	}
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}