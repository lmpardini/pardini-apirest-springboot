package br.com.pardini.crudspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pardini.crudspringboot.model.Usuario;
import br.com.pardini.crudspringboot.repository.UsuarioRepository;

@RestController
@RequestMapping
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
		
	@GetMapping(value = "listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listarUsuários(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarusernome") //Pesquisa usuario por nome;
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarUserPorNome(@RequestParam(name="name") String name){
		
		List<Usuario> usuario  = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscaruserid") //Pesquisa usuario por ID;
	@ResponseBody
	public ResponseEntity<Usuario> buscarUserPorId(@RequestParam(name="iduser") Long iduser){
		
		Usuario usuario  = usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value = "adicionar")//adiciona cadastros
	@ResponseBody
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "atualizar")//altera cadastros
	@ResponseBody
	public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario) {
		
		if(usuario.getId() == null){
			
			return new ResponseEntity<String>("Id não encontrado", HttpStatus.NOT_FOUND);					
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);		
	}
	
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long iduser){
		
		if(usuarioRepository.findById(iduser) == null){
			
			return new ResponseEntity<String>("Usuário não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		usuarioRepository.deleteById(iduser);
		
		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
	}
	

	
	
			
			
		
		
	}
	
	

