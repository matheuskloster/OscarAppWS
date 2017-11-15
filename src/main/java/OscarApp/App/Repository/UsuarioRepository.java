package OscarApp.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import OscarApp.App.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
}