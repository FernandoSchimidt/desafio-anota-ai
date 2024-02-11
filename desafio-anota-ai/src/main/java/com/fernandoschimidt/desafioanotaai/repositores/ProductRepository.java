package com.fernandoschimidt.desafioanotaai.repositores;

import com.fernandoschimidt.desafioanotaai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
