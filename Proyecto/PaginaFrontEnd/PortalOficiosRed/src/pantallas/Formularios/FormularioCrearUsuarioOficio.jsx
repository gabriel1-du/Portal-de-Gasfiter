import React, { useState } from 'react';
import { crearUsuarioOficio } from '../../servicios/usuariosService';

function FormularioCrearUsuarioOficio() {
  // Estilos en línea para el formulario
  const formStyle = {
    display: 'flex',
    flexDirection: 'column',
    maxWidth: '450px',
    margin: '20px auto',
    padding: '20px',
    border: '1px solid #ccc',
    borderRadius: '8px',
    backgroundColor: '#f9f9f9',
  };

  const labelStyle = {
    marginBottom: '5px',
    marginTop: '10px',
    fontWeight: 'bold',
    textAlign: 'left',
  };

  const inputStyle = {
    padding: '8px',
    marginBottom: '10px',
    border: '1px solid #ddd',
    borderRadius: '4px',
  };

  const buttonStyle = {
    padding: '10px 15px',
    backgroundColor: 'orange',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    marginTop: '20px',
    fontSize: '16px',
  };

  // Estado para los campos del formulario basados en crearUsuarioLVL2DTO
  const [formData, setFormData] = useState({
    primerNombre: '',
    segundoNombre: '',
    primerApellido: '',
    segundoApellido: '',
    idSexoUsu: '',
    correoElec: '',
    password: '',
    rut: '',
    rutDv: '',
    numeroTelef: '',
    idTipoUsu: '',
    foto: '',
    idRegionUsu: '',
    idComunaUsu: '',
    idOficio: '',
  });

  const [mensaje, setMensaje] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje('');

    // Construye el objeto de datos para enviar a la API
    const datosParaEnviar = {
      primerNombre: formData.primerNombre,
      segundoNombre: formData.segundoNombre || null,
      primerApellido: formData.primerApellido,
      segundoApellido: formData.segundoApellido || null,
      idSexoUsu: parseInt(formData.idSexoUsu),
      correoElec: formData.correoElec,
      password: formData.password,
      rut: formData.rut,
      rutDv: formData.rutDv,
      numeroTelef: formData.numeroTelef,
      idTipoUsu: parseInt(formData.idTipoUsu),
      foto: formData.foto || null,
      valoracion: null, // La valoración inicial la gestiona el backend
      idRegionUsu: formData.idRegionUsu ? parseInt(formData.idRegionUsu) : null,
      idComunaUsu: formData.idComunaUsu ? parseInt(formData.idComunaUsu) : null,
      idOficio: formData.idOficio ? parseInt(formData.idOficio) : null,
    };

    try {
      const response = await crearUsuarioOficio(datosParaEnviar);
      if (response) {
        setMensaje('¡Usuario de oficio creado exitosamente!');
        // Limpiar formulario (opcional)
        setFormData({
            primerNombre: '', segundoNombre: '', primerApellido: '', segundoApellido: '',
            idSexoUsu: '', correoElec: '', password: '', rut: '', rutDv: '',
            numeroTelef: '', idTipoUsu: '', foto: '', idRegionUsu: '',
            idComunaUsu: '', idOficio: '',
        });
      } else {
        setMensaje('Error al crear el usuario. Revisa los datos e inténtalo de nuevo.');
      }
    } catch (error) {
      console.error("Error en el envío del formulario de oficio:", error);
      setMensaje('Ocurrió un error inesperado. Por favor, inténtalo más tarde.');
    }
  };

  return (
    <div style={{ textAlign: 'center' }}>
      <h1>Crear Cuenta de Profesional</h1>
      <p>Completa tus datos para ofrecer tus servicios en nuestra plataforma.</p>
      {mensaje && <p>{mensaje}</p>}
      <form style={formStyle} onSubmit={handleSubmit}>
        <label style={labelStyle}>Primer Nombre:</label>
        <input type="text" name="primerNombre" value={formData.primerNombre} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>Segundo Nombre:</label>
        <input type="text" name="segundoNombre" value={formData.segundoNombre} onChange={handleChange} style={inputStyle} />
        <label style={labelStyle}>Primer Apellido:</label>
        <input type="text" name="primerApellido" value={formData.primerApellido} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>Segundo Apellido:</label>
        <input type="text" name="segundoApellido" value={formData.segundoApellido} onChange={handleChange} style={inputStyle} />
        <label style={labelStyle}>RUT:</label>
        <input type="text" name="rut" value={formData.rut} onChange={handleChange} style={inputStyle} required placeholder="Ej: 12345678" />
        <label style={labelStyle}>Dígito Verificador:</label>
        <input type="text" name="rutDv" value={formData.rutDv} onChange={handleChange} style={inputStyle} required maxLength="1" placeholder="Ej: K" />
        <label style={labelStyle}>Correo Electrónico:</label>
        <input type="email" name="correoElec" value={formData.correoElec} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>Contraseña:</label>
        <input type="password" name="password" value={formData.password} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>Número de Teléfono:</label>
        <input type="tel" name="numeroTelef" value={formData.numeroTelef} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>URL de la Foto:</label>
        <input type="text" name="foto" value={formData.foto} onChange={handleChange} style={inputStyle} placeholder="https://ejemplo.com/foto.jpg" />
        <label style={labelStyle}>ID Sexo:</label>
        <input type="number" name="idSexoUsu" value={formData.idSexoUsu} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>ID Tipo de Usuario:</label>
        <input type="number" name="idTipoUsu" value={formData.idTipoUsu} onChange={handleChange} style={inputStyle} required placeholder="ID para Profesional" />
        <label style={labelStyle}>ID Oficio:</label>
        <input type="number" name="idOficio" value={formData.idOficio} onChange={handleChange} style={inputStyle} required />
        <label style={labelStyle}>ID Región (Opcional):</label>
        <input type="number" name="idRegionUsu" value={formData.idRegionUsu} onChange={handleChange} style={inputStyle} />
        <label style={labelStyle}>ID Comuna (Opcional):</label>
        <input type="number" name="idComunaUsu" value={formData.idComunaUsu} onChange={handleChange} style={inputStyle} />
        <button type="submit" style={buttonStyle}>Registrarse como Profesional</button>
      </form>
    </div>
  );
}

export default FormularioCrearUsuarioOficio;