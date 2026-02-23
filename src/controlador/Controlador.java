package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import excepciones.InvalidDni;
import modelo.Alumno;

public class Controlador {
    List<Alumno> listaAlumnos = new LinkedList<>();
    File fichero = new File("src/alumnos.txt");

    public Controlador() {
        Alumno Marcos;
        try {
            Marcos = new Alumno("Marcos", "32954840A", "2006-07-05", "Avenida Descartes", "Jerez", "11405", "WEB");
            añadirAlumno(Marcos);
            
            cargarDesdeFichero(fichero);
            System.out.println("Fichero cargado.");

        } catch (InvalidDni e) {
            e.printStackTrace();
        }

        guardarEnFichero(fichero);
        System.out.println("Fichero guardado.");

    }

    public void añadirAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }

    private void guardarEnFichero(File fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            for (Alumno alumno : listaAlumnos) {
                bw.write(alumno.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cargarDesdeFichero(File fichero) throws InvalidDni {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                listaAlumnos.add(new Alumno(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]));

            }
            /*
             * for (Alumno string : listaAlumnos) {
             * System.out.println(string);
             * }
             */
        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
        }

    }
}
