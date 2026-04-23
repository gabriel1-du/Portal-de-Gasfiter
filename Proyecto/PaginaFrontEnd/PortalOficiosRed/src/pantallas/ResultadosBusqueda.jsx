import React, { useState, useEffect } from 'react';
import BarraBusqueda from '../assets/barraBusqueda.jsx';
import UsuarioCard from '../assets/UsuarioCard.jsx';
import { leerTodosLosUsuarios } from '../servicios/usuariosService.js';
import '../style/ResultadosBusqueda.css'; // Estilos para esta página

function ResultadosBusqueda() {
  const [usuarios, setUsuarios] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const cargarUsuarios = async () => {
      try {
        const data = await leerTodosLosUsuarios();
        if (Array.isArray(data)) {
          setUsuarios(data);
        } else {
          throw new Error(data || 'No se pudo obtener un array de usuarios.');
        }
      } catch (err) {
        console.error("Error al cargar los usuarios:", err);
        setError(err.message);
      }
    };

    cargarUsuarios();
  }, []); // Se ejecuta solo una vez al montar el componente

  return (
    <div>
      <BarraBusqueda />

      <div className="container mt-4">
        <div className="botones-tipo-resultado">
          <button className="btn-tipo activo">Usuarios</button>
          <button className="btn-tipo">Publicaciones</button>
        </div>

        <div className="resultados-contenedor">
          {error && <p className="error-mensaje">Error: {error}</p>}
          
          {!error && usuarios.length > 0 
            ? usuarios.slice(0, 5).map(usuario => (
                <UsuarioCard key={usuario.idUsuario} usuario={usuario} />
              ))
            : (!error && <p>Cargando usuarios o no se encontraron resultados...</p>)
          }
        </div>
      </div>
    </div>
  );
}

export default ResultadosBusqueda;
