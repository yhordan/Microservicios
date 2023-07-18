package com.service.usuario.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.usuario.entity.Carro;
import com.service.usuario.entity.Moto;
import com.service.usuario.entity.Usuario;
import com.service.usuario.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/crear")
	public ResponseEntity<Usuario> guardar (@RequestBody Usuario user){
		return new ResponseEntity<>(userService.crearUsuario(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listarUsuario(){
		List<Usuario> lista = userService.listar();
		if(lista.isEmpty())
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<Usuario>>(lista,HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Usuario> buscarUsuario (@PathVariable("id") int id){
		Usuario usuario = userService.buscarUsuario(id);
		if(usuario == null)
			return ResponseEntity.noContent().build();
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}
	
	@GetMapping("/listarMoto/{id}")
	public ResponseEntity<List<Moto>> buscarUsuarioMoto(@PathVariable("id") int id){
		List<Moto> moto = userService.listarMoto(id);
		if(moto.isEmpty())
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<Moto>>(moto,HttpStatus.OK);
	}
	
	@GetMapping("/listarCarro/{id}")
	public ResponseEntity<List<Carro>> buscarUsuarioCarro(@PathVariable("id") int id){
		List<Carro> carro = userService.listarCarro(id);
		if(carro.isEmpty())
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<Carro>>(carro,HttpStatus.OK);
	}
	
	@PostMapping("/crearCarro/{id}")
	public ResponseEntity<Carro> crearCarro(@RequestBody Carro carro,@PathVariable int id){
		if(userService.buscarUsuario(id)==null) {
			return ResponseEntity.notFound().build();
		}
		Carro carroNuevo = userService.saveCarro(id, carro);
		return new ResponseEntity<Carro>(carroNuevo,HttpStatus.CREATED);
	}
	
	@PostMapping("/crearMoto/{id}")
	public ResponseEntity<Moto> crearMoto(@RequestBody Moto moto,@PathVariable int id){
		if(userService.buscarUsuario(id)==null) {
			return ResponseEntity.notFound().build();
		}
		Moto motoNuevo = userService.saveMoto(id, moto);
		return new ResponseEntity<Moto>(motoNuevo,HttpStatus.CREATED);
	}
	
	@GetMapping("/listarAll/{id}")
	public ResponseEntity<Map<String, Object>> listarAll(@PathVariable int id){
		Map<String, Object> map = userService.listarAll(id);
		return ResponseEntity.ok(map); 
	}
}
