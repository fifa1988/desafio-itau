package br.com.itau.desafio.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantsTest {
	@Test
	void deveriaGerarObjeto() {
		Assertions.assertEquals("dd/MM/yyyyTHH:mm:ss.SSS", Constants.DATE_TIME_UNIX_PATTERN);
		
		Assertions.assertEquals("ITAU - API DE DESAFIO DOS MICRO SERVIÇOS", Constants.TITLE_API);
		Assertions.assertEquals("Version:", Constants.TITLE_VERSION);
		Assertions.assertEquals(" 1.0", Constants.VERSION);
		Assertions.assertEquals("Terms of Service", Constants.TERMS_OF_SERVICE);
		Assertions.assertEquals("Itaú | Feito para você", Constants.AUTHOR);
		Assertions.assertEquals("https://itau.com.br/", Constants.ITAU);
		Assertions.assertEquals("itau@itau.com.br", Constants.EMAIL_DEV);
		Assertions.assertEquals("Apache License Version 2.0", Constants.LICENSE);
		Assertions.assertEquals("https://www.apache.org/licesen.html", Constants.LICENSE_LINK);
		
		Assertions.assertEquals("Requisição realizada com sucesso", Constants.MESSAGE_CODE200);
		Assertions.assertEquals("Criado com sucesso", Constants.MESSAGE_CODE201);
		Assertions.assertEquals("Sem conteúdo", Constants.MESSAGE_CODE204);
		Assertions.assertEquals("Requisição não autorizada", Constants.MESSAGE_CODE401);
		Assertions.assertEquals("Requisição não permitida", Constants.MESSAGE_CODE403);
		Assertions.assertEquals("Requisição inválida", Constants.MESSAGE_CODE404);
		Assertions.assertEquals("Erro interno", Constants.MESSAGE_CODE500);
		
		Assertions.assertEquals("circuit-breaker", Constants.CIRCUIT_BREAKER);
		
		Assertions.assertEquals("%s - %s", Constants.STATUS_CODE_FORMAT);
		Assertions.assertEquals("Ocorreu um erro interno no servidor", Constants.INTERNAL_SERVER_ERROR_MESSAGE);
		Assertions.assertEquals("Microserviço indisponível no momento", Constants.SERVER_UNAVALIABLE);
		Assertions.assertEquals("Falha ao converter parâmetros recebidos", Constants.PARAMETERS_INVALID_ERROR_MSG);
		Assertions.assertEquals("Recebe o token de login para autenticar o usuário", Constants.ENDPOINT_LOGIN_TOKEN);
		
		Assertions.assertEquals("read", Constants.SCOPE_READ);
		Assertions.assertEquals("write", Constants.SCOPE_WRITE);
		Assertions.assertEquals("password", Constants.GRANT_TYPE);
		Assertions.assertEquals("permitAll()", Constants.TOKEN_KEY_ACCESS);
		Assertions.assertEquals("isAuthenticated()", Constants.CHECK_TOKEN_ACCESS);
	}
}
