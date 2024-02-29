package com.fernandoschimidt.desafioanotaai.services;

import com.fernandoschimidt.desafioanotaai.domain.category.Category;
import com.fernandoschimidt.desafioanotaai.domain.category.CategoryDTO;
import com.fernandoschimidt.desafioanotaai.domain.category.exeptions.CategoryNotFoundException;
import com.fernandoschimidt.desafioanotaai.repositores.CategoriRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
        private CategoriRepository repository;

        public CategoryService(CategoriRepository repository){
            this.repository = repository;
        }
        public Category insert(CategoryDTO categoryData){
            Category newCategory = new Category(categoryData);
            this.repository.save(newCategory);
            return  newCategory;
        }

        public List<Category>getAll(){
            return  this.repository.findAll();
        }
        public Category update(String id,CategoryDTO categoryData){
            Category category = this.repository.findById(id)
                    .orElseThrow(CategoryNotFoundException::new);

            if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
            if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
            this.repository.save(category);
            return  category;
        }
        public void delete(String id){
            Category category = this.repository.findById(id)
                    .orElseThrow(CategoryNotFoundException::new);

            this.repository.delete(category);
            }

            public Optional<Category> getbyId(String id){
            return  this.repository.findById(id);
            }
    }

