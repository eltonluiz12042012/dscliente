package com.projeto.dscliente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dscliente.dto.ClientDto;
import com.projeto.dscliente.entities.Client;
import com.projeto.dscliente.repositories.ClientRepository;
import com.projeto.dscliente.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	
	@Autowired
	private ClientRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest){
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDto(x));
		
	}
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		
		Optional<Client> objClient = repository.findById(id); 
		Client entity = objClient.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
		return new ClientDto(entity);
	}
}
