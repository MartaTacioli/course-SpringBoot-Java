package com.educandoweb.couse.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.couse.entities.Funcionario;
import com.educandoweb.couse.services.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResouce {

	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll(){
		List<Funcionario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id){
		Funcionario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> insert(@RequestBody Funcionario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCpf()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
//	@GetMapping(value = "/atualizarFuncionario/{cpf}")
//	public ResponseEntity<Funcionario> findBycpf(@PathVariable Funcionario cpf){
//		Funcionario obj = service.atualizarFuncionario(cpf);
//		return ResponseEntity.ok().body(obj);
//	}
	
//	@GetMapping(value = "/{nome}")
//	public ResponseEntity<Funcionario> findByNome(@PathVariable String nome){
//		Funcionario obj = service.findByNome(nome);
//		return ResponseEntity.ok().body(obj);
//	}
	
	@PutMapping(value = "/pendentes/aprovar")
	public ResponseEntity<List<Funcionario>> aprovarFuncionarios (@RequestBody List<Funcionario> obj){
		obj = service.aprovarFuncionarios(obj);
	 return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/atualizar-foto")
	public boolean atualizarFoto (@RequestBody Funcionario obj){
		return service.atualizarFoto(obj);
	 
	}
	
	@PutMapping(value = "/atualizar-curriculo")
	public boolean atualizarCurriculo (@RequestBody Funcionario obj){
		return service.atualizarCurriculo(obj);
	 
	}
	
	@GetMapping(value = "/pendentes")
	public ResponseEntity<List<Funcionario>> findPendentes(){
		List<Funcionario> list = service.findPendentes();
		return ResponseEntity.ok().body(list);
	}
}
