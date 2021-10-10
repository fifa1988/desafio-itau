package br.com.itau.desafio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.itau.desafio.DesafioItauApiApplication;

class DesafioItauApiApplicationTest {
	
	@Mock
	ApplicationContext application;
	
	@Test
	void contextLoads() throws Exception {
		DesafioItauApiApplication desafioMuxiApiApplicationMock = Mockito.mock(DesafioItauApiApplication.class);
		Assertions.assertNotNull(desafioMuxiApiApplicationMock);
		desafioMuxiApiApplicationMock.setApplicationContext(application);
		desafioMuxiApiApplicationMock.init();
		desafioMuxiApiApplicationMock.configure(new SpringApplicationBuilder(DesafioItauApiApplication.class));
	}
}
