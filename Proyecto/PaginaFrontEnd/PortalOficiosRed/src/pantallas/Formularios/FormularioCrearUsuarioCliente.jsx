import React, { useState, useEffect } from 'react';
import { crearUsuarioCliente } from '../../servicios/usuariosService'; // Importa la función del servicio
import { getAllRegions } from '../../servicios/regionService'; // Importa la función para obtener regiones
import { getAllComunas } from '../../servicios/comunasService'; // Importa la función para obtener comunas
import { getAllSexos } from '../../servicios/sexoService'; // Importa la función para obtener sexos

import '../../style/formulacioCreacionUsuario.css'; // Importa el nuevo archivo CSS

function FormularioCrearUsuarioCliente() {
  const [regiones, setRegiones] = useState([]);
  const [comunas, setComunas] = useState([]);
  const [sexos, setSexos] = useState([]);
  const [comunasFiltradas, setComunasFiltradas] = useState([]);
  const [errors, setErrors] = useState({});
  // Estado para los campos del formulario
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
    idRegionUsu: '',
    idComunaUsu: '',
  });

  useEffect(() => {
    const cargarDatosGeograficos = async () => {
      try {
        const [regionesData, comunasData, sexosData] = await Promise.all([
          getAllRegions(),
          getAllComunas(),
          getAllSexos()
        ]);

        if (Array.isArray(regionesData)) {
          setRegiones(regionesData);
        }
        if (Array.isArray(comunasData)) {
          setComunas(comunasData);
        }
        if (Array.isArray(sexosData)) {
          setSexos(sexosData);
        }
      } catch (error) {
        console.error("Error al cargar datos geográficos:", error);
      }
    };
    cargarDatosGeograficos();
  }, []);

  // Efecto para filtrar las comunas cuando se selecciona una región
  useEffect(() => {
    if (formData.idRegionUsu) {
      const filtradas = comunas.filter(
        (comuna) => comuna.idRegion === parseInt(formData.idRegionUsu)
      );
      setComunasFiltradas(filtradas);
    } else {
      setComunasFiltradas([]); // Limpia las comunas si no hay región seleccionada
    }
  }, [formData.idRegionUsu, comunas]);

  const [mensaje, setMensaje] = useState('');

  // Función para manejar los cambios en los inputs
  const handleChange = (e) => {
    const { name, value, type, files } = e.target;

    if (type === 'file') {
      const file = files[0];
      if (file) {
        // Se crea una URL local para el archivo seleccionado.
        // Esto es temporal hasta que se conecte a un servicio de almacenamiento en la nube.
        setFormData((prevData) => ({
          ...prevData,
          foto: URL.createObjectURL(file),
        }));
      }
    } else if (name === 'idRegionUsu') {
      setFormData((prevData) => ({
        ...prevData,
        idRegionUsu: value,
        idComunaUsu: '', // Resetea la comuna
      }));
    } else {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    }
  };

  // Función para manejar el envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault(); // Previene el comportamiento por defecto del formulario
    setMensaje(''); // Limpia mensajes anteriores
    setErrors({}); // Limpia errores anteriores

    // Validaciones
    const newErrors = {};
    if (!/\S+@\S+\.\S+/.test(formData.correoElec)) {
      newErrors.correoElec = 'El formato del correo electrónico no es válido.';
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      setMensaje('Por favor, corrige los errores en el formulario.');
      return;
    }

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
      idTipoUsu: 1, // Se asigna automáticamente el ID 1 para clientes
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
          idRegionUsu: '', idComunaUsu: '',
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
      <p>Completa los siguientes datos para crear tu cuenta.</p>
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
          <label htmlFor="idSexoUsu">Sexo:</label>
          <select id="idSexoUsu" name="idSexoUsu" value={formData.idSexoUsu} onChange={handleChange} required>
            <option value="">Selecciona un sexo</option>
            {sexos.map((sexo) => (
              <option key={sexo.idSexo} value={sexo.idSexo}>
                {sexo.nombreSexo}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="foto">URL de la Foto:</label>
          <input type="file" id="foto" name="foto" onChange={handleChange} accept="image/*" />
        </div>
        <div className="form-group">
          <label htmlFor="correoElec">Correo Electrónico:</label>
          <input type="email" id="correoElec" name="correoElec" value={formData.correoElec} onChange={handleChange} required />
          {errors.correoElec && <span className="error-message">{errors.correoElec}</span>}
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
          <label htmlFor="idRegionUsu">Región:</label>
          <select id="idRegionUsu" name="idRegionUsu" value={formData.idRegionUsu} onChange={handleChange} required>
            <option value="">Selecciona una región</option>
            {regiones.map((region) => (
              <option key={region.idRegion} value={region.idRegion}>
                {region.nombreRegion}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="idComunaUsu">Comuna:</label>
          <select id="idComunaUsu" name="idComunaUsu" value={formData.idComunaUsu} onChange={handleChange} disabled={!formData.idRegionUsu}>
            <option value="">Selecciona una comuna</option>
            {comunasFiltradas.map((comuna) => (
              <option key={comuna.idComuna} value={comuna.idComuna}>
                {comuna.nombreComuna}
              </option>
            ))}
          </select>
        </div>
        <button type="submit" className="form-submit-button">Registrarse</button>
      </form>
    </div>
  );
}

export default FormularioCrearUsuarioCliente;