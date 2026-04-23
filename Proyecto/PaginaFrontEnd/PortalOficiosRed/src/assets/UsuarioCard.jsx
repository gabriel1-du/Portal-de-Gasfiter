import React from 'react';
import '../style/UsuarioCard.css'; // Importamos los estilos para la tarjeta

/**
 * Componente para mostrar la tarjeta de un usuario.
 * @param {object} props - Propiedades del componente.
 * @param {object} props.usuario - Objeto con los datos del usuario.
 */
const UsuarioCard = ({ usuario }) => {
  // Desestructuramos los datos que vamos a usar
  const {
    primerNombre,
    primerApellido,
    foto,
    nombreOficio
  } = usuario;

  const nombreCompleto = `${primerNombre} ${primerApellido}`;

  return (
    <div className="usuario-card">
      <img src={foto || 'https://via.placeholder.com/100'} alt={`Foto de ${nombreCompleto}`} className="usuario-card-foto" />
      <div className="usuario-card-info">
        <h4>{nombreCompleto}</h4>
        <p>{nombreOficio || 'Cliente'}</p>
      </div>
    </div>
  );
};

export default UsuarioCard;