package com.dev.superior.dscommerce.services;

import com.dev.superior.dscommerce.dto.ProductDTO;
import com.dev.superior.dscommerce.entities.Product;
import com.dev.superior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository repository;

    @Transactional(readOnly = true )
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}
