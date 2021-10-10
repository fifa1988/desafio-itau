package br.com.itau.desafio.config.authorization.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.itau.desafio.data.dto.UserDTO;
import br.com.itau.desafio.exception.APIException;

@Service
public class AuthenticationValidation {
	private MessageSource messageSource;
	
	@Autowired
	public AuthenticationValidation(MessageSource messageSource){
		this.messageSource = messageSource;
	}
	
	public UserDTO validarToken(HttpServletRequest request) throws APIException {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty())
			throw new APIException(messageSource.getMessage("token.invalido", null, LocaleContextHolder.getLocale()), 
					HttpStatus.UNAUTHORIZED);
		
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getDecoder();
		JSONObject objTokenJwt;
		try {
			objTokenJwt = new JSONObject(new String(decoder.decode(chunks[1])));
		} catch (Exception e) {
			throw new APIException(messageSource.getMessage("token.invalido", null, LocaleContextHolder.getLocale()), 
					HttpStatus.UNAUTHORIZED);
		}
		
		// CHECAR SE TOKEN ESTÁ EXPIRADO
		if (Timestamp.valueOf(LocalDateTime.now())
				.after(new Timestamp(objTokenJwt.getLong("exp") * 1000)))
			throw new APIException(messageSource.getMessage("token.expirado", null, LocaleContextHolder.getLocale()), 
					HttpStatus.UNAUTHORIZED);
		
		// DECRIPTAR O TOKEN
		UserDTO user;
		try {
			user = new Gson().fromJson(objTokenJwt.toString(),UserDTO.class);
			user.setUsername(objTokenJwt.getString("user_name"));
		} catch (Exception e) {
			throw new APIException(messageSource.getMessage("usuario.nao-encontrado", null, LocaleContextHolder.getLocale()), 
	    			HttpStatus.UNAUTHORIZED, null, e);
		}
		
		return user;
	}
}

