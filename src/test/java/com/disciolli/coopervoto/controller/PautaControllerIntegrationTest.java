package com.disciolli.coopervoto.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PautaControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	private String url = "/api/v1";

	private String jsonTemplate = "{\n" + "\"nome\": \"%s\",\n" + "\"descricao\": \"%s\"\n" + "}";

	private String pautaNome = "Saneamento Básico";
	private String pautaDescricao = "Aumentar orçamento para saneamento básico.";

	@Test
	public void testCadastrarPautaComSucesso() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(url + "/pauta").contentType(MediaType.APPLICATION_JSON)
				.content(String.format(jsonTemplate, pautaNome, pautaDescricao))).andExpect(status().isOk());
	}

}
