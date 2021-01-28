package silva.lucas.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import silva.lucas.cursomc.dto.EmailDTO;
import silva.lucas.cursomc.security.JWTUtil;
import silva.lucas.cursomc.security.UserSS;
import silva.lucas.cursomc.services.AuthService;
import silva.lucas.cursomc.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authotization", "Bearer" + token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void>forgot(@Valid @RequestBody EmailDTO objDto){
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
