package com.disciolli.coopervoto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.disciolli.coopervoto.model.Sessao;

public interface SessaoRepository extends MongoRepository<Sessao, String> {

}
