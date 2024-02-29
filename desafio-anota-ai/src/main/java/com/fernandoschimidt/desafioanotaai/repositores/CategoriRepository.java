package com.fernandoschimidt.desafioanotaai.repositores;

import com.fernandoschimidt.desafioanotaai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriRepository extends MongoRepository<Category,String> {
}
