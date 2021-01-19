package com.disciolli.coopervoto.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.disciolli.coopervoto.model.TotalVotoOpcao;
import com.disciolli.coopervoto.model.Voto;

public interface VotoRepository extends MongoRepository<Voto, String> {

	public Voto findByPautaIdAndCpf(String pautaId, String cpf);

	@Aggregation(pipeline = { "{$match: {'pautaId': {'$eq' : ?0}}}", "{$group: {_id: '$opcao', 'count': {$sum: 1}}}" })
	public AggregationResults<TotalVotoOpcao> calcularResultadoVotacao(String pautaId);

}
