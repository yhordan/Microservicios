package com.service.usuario.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.usuario.entity.Moto;

@FeignClient(name = "moto-service", url = "http://localhost:8002")
public interface MotoFeignClients {

	@RequestMapping(method = RequestMethod.POST, value = "/moto/crear")
	Moto crearMoto (@RequestBody Moto moto);
	
	@RequestMapping(method = RequestMethod.GET, value = "/moto/listarUsuario/{id}")
	List<Moto> listarMoto(@PathVariable int id);
}
