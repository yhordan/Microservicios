package com.service.usuario.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.service.usuario.entity.Carro;
import com.service.usuario.entity.Moto;
import com.service.usuario.entity.Usuario;
import com.service.usuario.feignClients.CarroFeignClients;
import com.service.usuario.feignClients.MotoFeignClients;
import com.service.usuario.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CarroFeignClients carroFeignClients;
	
	@Autowired
	MotoFeignClients motoFeignClients;
	
	public List<Usuario> listar(){
		List<Usuario> listaUser = userRepository.findAll();
		return listaUser;
	}
	
	public Usuario crearUsuario(Usuario user) {
		return userRepository.save(user);
	}
	
	public Usuario buscarUsuario(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public List<Carro> listarCarro(int id){
		List<Carro> listarCarro = restTemplate.getForObject("http://localhost:8003/carro/listarUsuario/"+id, List.class);
		return listarCarro;
	}
	
	public List<Moto> listarMoto(int id){
		List<Moto> listarMoto = restTemplate.getForObject("http://localhost:8002/moto/listarUsuario/"+id, List.class);
		return listarMoto;
	}
	
	public Carro saveCarro(int id,Carro carro) {
		carro.setUserId(id);
		Carro nuevo = carroFeignClients.crearCarro(carro);
		return nuevo;
	}
	
	public Moto saveMoto(int id, Moto moto) {
		moto.setUserId(id);
		Moto nuevo = motoFeignClients.crearMoto(moto);
		return nuevo;
	}
	
	public Map<String, Object> listarAll(int id){
		Map<String, Object> result = new HashMap<>();
		Usuario usuario = userRepository.findById(id).orElse(null);
		if(usuario == null) {
			result.put("Mensaje", "No existe el usuario");
			System.out.println("usuario");
			return result;
		}
		result.put("Usuario", usuario);
		List<Carro> carro = carroFeignClients.listarCarro(id);
		if(carro.isEmpty()) {
			result.put("Carro","El usuario "+ usuario.getName()+ " no tiene carros");
		}else {
			result.put("Carro", carro);
		}
		List<Moto> moto = motoFeignClients.listarMoto(id);
		if(moto.isEmpty()) {
			result.put("Moto","El usuario "+ usuario.getName()+ " no tiene motos");
		}else {
			result.put("Moto", moto);
		}
		
		return result;
	
	}

}
