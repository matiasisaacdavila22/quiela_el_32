/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.BoletaController;
import model.Jugada;
import controller.JugadaController;
import java.util.concurrent.ThreadLocalRandom;
import model.Boleta;

/**
 *
 * @author holasur
 */
public class herramietasTest {

    public String CrearNombre() {

        String[] nombres = {"Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
            "Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
            "Caritina", "Carlota", "Baltazar"};
        String[] apellidos = {"Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
            "Carion", "Castiyo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
            "Grigalva"};

        String nombresAleatorios = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " "
                + apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
        System.out.println("el nombre aleatorio es :" + nombresAleatorios);

        return nombresAleatorios;

    }

    public String CrearTurno() {
        String[] turnos = {"11", "14", "17", "21"};
        String turnoAleatorio = turnos[(int) (Math.floor(Math.random() * ((turnos.length - 1) - 0 + 1) + 0))];
        System.out.println("el turno aleatorio es :" + turnoAleatorio);
        return turnoAleatorio;
    }

    public String CrearQuiniela() {
        String[] quinielas = {"N", "P", "C", "E", "F", "O"};
        String quinielaAleatoria = quinielas[(int) (Math.floor(Math.random() * ((quinielas.length - 1) - 0 + 1) + 0))];
        System.out.println(" la quiniela aleatoria es  : " + quinielaAleatoria);
        return quinielaAleatoria;
    }

    public String CrearNumero() {
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 99 + 1);
        System.out.println("el numero aleatorio es : " + numeroAleatorio);
        return "" + numeroAleatorio;
    }

    public int CrearPosicion() {
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        System.out.println("el pposicion aleatorio es : " + numeroAleatorio);
        return numeroAleatorio;
    }

    public int monto() {
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        System.out.println("el monto aleatorio es : " + numeroAleatorio);
        return numeroAleatorio;
    }

    public void crearJugadas(int boletas) {
        JugadaController jugadaController = new JugadaController();
        BoletaController boletaController = new BoletaController();

        for (int j = 0; j < boletas; j++) {
            String name = this.CrearNombre();
            int montoBoleta = this.monto();
            boolean state = false;
            Boleta boleta = new Boleta(name, montoBoleta);
            boletaController.insertar(boleta);

            for (int i = 0; i < 10; i++) {

                String turno = this.CrearTurno();
                String quiniela = this.CrearQuiniela();
                String numero = this.CrearNumero();
                int posicion = this.CrearPosicion();
                int monto = this.monto();
                int cifras = numero.length();

                Jugada jugada = new Jugada(1, numero, cifras, 1, monto, quiniela, turno, false);
                jugadaController.insertar(jugada);

            }
        }

    }

}
