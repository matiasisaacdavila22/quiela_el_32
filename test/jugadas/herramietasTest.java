/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jugadas;

import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author holasur
 */
public class herramietasTest {
    
       public String CrearNombre(){
 
		String[] nombres = { "Andrea", "David", "Baldomero", "Balduino", "Baldwin", "Baltasar", "Barry", "Bartolo",
				"Bartolomé", "Baruc", "Baruj", "Candelaria", "Cándida", "Canela", "Caridad", "Carina", "Carisa",
				"Caritina", "Carlota", "Baltazar"};
		String[] apellidos = { "Gomez", "Guerrero", "Cardenas", "Cardiel", "Cardona", "Cardoso", "Cariaga", "Carillo",
				"Carion", "Castiyo", "Castorena", "Castro", "Grande", "Grangenal", "Grano", "Grasia", "Griego",
				"Grigalva" };

         String nombresAleatorios = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " "
         + apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
           System.out.println("el nombre aleatorio es :"+nombresAleatorios);
           
           return nombresAleatorios;
		
    }
		 
    public String CrearTurno(){
        String[] turnos = {"11", "14", "17", "21"};
        String turnoAleatorio = turnos[(int) (Math.floor(Math.random() * ((turnos.length - 1) - 0 + 1) + 0))];
        System.out.println("el turno aleatorio es :"+turnoAleatorio);
        return turnoAleatorio;
      }
    
        public String CrearQuiniela(){
        String[] quinielas = {"N", "P", "C", "E", "F", "O"};
        String quinielaAleatoria = quinielas[(int) (Math.floor(Math.random() * ((quinielas.length - 1) - 0 + 1) + 0))];
        System.out.println(" la quiniela aleatoria es  : "+quinielaAleatoria);
        return quinielaAleatoria;
      }
        
     public String CrearNumero(){
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 9999 + 1);
         System.out.println("el numero aleatorio es : "+numeroAleatorio);
         return ""+numeroAleatorio;
      }  
     
     public String CrearPosicion(){
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 20 + 1);
         System.out.println("el pposicion aleatorio es : "+numeroAleatorio);
         return ""+numeroAleatorio;
      }
     
      public String monto(){
       int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 20 + 1);
         System.out.println("el monto aleatorio es : "+numeroAleatorio);
         return ""+numeroAleatorio;
      }
      
      public void crearJugadas(int jugadas){
           for(int i = 0; i< jugadas; i++){
               String name = this.CrearNombre();
               String turno = this.CrearTurno();
               String quiniela = this.CrearQuiniela();
               String numero = this.CrearNumero();
               String posicion = this.CrearPosicion();
               String monto = this.monto();
               
              
           }
      }
     
}
