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
public class AssociadoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	private String url = "/api/v1/associado";

	private String jsonTemplate = "{\n" + "\"cpf\": \"%s\",\n" + "\"nome\": \"%s\"\n" + "}";

	// O ideal seria utilizar um gerador de CPF/Nome.
	private String cpfOk = "35327189015";
	private String cpfErro = "15327189019";
	private String nome = "Maria dos Santos";

	@Test
	public void testCadastroComSucesso() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
				.content(String.format(jsonTemplate, cpfOk, nome))).andExpect(status().isOk());
	}
	
	@Test
	public void testCadastroComErro() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
				.content(String.format(jsonTemplate, cpfErro, nome))).andExpect(status().isBadRequest());
	}

}
