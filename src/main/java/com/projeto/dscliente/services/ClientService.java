package com.projeto.dscliente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dscliente.dto.ClientDto;
import com.projeto.dscliente.entities.Client;
import com.projeto.dscliente.repositories.ClientRepository;
import com.projeto.dscliente.services.exceptions.DatabaseException;
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
	
	@Transactional
	public ClientDto insert(ClientDto dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDto(entity);
	}
	
	@Transactional
	public ClientDto update(Long id, ClientDto dto) {
		try {
			Client entity = repository.getReferenceById(id);
			entity = repository.save(entity);
			return new ClientDto(entity);
			
		} catch(javax.persistence.EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID not found " + id); 
		}catch(DataIntegrityViolationException e) {
			 throw new DatabaseException("Integrity vialotaion");
		}
	}
	
	private void copyDtoToEntity(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setBirtDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
	}
}
