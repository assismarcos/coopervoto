package com.disciolli.coopervoto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disciolli.coopervoto.model.Associado;

public interface AssociadoRepository extends MongoRepository<Associado, String> {

}
