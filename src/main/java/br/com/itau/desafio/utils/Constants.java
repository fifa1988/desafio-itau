package br.com.itau.desafio.utils;

public class Constants {
	
	private Constants() {}
	
	// ### GENERAL
    public static final String DATE_TIME_UNIX_PATTERN = "dd/MM/yyyyTHH:mm:ss.SSS";
    
    public static final String TITLE_API = "ITAU - API DE DESAFIO DOS MICRO SERVIÇOS"; 
	public static final String TITLE_VERSION = "Version:";
	public static final String VERSION = " 1.0";
	public static final String TERMS_OF_SERVICE = "Terms of Service";
	public static final String AUTHOR = "Itaú | Feito para você";
	public static final String ITAU = "https://itau.com.br/";
	public static final String EMAIL_DEV = "itau@itau.com.br";
	public static final String LICENSE = "Apache License Version 2.0";
	public static final String LICENSE_LINK = "https://www.apache.org/licesen.html";
	
	public static final String MESSAGE_CODE200 = "Requisição realizada com sucesso";
	public static final String MESSAGE_CODE201 = "Criado com sucesso";
	public static final String MESSAGE_CODE204 = "Sem conteúdo";
	public static final String MESSAGE_CODE401 = "Requisição não autorizada";
	public static final String MESSAGE_CODE403 = "Requisição não permitida";
	public static final String MESSAGE_CODE404 = "Requisição inválida";
	public static final String MESSAGE_CODE500 = "Erro interno";
	
	public static final String CIRCUIT_BREAKER = "circuit-breaker";
	
	public static final String STATUS_CODE_FORMAT = "%s - %s";
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Ocorreu um erro interno no servidor";
	public static final String SERVER_UNAVALIABLE = "Microserviço indisponível no momento";
	public static final String PARAMETERS_INVALID_ERROR_MSG = "Falha ao converter parâmetros recebidos";

	public static final String ENDPOINT_LOGIN_TOKEN = "Recebe o token de login para autenticar o usuário";
	
	public static final String SCOPE_READ = "read";
	public static final String SCOPE_WRITE = "write";
	public static final String GRANT_TYPE = "password";
	public static final String TOKEN_KEY_ACCESS = "permitAll()";
	public static final String CHECK_TOKEN_ACCESS = "isAuthenticated()";
	
}
