package br.com.itau.desafio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.com.itau.desafio.utils.Constants;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${jwt.expiration}")
	private int EXPIRATION;
	
	@Value("${oauth.client.name}")
	private String APP_USER;
	
	@Value("${oauth.client.secret}")
	private String APP_PASS;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Override
	public void configure(AuthorizationServerSecurityConfigurer secutity) throws Exception {
		secutity
			.tokenKeyAccess(Constants.TOKEN_KEY_ACCESS)
			.checkTokenAccess(Constants.CHECK_TOKEN_ACCESS);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(APP_USER)
			.secret(passwordEncoder.encode(APP_PASS))
			.authorizedGrantTypes(Constants.GRANT_TYPE)
			.scopes(Constants.SCOPE_READ, Constants.SCOPE_WRITE)
			.accessTokenValiditySeconds(EXPIRATION);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager)
			.tokenStore(tokenStore)
			.accessTokenConverter(accessTokenConverter);
	}
}