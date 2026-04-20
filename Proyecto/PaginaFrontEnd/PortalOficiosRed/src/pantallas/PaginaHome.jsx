import React from 'react';
import '../style/home.css';
import PublicacionCard from '../assets/PublicacionesCard.jsx'; // 1. Importamos el nuevo componente
import BarraBusqueda from '../assets/barraBusqueda.jsx';

function PaginaHome() {
  // 2. Datos de ejemplo para la publicación
  const publicacionEjemplo = {
      "idPublicacion": 2,
      "idAutor": 3,
      "tituloPublicacion": "Guitarra Eléctrica Fender Stratocaster",
      "idRegion": 1,
      "nombreRegion": "Metropolitana",
      "idComuna": 1,
      "nombreComuna": "Puente Alto",
      "ubicacionPublicacion": "Santiago Centro, cerca de Metro U. de Chile",
      "descripcionPublicacion": "Vendo guitarra en excelente estado, poco uso. Incluye funda y correa. Ideal para rock y blues.",
      "cantidadLikes": 15
  };

  return (
    <div>
      {/* Navbar Personalizado */}
      <BarraBusqueda />

      {/* Contenedor principal */}
      <div className="container mt-4 mi-pagina-contenedor">
        <h3>Contenido Principal</h3>
        <p>Aquí se listarán las tarjetas o resultados de tu aplicación. A continuación un ejemplo:</p>

        {/* 3. Aquí usamos el nuevo componente con los datos de ejemplo */}
        <PublicacionCard publicacion={publicacionEjemplo} />
      </div>
    </div>
  );
}

export default PaginaHome;