package com.educandoweb.couse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.Skills;
import com.educandoweb.couse.services.SkillsService;

@RestController
@RequestMapping(value = "/skills")
public class SkillsResouce {

	@Autowired
	private SkillsService service;
	
	@GetMapping
	public ResponseEntity<List<Skills>> findAll(){
		List<Skills> list = service.findAll();
		System.out.println(list.get(0).toString());
		System.out.println("ResponseEntity.ok().body(list);"+ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Skills> findById(@PathVariable Long id){
		Skills obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}