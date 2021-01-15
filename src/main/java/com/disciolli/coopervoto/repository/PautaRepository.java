package com.disciolli.coopervoto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disciolli.coopervoto.model.Pauta;

public interface PautaRepository extends MongoRepository<Pauta, String> {

}
