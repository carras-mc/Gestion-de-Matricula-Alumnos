package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import excepciones.InvalidDni;
import modelo.Alumno;

public class Controlador {
    List<Alumno> listaAlumnos = new LinkedList<>();
    File fichero = new File("src/alumnos.txt");
    Scanner sc = new Scanner(System.in);

    public Controlador() {

        boolean salir = false;
        cargarDesdeFichero(fichero);
        while (!salir) {
            System.out.println("========= GESTIÓN DE ALUMNOS =========");
            System.out.println("1. Añadir nuevo alumno");
            System.out.println("2. Alumnos por especialidad (orden alfabético)");
            System.out.println("3. Alumnos por especialidad (orden por edad)");
            System.out.println("4. Número de alumnos por especialidad");
            System.out.println("5. Alumno más joven y más mayor");
            System.out.println("6. Listado completo");
            System.out.println("0. Salir");
            System.out.println("======================================");
            System.out.print("Introduce la acción que desea realizar (0-6) : ");
            int opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            try {

                switch (opcion) {
                    case 0:

                        salir = true;
                        guardarEnFichero(fichero);
                        break;

                    case 1:

                        añadirAlumno();
                        break;

                    case 2:

                        alumnosPorEspecialidadAlfabeticamente();
                        break;

                    case 3:

                        alumnosPorEspecialidadEdad();
                        break;

                    case 4:

                        numeroAlumnosPorEspecialidad();
                        break;

                    case 5:

                        alumnoJovenMayor();
                        break;

                    case 6:

                        listadoCompleto();
                        break;

                    default:
                        break;
                }

            } catch (InvalidDni | DateTimeParseException e ) {
                System.out.println(e.getMessage());
            }

            System.out.println("\nPULSE ENTER PARA CONTINUAR...");
            sc.nextLine();
        }

    }

    private void alumnoJovenMayor() {
        
        List<Alumno> copiaLista = new LinkedList<>(listaAlumnos);

        copiaLista.sort(Comparator.comparing(Alumno::getFechaNacimiento));

        System.out.println("Alumno más joven : "+copiaLista.getFirst());
        System.out.println("Alumno más mayor : "+copiaLista.getLast());        
    }

    private void numeroAlumnosPorEspecialidad() {

        int img = 0;
        int web = 0;
        int coc = 0;

        for (Alumno alumno : listaAlumnos) {

            if (alumno.getCurso().equals("COC") ) {
                coc++;
            }
            if (alumno.getCurso().equals("WEB")) {
                web++;
            }
            if (alumno.getCurso().equals("IMG")) {
                img++;
            }
        }
        System.out.println("Cocina : " + coc + " alumnos matriculados");
        System.out.println("Web : " + web + " alumnos matriculados");
        System.out.println("Imagenes : " + img + " alumnos matriculados");
    }

    private void listadoCompleto() {
        for (Alumno alumno : listaAlumnos) {
            System.out.println(alumno);
        }
    }

    private void alumnosPorEspecialidadEdad() {

        List<Alumno> copiaLista = new LinkedList<>(listaAlumnos);

        copiaLista.sort(
                Comparator.comparing(Alumno::getCurso)
                        .thenComparing(Alumno::getFechaNacimiento));

        for (Alumno alumno : copiaLista) {
            System.out.println(alumno);
        }
    }

    private void alumnosPorEspecialidadAlfabeticamente() {

        List<Alumno> copiaLista = new LinkedList<>(listaAlumnos);

        copiaLista.sort(
                Comparator.comparing(Alumno::getCurso)
                        .thenComparing(Alumno::getNombre));

        for (Alumno alumno : copiaLista) {
            System.out.println(alumno);
        }
    }

    public void añadirAlumno() throws InvalidDni {
        System.out.print("Nombre : ");
        String nombre = sc.nextLine();
        System.out.println();
        System.out.print("DNI : ");
        String dni = sc.nextLine();
        System.out.println();
        System.out.print("Fecha nacimiento (YYYY-MM-DD) : ");
        String fechaNacimiento = sc.nextLine();
        System.out.println();
        System.out.print("Calle : ");
        String calle = sc.nextLine();
        System.out.println();
        System.out.print("Poblacion : ");
        String poblacion = sc.nextLine();
        System.out.println();
        System.out.print("Codigo postal : ");
        String cp = sc.nextLine();
        System.out.println();
        System.out.print("Curso (COC, WEB, IMG) : ");
        String curso = sc.nextLine().toUpperCase();
        System.out.println();

        Alumno alumno = new Alumno(nombre, dni, fechaNacimiento, calle, poblacion, cp, curso);
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
        System.out.println("Fichero guardado.");

    }

    public void cargarDesdeFichero(File fichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                listaAlumnos.add(new Alumno(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]));

            }

            System.out.println("Fichero cargado.");
        } catch (IOException | DateTimeParseException | InvalidDni e) {
            System.out.println(e.getMessage());
        }

    }
}