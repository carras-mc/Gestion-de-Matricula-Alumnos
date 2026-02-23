package modelo;

import java.time.LocalDate;

import excepciones.InvalidDni;

public class Persona {
    String nombre;
    String dni;
    LocalDate fechaNacimiento;
    Direccion direccion;
    

    public Persona() {
    }

    public Persona(String nombre, String dni, String fechaNacimiento, String calle, String poblacion, String cp) {
        this.nombre = nombre;
        try {
            this.setDni(dni);
        } catch (InvalidDni e) {
            
            e.printStackTrace();
        }
        this.setFechaNacimiento(fechaNacimiento);
        this.setDireccion(calle, poblacion, cp);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws InvalidDni {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numerosDni = Integer.parseInt(dni.substring(0, 8));
        String letraDni = String.valueOf(dni.charAt(8));
        String letraCorrespondida = String.valueOf(letras.charAt(numerosDni%23));

        if (dni.length() == 9 && letraDni.equals(letraCorrespondida)) {
            this.dni = dni;
        }
        else throw new InvalidDni();
        
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(String calle, String poblacion, String cp) {
        this.direccion = new Direccion(calle, poblacion, cp);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        if (dni == null) {
            if (other.dni != null)
                return false;
        } else if (!dni.equals(other.dni))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nombre +","+  dni +","+ fechaNacimiento +","+ direccion;
    }

    
    

    
}
