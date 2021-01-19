package com.disciolli.coopervoto.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import com.disciolli.coopervoto.dto.ResultadoVotacaoDto;
import com.disciolli.coopervoto.service.KafkaService;

@SpringBootTest
public class KafkaServiceTest {

	@Value("${coopervoto.resultadovotacao.kafka.topic}")
	private String topicoKafka;

	@InjectMocks
	private KafkaService<ResultadoVotacaoDto> kafkaService;

	@Mock
	private KafkaTemplate<String, ResultadoVotacaoDto> kafkaTemplate;

	@Test
	public void testaEnvioDeMensagem() {
		when(kafkaTemplate.send(anyString(), any())).thenReturn(new SettableListenableFuture<>());

		ListenableFuture<SendResult<String, ResultadoVotacaoDto>> resultListenableFuture = kafkaService
				.send(topicoKafka, new ResultadoVotacaoDto());

		assertNotNull(resultListenableFuture);
		verify(kafkaTemplate, times(1)).send(anyString(), any());
	}
}
