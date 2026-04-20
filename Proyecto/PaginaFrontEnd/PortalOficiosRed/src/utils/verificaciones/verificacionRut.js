// Función para validar el RUT chileno
export const validarRut = (rutCompleto) => {
    try {
        if (!rutCompleto || typeof rutCompleto !== 'string') return false;

        // Limpiar y normalizar el RUT, quitando puntos y guiones
        const rutLimpio = rutCompleto.replace(/[^0-9kK]/g, '').toLowerCase();
        if (rutLimpio.length < 2) return false;

        // Separar cuerpo y dígito verificador
        const cuerpo = rutLimpio.slice(0, -1);
        const dv = rutLimpio.slice(-1);

        if (!/^[0-9]+$/.test(cuerpo)) return false;

        let suma = 0;
        let multiplo = 2;

        // Recorrer el cuerpo del RUT de derecha a izquierda
        for (let i = cuerpo.length - 1; i >= 0; i--) {
            suma += parseInt(cuerpo.charAt(i), 10) * multiplo;
            multiplo = multiplo === 7 ? 2 : multiplo + 1;
        }

        const resto = suma % 11;
        const dvCalculado = 11 - resto;

        if (dvCalculado === 11 && dv === '0') return true;
        if (dvCalculado === 10 && dv === 'k') return true;
        if (dvCalculado.toString() === dv) return true;

        return false;
    } catch (e) {
        return false;
    }
};