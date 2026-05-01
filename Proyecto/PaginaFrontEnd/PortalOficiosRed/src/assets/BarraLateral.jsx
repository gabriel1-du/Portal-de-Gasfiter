import React from 'react';
import ReactDOM from 'react-dom';
import '../style/BarraLateral.css';

const BarraLateral = ({ abierta, alCerrar }) => {
  // Si no está abierta, no renderizamos nada.
  if (!abierta) {
    return null;
  }

  // Usamos el portal para renderizar la barra lateral y el fondo en el body.
  return ReactDOM.createPortal(
    <>
      <div className="sidebar-backdrop abierto" onClick={alCerrar}></div>
      <div className="barra-lateral abierta">
        <div className="barra-lateral-header">
          <h3>Menú</h3>
          <button onClick={alCerrar} className="btn-cerrar-sidebar">&times;</button>
        </div>
        <p>Aquí irá el contenido de la barra lateral.</p>
      </div>
    </>,
    document.getElementById('sidebar-root')
  );
};

export default BarraLateral;
