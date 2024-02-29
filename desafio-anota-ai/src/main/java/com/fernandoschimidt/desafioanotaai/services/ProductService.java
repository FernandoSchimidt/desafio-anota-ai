package com.fernandoschimidt.desafioanotaai.services;

import com.fernandoschimidt.desafioanotaai.domain.category.Category;
import com.fernandoschimidt.desafioanotaai.domain.category.CategoryDTO;
import com.fernandoschimidt.desafioanotaai.domain.category.exeptions.CategoryNotFoundException;
import com.fernandoschimidt.desafioanotaai.domain.product.Product;
import com.fernandoschimidt.desafioanotaai.domain.product.ProductDTO;
import com.fernandoschimidt.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.fernandoschimidt.desafioanotaai.repositores.CategoriRepository;
import com.fernandoschimidt.desafioanotaai.repositores.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    public ProductService(ProductRepository productRepository,CategoryService categoryService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product insert(ProductDTO productData){
        Category category = this.categoryService.getbyId(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return  newProduct;
    }
    public Product update(String id, ProductDTO productData){
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if(productData.categoryId() != null){
            this.categoryService.getbyId(productData.categoryId())
                    .ifPresent(product::setCategory);
        }

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());

        this.productRepository.save(product);
        return  product;
    }

    public void delete(String id){
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);
    }
    public List<Product> getAll(){
        return  this.productRepository.findAll();
    }

}
