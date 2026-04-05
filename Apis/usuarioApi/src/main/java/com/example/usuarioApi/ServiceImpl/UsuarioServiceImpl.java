package com.example.usuarioApi.ServiceImpl;

import com.example.usuarioApi.DTO.clasesUsuarioDTO.actualizarUserDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.actualizarUsuarioDTOAdmin;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.crearUsuarioDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.eliminarUserDTO;
import com.example.usuarioApi.DTO.clasesUsuarioDTO.leerUsuarioDTO;
import com.example.usuarioApi.Model.*; // Importar todos los modelos para las relaciones
import com.example.usuarioApi.Repository.*; // Importar todos los repositorios necesarios
import org.springframework.stereotype.Service;

import com.example.usuarioApi.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;




@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SexoUsuarioRepository sexoRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ComunaRepository comunaRepository;
    @Autowired
    private OficioRepository oficioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public leerUsuarioDTO crearUsuario(crearUsuarioDTO usuarioDTO) {
        // 1. Mapear el DTO de creación a la entidad Usuario, aplicando la lógica de valores por defecto.
        Usuario usuario = mapCrearUsuarioDTOToUsuario(usuarioDTO);

        // 2. Hashear la contraseña obtenida del DTO usando el PasswordEncoder.
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
 
        // 2. Guardar la nueva entidad de usuario en la base de datos.
        // El método save() de JPARepository devuelve la entidad guardada, que ahora incluye el ID generado.
        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // 3. Mapear la entidad recién guardada a un DTO de lectura para la respuesta.
        // Esto asegura que el cliente reciba el estado final del objeto, incluyendo el ID.
        return mapUsuarioToLeerUsuarioDTO(nuevoUsuario);
    }

    @Override
    public leerUsuarioDTO leerUsuario(Integer id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id)); 

        return mapUsuarioToLeerUsuarioDTO(usuario);
    }

    @Override
    public leerUsuarioDTO actualizarUsuario(Integer id, actualizarUserDTO usuarioDTO) {
        // TODO: Implementar la lógica para actualizar un usuario.
        throw new UnsupportedOperationException("El método 'actualizarUsuario' aún no ha sido implementado.");
    }

    @Override
    public void eliminarUsuario(Integer id, eliminarUserDTO deleteDTO) {
        // TODO: Implementar la lógica para eliminar un usuario.
        throw new UnsupportedOperationException("El método 'eliminarUsuario' aún no ha sido implementado.");
    }

    @Override
    public leerUsuarioDTO actualizarUsuarioAdmin(Integer id, actualizarUsuarioDTOAdmin usuarioDTO) {
        // TODO: Implementar la lógica para actualizar un usuario como administrador.
        throw new UnsupportedOperationException("El método 'actualizarUsuarioAdmin' aún no ha sido implementado.");
    }

    // --- MÉTODOS DE MAPEO ---

    

    private leerUsuarioDTO mapUsuarioToLeerUsuarioDTO(Usuario usuario) {
        leerUsuarioDTO dto = new leerUsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setPNombre(usuario.getPNombre());
        dto.setSNombre(usuario.getSNombre());
        dto.setPApellido(usuario.getPApellido());
        dto.setSApellido(usuario.getSApellido());
        dto.setCorreoElec(usuario.getCorreoElec());
        dto.setRut(usuario.getRut());
        dto.setRutDv(usuario.getRutDv());
        dto.setNumeroTelef(usuario.getNumeroTelef());
        dto.setFoto(usuario.getFoto());
        dto.setValoracion(usuario.getValoracion());
        dto.setFechaCreacion(usuario.getFechaCreacion());

        
        if (usuario.getSexo() != null) dto.setNombreSexo(usuario.getSexo().getNombreSexo());
        if (usuario.getTipoUsuario() != null) dto.setNombreTipoUsu(usuario.getTipoUsuario().getNombreRol());
        if (usuario.getRegion() != null) dto.setNombreRegion(usuario.getRegion().getNombreRegion());
        if (usuario.getComuna() != null) dto.setNombreComuna(usuario.getComuna().getNombreComuna());
        if (usuario.getOficio() != null) dto.setNombreOficio(usuario.getOficio().getNombreOficio());

        return dto;
    }

    private Usuario mapCrearUsuarioDTOToUsuario(crearUsuarioDTO dto) {
        Usuario usuario = new Usuario();

        // Mapeo de datos personales, con "N" como valor por defecto para strings vacíos o nulos.
        usuario.setPNombre(defaultIfBlank(dto.getPNombre(), "N"));
        usuario.setSNombre(defaultIfBlank(dto.getSNombre(), "N"));
        usuario.setPApellido(defaultIfBlank(dto.getPApellido(), "N"));
        usuario.setSApellido(defaultIfBlank(dto.getSApellido(), "N"));

        // Mapeo de credenciales y datos de identificación.
        usuario.setCorreoElec(dto.getCorreoElec());
        usuario.setPassword(dto.getPassword()); // La contraseña DEBE ser hasheada en el servicio.
        usuario.setRut(defaultIfBlank(dto.getRut(), "N"));
        usuario.setRutDv(defaultIfBlank(dto.getRutDv(), "N"));
        usuario.setNumeroTelef(defaultIfBlank(dto.getNumeroTelef(), "N"));

        // Mapeo de relaciones (IDs a Entidades).
        // Si el ID es null, la relación no se establece.
        // Si el ID no es null pero no se encuentra la entidad, se lanza una excepción para detener la creación.
        if (dto.getIdSexoUsu() != null) {
            SexoUsuario sexo = sexoRepository.findById(dto.getIdSexoUsu())
                    .orElseThrow(() -> new RuntimeException("Sexo no encontrado con id: " + dto.getIdSexoUsu()));
            usuario.setSexo(sexo);
        }
        if (dto.getIdTipoUsu() != null) {
            TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(dto.getIdTipoUsu())
                    .orElseThrow(() -> new RuntimeException("Tipo de Usuario no encontrado con id: " + dto.getIdTipoUsu()));
            usuario.setTipoUsuario(tipoUsuario);
        }
        if (dto.getIdRegionUsu() != null) {
            Region region = regionRepository.findById(dto.getIdRegionUsu())
                    .orElseThrow(() -> new RuntimeException("Región no encontrada con id: " + dto.getIdRegionUsu()));
            usuario.setRegion(region);
        }
        if (dto.getIdComunaUsu() != null) {
            Comuna comuna = comunaRepository.findById(dto.getIdComunaUsu())
                    .orElseThrow(() -> new RuntimeException("Comuna no encontrada con id: " + dto.getIdComunaUsu()));
            usuario.setComuna(comuna);
        }
        if (dto.getIdOficio() != null) {
            Oficio oficio = oficioRepository.findById(dto.getIdOficio())
                    .orElseThrow(() -> new RuntimeException("Oficio no encontrado con id: " + dto.getIdOficio()));
            usuario.setOficio(oficio);
        }
        return usuario;
    }

    private String defaultIfBlank(String input, String defaultValue) {
        return (input == null || input.trim().isEmpty()) ? defaultValue : input;
    }
}
