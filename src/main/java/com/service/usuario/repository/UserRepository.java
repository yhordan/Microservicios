package com.service.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.usuario.entity.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

}
