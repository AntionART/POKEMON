package com.pokemonapp;

import com.pokemonapp.entities.Entrenador;
import com.pokemonapp.entities.Pokemon;
import com.pokemonapp.entities.Tipo;
import com.pokemonapp.entities.Movimiento;
import java.sql.SQLException;
import java.util.Scanner;

public class AplicacionPokemon {
    private static Scanner scanner = new Scanner(System.in);
    private static final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
    private static PokemonDAO pokemonDAO = new PokemonDAO();
    private static TipoDAO tipoDAO = new TipoDAO();
    private static MovimientoDAO movimientoDAO = new MovimientoDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("________________________________________________________");
            System.out.println("|               Aplicacion Pokemon                     |");
            System.out.println("|_______________________________________________________");
            System.out.println("| 1. Agregar Entrenador                                |");
            System.out.println("| 2. Obtener Entrenador                                |");
            System.out.println("| 3. Agregar Pokemon                                   |");
            System.out.println("| 4. Obtener Pokemon                                   |");
            System.out.println("| 5. Agregar Tipo                                      |");
            System.out.println("| 6. Obtener Tipo                                      |");
            System.out.println("| 7. Agregar Movimiento                                |");
            System.out.println("| 8. Obtener Movimiento                                |");
            System.out.println("| 9. Salir                                             |");
            System.out.println("________________________________________________________");
            System.out.print("Elija una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            try {
                switch (opcion) {
                    case 1:
                        agregarEntrenador();
                        break;
                    case 2:
                        obtenerEntrenador();
                        break;
                    case 3:
                        agregarPokemon();
                        break;
                    case 4:
                        obtenerPokemon();
                        break;
                    case 5:
                        agregarTipo();
                        break;
                    case 6:
                        obtenerTipo();
                        break;
                    case 7:
                        agregarMovimiento();
                        break;
                    case 8:
                        obtenerMovimiento();
                        break;
                    case 9:
                        System.exit(0);
                    default:
                        System.out.println("Opcion no válida. Intente nuevamente.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error de base de datos: " + e.getMessage());
            }
        }
    }

    private static void agregarEntrenador() throws SQLException {
        System.out.print("Ingrese el nombre del entrenador: ");
        String nombre = scanner.nextLine();
        Entrenador entrenador = new Entrenador(0, nombre); // El ID se generará automáticamente
        entrenadorDAO.agregarEntrenador(entrenador);
        System.out.println("Entrenador agregado.");
    }

    private static void obtenerEntrenador() throws SQLException {
        System.out.print("Ingrese el ID del entrenador: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Entrenador entrenador = entrenadorDAO.obtenerEntrenador(id);
        if (entrenador != null) {
            System.out.println("ID del entrenador: " + entrenador.getId() + ", Nombre: " + entrenador.getNombre());
            System.out.println("Pokemon:");
            for (Pokemon pokemon : entrenador.getPokemones()) {
                System.out.println(" - ID del Pokemon: " + pokemon.getId() + ", Nombre: " + pokemon.getNombre());
            }
        } else {
            System.out.println("Entrenador no encontrado.");
        }
    }

    private static void agregarPokemon() throws SQLException {
        System.out.print("Ingrese el nombre del Pokemon: ");
        String nombre = scanner.nextLine();
        Pokemon pokemon = new Pokemon(0, nombre); // El ID se generará automáticamente
        pokemonDAO.agregarPokemon(pokemon);
        System.out.println("Pokemon agregado.");
    }

    private static void obtenerPokemon() throws SQLException {
        System.out.print("Ingrese el ID del Pokemon: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Pokemon pokemon = pokemonDAO.obtenerPokemon(id);
        if (pokemon != null) {
            System.out.println("ID del Pokemon: " + pokemon.getId() + ", Nombre: " + pokemon.getNombre());
            System.out.println("Tipos:");
            for (Tipo tipo : pokemon.getTipos()) {
                System.out.println(" - ID del Tipo: " + tipo.getId() + ", Nombre: " + tipo.getNombre());
            }
            System.out.println("Movimientos:");
            for (Movimiento movimiento : pokemon.getMovimientos()) {
                System.out.println(" - ID del Movimiento: " + movimiento.getId() + ", Nombre: " + movimiento.getNombre());
            }
        } else {
            System.out.println("Pokemon no encontrado.");
        }
    }

    private static void agregarTipo() throws SQLException {
        System.out.print("Ingrese el nombre del Tipo: ");
        String nombre = scanner.nextLine();
        Tipo tipo = new Tipo(0, nombre); // El ID se generará automáticamente
        tipoDAO.agregarTipo(tipo);
        System.out.println("Tipo agregado.");
    }

    private static void obtenerTipo() throws SQLException {
        System.out.print("Ingrese el ID del Tipo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Tipo tipo = tipoDAO.obtenerTipo(id);
        if (tipo != null) {
            System.out.println("ID del Tipo: " + tipo.getId() + ", Nombre: " + tipo.getNombre());
            System.out.println("Movimientos:");
            for (Movimiento movimiento : tipo.getMovimientos()) {
                System.out.println(" - ID del Movimiento: " + movimiento.getId() + ", Nombre: " + movimiento.getNombre());
            }
        } else {
            System.out.println("Tipo no encontrado.");
        }
    }

     private static void agregarMovimiento() throws SQLException {
        System.out.print("Ingrese el nombre del Movimiento: ");
        String nombre = scanner.nextLine();
        Movimiento movimiento = new Movimiento(0, nombre); // El ID se generará automáticamente
        movimientoDAO.agregarMovimiento(movimiento);
        System.out.println("Movimiento agregado.");
    }

    private static void obtenerMovimiento() throws SQLException {
        System.out.print("Ingrese el ID del Movimiento: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Movimiento movimiento = movimientoDAO.obtenerMovimiento(id);
        if (movimiento != null) {
            System.out.println("ID del Movimiento: " + movimiento.getId() + ", Nombre: " + movimiento.getNombre());
        } else {
            System.out.println("Movimiento no encontrado.");
        }
    }
}