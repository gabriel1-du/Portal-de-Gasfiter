import { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem('token') || null);
    const [usuario, setUsuario] = useState(null);

    // Función para iniciar sesión
    const iniciarSesion = (nuevoToken, datosUsuario) => {
        setToken(nuevoToken);
        setUsuario(datosUsuario);
        localStorage.setItem('token', nuevoToken); // Se queda guardado aunque cierres el navegador
    };

    // Función para cerrar sesión
    const cerrarSesion = () => {
        setToken(null);
        setUsuario(null);
        localStorage.removeItem('token');
    };

    return (
        <AuthContext.Provider value={{ token, usuario, iniciarSesion, cerrarSesion }}>
            {children}
        </AuthContext.Provider>
    );
};