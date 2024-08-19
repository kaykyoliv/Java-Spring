package com.devsuperior.dscatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private CategoryRepository repositoryCategory;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> entity = repository.findAll(pageable);
        return entity.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(entity, dto);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete (Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found");
        }
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }
    
    private void copyDtoToEntity(Product entity, ProductDTO dto) {
    	entity.setName(dto.getName());
    	entity.setDescription(dto.getDescription());
    	entity.setPrice(dto.getPrice());
    	entity.setDate(dto.getDate());
    	entity.setImgUrl(dto.getImgUrl());
    	
    	entity.getCategories().clear();
    	for(CategoryDTO catDto : dto.getCategories()) {
    		Category category = repositoryCategory.getReferenceById(catDto.getId());
    		entity.getCategories().add(category);
    	}
    }

}
