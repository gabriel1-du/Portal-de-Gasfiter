import React from 'react';
import { useState } from 'react';
import { crearUsuarioCliente } from '../../servicios/usuariosService'; // Importa la función del servicio

import '../../style/formulacioCreacionUsuario.css'; // Importa el nuevo archivo CSS

function FormularioCrearUsuarioCliente() {
  // Estado para cada campo del formulario
  const [formData, setFormData] = useState({
    primerNombre: '',
    segundoNombre: '',
    primerApellido: '',
    segundoApellido: '',
    idSexoUsu: '',
    foto: '',
    correoElec: '',
    password: '',
    numeroTelef: '',
    idTipoUsu: '',
    idRegionUsu: '',
    idComunaUsu: '',
  });

  const [mensaje, setMensaje] = useState('');

  // Función para manejar los cambios en los inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  // Función para manejar el envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault(); // Previene el comportamiento por defecto del formulario
    setMensaje(''); // Limpia mensajes anteriores

    // Construye el objeto con los datos del formulario, asegurando los tipos correctos
    const datosParaEnviar = {
      primerNombre: formData.primerNombre,
      segundoNombre: formData.segundoNombre || null, // Opcional
      primerApellido: formData.primerApellido,
      segundoApellido: formData.segundoApellido || null, // Opcional
      idSexoUsu: parseInt(formData.idSexoUsu),
      foto: formData.foto || null, // Opcional
      correoElec: formData.correoElec,
      password: formData.password,
      numeroTelef: formData.numeroTelef,
      idTipoUsu: parseInt(formData.idTipoUsu),
      idRegionUsu: formData.idRegionUsu ? parseInt(formData.idRegionUsu) : null, // Opcional
      idComunaUsu: formData.idComunaUsu ? parseInt(formData.idComunaUsu) : null, // Opcional
    };

    try {
      const response = await crearUsuarioCliente(datosParaEnviar);
      if (response) {
        setMensaje('¡Usuario creado exitosamente!');
        // Opcional: Limpiar el formulario después del éxito
        setFormData({
          primerNombre: '', segundoNombre: '', primerApellido: '', segundoApellido: '',
          idSexoUsu: '', foto: '', correoElec: '', password: '', numeroTelef: '',
          idTipoUsu: '', idRegionUsu: '', idComunaUsu: '',
        });
      } else {
        setMensaje('Error al crear el usuario. Por favor, inténtalo de nuevo.');
      }
    } catch (error) {
      console.error("Error en el envío del formulario:", error);
      setMensaje('Ocurrió un error inesperado. Por favor, inténtalo más tarde.');
    }
  };

  return (
    <div style={{ textAlign: 'center' }}>
      <h1>Crear Cuenta de Usuario</h1>
      <p>Por favor, completa los siguientes datos para crear tu cuenta.</p>
      {mensaje && <p>{mensaje}</p>} {/* Muestra el mensaje de estado */}
      <form className="form-container" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="primerNombre">Primer Nombre:</label>
          <input type="text" id="primerNombre" name="primerNombre" value={formData.primerNombre} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="segundoNombre">Segundo Nombre:</label>
          <input type="text" id="segundoNombre" name="segundoNombre" value={formData.segundoNombre} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="primerApellido">Primer Apellido:</label>
          <input type="text" id="primerApellido" name="primerApellido" value={formData.primerApellido} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="segundoApellido">Segundo Apellido:</label>
          <input type="text" id="segundoApellido" name="segundoApellido" value={formData.segundoApellido} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="idSexoUsu">ID Sexo:</label>
          <input type="number" id="idSexoUsu" name="idSexoUsu" value={formData.idSexoUsu} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="foto">URL de la Foto:</label>
          <input type="text" id="foto" name="foto" value={formData.foto} onChange={handleChange} placeholder="https://ejemplo.com/tu-foto.jpg" />
        </div>
        <div className="form-group">
          <label htmlFor="correoElec">Correo Electrónico:</label>
          <input type="email" id="correoElec" name="correoElec" value={formData.correoElec} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="password">Contraseña:</label>
          <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="numeroTelef">Número de Teléfono:</label>
          <input type="tel" id="numeroTelef" name="numeroTelef" value={formData.numeroTelef} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="idTipoUsu">ID Tipo de Usuario:</label>
          <input type="number" id="idTipoUsu" name="idTipoUsu" value={formData.idTipoUsu} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="idRegionUsu">ID Región (Opcional):</label>
          <input type="number" id="idRegionUsu" name="idRegionUsu" value={formData.idRegionUsu} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="idComunaUsu">ID Comuna (Opcional):</label>
          <input type="number" id="idComunaUsu" name="idComunaUsu" value={formData.idComunaUsu} onChange={handleChange} />
        </div>
        <button type="submit" className="form-submit-button">Registrarse</button>
      </form>
    </div>
  );
}

export default FormularioCrearUsuarioCliente;