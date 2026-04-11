package com.example.usuarioApi.Repository;

import com.example.usuarioApi.Model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Métodos personalizados para buscar por correo electrónico y rut, si es necesario para la lógica de negocio.
    Optional<Usuario> findByCorreoElec(String correoElec);
    Optional<Usuario> findByRut(String rut);


}