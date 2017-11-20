package OscarApp.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import OscarApp.App.Entity.Usuario;
import OscarApp.App.Repository.UsuarioRepository;

@SuppressWarnings("rawtypes")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody String body) throws Exception {
		Usuario usuario = new ObjectMapper().readValue(body, Usuario.class);
		Usuario usuarioReal = this.usuarioRepository.findOne(usuario.getUsername());
		if (usuarioReal == null || !(usuario.getUsername().equals(usuarioReal.getUsername())
				&& usuario.getPassword().equals(usuarioReal.getPassword()))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou senha inválidos.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioReal);
	}

	@PutMapping("/usuarios")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Usuario usuario = new ObjectMapper().readValue(body, Usuario.class);
		Usuario usuarioReal = this.usuarioRepository.findOne(usuario.getUsername());
		if (usuarioReal != null && usuarioReal.getDiretor() != null && usuarioReal.getFilme() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Erro: Usuário já possui voto registrado.\"}");
		}
		if (usuario.getDiretor() == null || usuario.getFilme() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Erro: Você deve votar para um filme e um diretor.\"}");
		}
		return ResponseEntity.status(HttpStatus.OK).body(this.usuarioRepository.saveAndFlush(usuario));
	}

}
