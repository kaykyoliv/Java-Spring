package com.dev.superior.dscommerce.services.exceptions;

import com.dev.superior.dscommerce.dto.CategoryDTO;
import com.dev.superior.dscommerce.entities.Category;
import com.dev.superior.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
