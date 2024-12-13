package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.entities.Category;

public class CategoryFactory {

    public static Category createCategory(){
        return new Category(2L, "Games");
    }

    public static Category createCategory(Long id, String name){
        return new Category(id, name);
    }
}