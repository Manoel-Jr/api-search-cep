package com.example.challenge.api.service;

import com.example.challenge.api.entity.Endereco;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

	@Autowired
	private ConsumerViaCep consumerViaCep;

	public Endereco getEndereco(String cep) {
		String jsonResponse = consumerViaCep.consumirWebServiceViaCep(cep);
		Gson gson = new Gson();
		Endereco endereco = gson.fromJson(jsonResponse, Endereco.class);
		return endereco;
	}
}
