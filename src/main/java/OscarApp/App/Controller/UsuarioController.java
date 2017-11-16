package OscarApp.App.Controller;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import OscarApp.App.Entity.Usuario;
import OscarApp.App.Repository.UsuarioRepository;

@SuppressWarnings("rawtypes")
@RestController
// @RequestMapping(value = "usuarios")

public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	public ResponseEntity get() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.usuarioRepository.findAll());
	}

	@PostMapping("/usuarios")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Usuario usuario = new ObjectMapper().readValue(body, Usuario.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.usuarioRepository.saveAndFlush(usuario));
	}

	@PutMapping("/usuarios")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Usuario usuario = new ObjectMapper().readValue(body, Usuario.class);
		return ResponseEntity.status(HttpStatus.OK).body(this.usuarioRepository.saveAndFlush(usuario));
	}

	@RequestMapping("/usuarios/{username}")
	public String findByUsername(@PathVariable String username) throws Exception {
		ResponseEntity<Usuario> u = ResponseEntity.status(HttpStatus.OK).body(this.usuarioRepository.findByUsername(username));
		return u.getBody().getPassword();
	}

}
