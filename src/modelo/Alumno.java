package modelo;

import java.time.LocalDate;

import excepciones.InvalidDni;

public class Alumno extends Persona {

    String curso;

    public Alumno() {
    }

    public Alumno(String nombre, String dni, String fechaNacimiento, String calle, String poblacion,
            String cp, String curso) throws InvalidDni {
        this.setNombre(nombre);
        this.setDni(dni);
        this.setFechaNacimiento(fechaNacimiento);
        this.setDireccion(calle, poblacion, cp);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return getNombre() + "," + getDni() + "," + getFechaNacimiento()
                + "," + getDireccion() + "," + getCurso();
    }

    

}
