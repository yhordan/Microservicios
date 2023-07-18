package com.service.usuario.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.usuario.entity.Carro;

@FeignClient(name = "carro-service", url = "http://localhost:8003")
public interface CarroFeignClients {

	@RequestMapping(method = RequestMethod.POST, value = "/carro/crear")
	Carro crearCarro(@RequestBody Carro carro);
	
	@RequestMapping(method = RequestMethod.GET, value = "/carro/listarUsuario/{id}")
	List<Carro> listarCarro(@PathVariable int id);
}
