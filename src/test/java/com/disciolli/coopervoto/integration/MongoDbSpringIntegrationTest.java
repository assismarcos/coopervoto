package com.disciolli.coopervoto.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.disciolli.coopervoto.model.Pauta;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MongoDbSpringIntegrationTest {
	@DisplayName("Testar MongoDB")
	@Test
	public void test(@Autowired MongoTemplate mongoTemplate) {

		assertNotNull(mongoTemplate);

		Pauta pSave = mongoTemplate.save(new Pauta("PautaTeste", "Detalhes da pauta"));
		
		assertNotNull(pSave);

		Pauta pSearch = mongoTemplate.findById(pSave.getId(), Pauta.class);
		
		assertNotNull(pSearch);

		assertEquals(pSave.getId(), pSearch.getId());

		mongoTemplate.remove(pSearch);
	}

}
