package com.example.challenge.api.rest;

import com.example.challenge.api.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.challenge.api.service.EnderecoService;

@RestController
@RequestMapping("/viacep")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> buscarApi(@PathVariable String cep) {
		return new ResponseEntity<>(enderecoService.getEndereco(cep), HttpStatus.OK);
	}
}
