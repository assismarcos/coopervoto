package com.disciolli.coopervoto.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.disciolli.coopervoto.model.Pauta;

public interface PautaRepository extends MongoRepository<Pauta, String> {

	@Query("{'sessao' : {'$ne' : null}, 'sessao.dataHoraFim' : {'$gt' : ?0}}")
	public List<Pauta> findAllSessaoEmVotacao(LocalDateTime dateTime);

	@Query("{'sessao' : {'$ne' : null}, 'sessao.dataHoraFim' : {'$lt' : ?0}, 'resultadoProcessado' : {'$eq' : false}}")
	public List<Pauta> findAllPendenteResultado(LocalDateTime dateTime);

	
}
