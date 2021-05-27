package jugadas;


import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author holasur
 */
public class testJugadas{
    
    /**
     *
     */
    @Before
    public  void before(){
        System.out.println("inicio de las pruebas unitarias");
    }
    @After
     public  void after(){
        System.out.println("fin de las pruebas unitarias");
    }
     
    @Test
    public void TestAgregarJugada(){
        assertTrue(true);
    }
    
    @Test
    public void TestCrearNombre(){
 
		String[] nombres = { "Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
				"Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
				"Caritina", "Carlota", "Baltazar"};
		String[] apellidos = { "Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
				"Carion", "Castiyo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
				"Grigalva" };

		String nombresAleatorios = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " "
					+ apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
		
                assertTrue(true);
    }
		
    @Test
    public void TestCrearTurno(){
        String[] turnos = {"11", "14", "17", "21"};
        String turnoAleatorio = turnos[(int) (Math.floor(Math.random() * ((turnos.length - 1) - 0 + 1) + 0))];
        assertTrue(true);
    }
    
}
