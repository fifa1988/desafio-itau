package br.com.itau.desafio.config.authorization.model;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.itau.desafio.data.dto.UserDTO;
import br.com.itau.desafio.data.entity.Usuario;
import br.com.itau.desafio.exception.APIException;
import br.com.itau.desafio.service.UsuarioService;


@Service
public class Authentication implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Value("${jwt.expiration}")
	private Long exp;
	
	private Logger logger = LoggerFactory.getLogger(Authentication.class);
	
	@Override
	public UserDetails loadUserByUsername(String token) throws APIException, UsernameNotFoundException {
		UserDTO dataUser = null;
		try {
			dataUser = decryptToken(token);
		} catch (Exception e) {
			new APIException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		UserModel user = new UserModel();
		user.setUsername(dataUser.getUsername());
		user.setPassword(dataUser.getPassword());
		user.setClient_id(dataUser.getClient_id());
		user.setClient_user(dataUser.getClient_user());
		user.setClient_key(dataUser.getClient_key());
		
		return user;
	}
	
	public UserDTO decryptToken(String token) throws APIException, Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String password = request.getParameter("password");
		Usuario usuario = usuarioService.get(token, new String(Base64.getEncoder().encode(password.getBytes())));
		
		String authHeader = request.getHeader("Authorization");
		
		Base64.Decoder decoder = Base64.getDecoder();

		if (authHeader != null && authHeader.startsWith("Basic")) {
		    String encodedUsernamePassword = authHeader.substring("Basic ".length()).trim();
		    String usernamePassword = new String(decoder.decode(encodedUsernamePassword));

		    int seperatorIndex = usernamePassword.indexOf(':');

		    String acesskey = usernamePassword.substring(0, seperatorIndex);
		    String secretKey = usernamePassword.substring(seperatorIndex + 1);
		    
		    return UserDTO.builder()
					.token_login(token)
					.password(passwordEncoder.encode(usuario.getSenha()))
					.client_id(usuario.getConta().toString())
					.client_user(acesskey)
					.client_key(secretKey)
					.user_id(usuario.getId())
					.username(usuario.getEmail())
					.exp(exp)
					.email(usuario.getEmail())
					.super_admin(usuario.isUsersuper())
					.build();
		} else {
		    throw new APIException("The authorization header is either empty or isn't Basic.", logger);
		}
	}
}