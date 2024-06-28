package com.devsuperior.crud.services;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.entities.Client;
import com.devsuperior.crud.repositories.ClientRepository;
import com.devsuperior.crud.services.exceptions.DatabaseException;
import com.devsuperior.crud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> entity = repository.findAll(pageable);
        return  entity.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        CopyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id){
        try{
            Client entity = repository.getReferenceById(id);
            CopyDtoToEntity(entity, dto);
            entity = repository.save(entity);
            return new ClientDTO(entity);
         }
        catch (EntityNotFoundException e){
             throw new ResourceNotFoundException("Recurso não encontrado");
         }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }
       	catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void CopyDtoToEntity(Client entity, ClientDTO dto) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
