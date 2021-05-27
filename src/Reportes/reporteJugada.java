/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;
 
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Boleta;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import model.Jugada; 
import controller.JugadaController;
import controller.BoletaController;

public class reporteJugada implements JRDataSource{
    JugadaController jugadaController = new JugadaController();
    BoletaController boletaController = new BoletaController();
    ArrayList<Jugada> jugadas = new ArrayList();
    private int index = -1; 

      public reporteJugada(ArrayList<Jugada>jugadas){
          this.jugadas = jugadas;
      }
    
     public ArrayList<Jugada> getjugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }


    @Override
    public boolean next() throws JRException {
        this.index++;
        return index < jugadas.size();
     }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object value = null;
        Boleta boleta = boletaController.consultarBoletaById(jugadas.get(index).getIdBoleta());
        String fieldName = jrf.getName();
      
        switch(fieldName){
            
            case "Id":
                value = jugadas.get(index).idBoleta;
            break;
            
            case "Nombre":
                 value = boleta.getNombre();
            break;
            
            case "Quiniela":
                value = jugadas.get(index).getQuiniela();                
            break;
            
            case "Turno":
                value = jugadas.get(index).getTurno();                
            break;
            
            case "Numero":
                value = jugadas.get(index).getNumero();                
            break;
            
             case "Posicion":
                value = jugadas.get(index).getPosicion();                
            break;
            
           case "Monto":
                value = jugadas.get(index).getMonto();                
            break;
            
            case "Linea":
                if(jugadas.get(index).isGano()){
                    System.out.println("el numero ganador es el numero : "+jugadas.get(index).getNumero());
                value = jugadaController.premio(jugadas.get(index));
                  break;
                }else{
                    value = 0;
                      break;
                }
          
            
            case "Pagado":
                if(jugadas.get(index).getTotalPago()> 0){
                      value = jugadas.get(index).getFecha(); 
                }else{
                    value = "--/--/--";
                }
                             
            break;
            
        }

        
        return value;

    }
    
 
    
    public static JRDataSource getDataSource(ArrayList<Jugada> jugadas){
        System.out.println("entro a getDataSource  Jugada");
        return new reporteJugada(jugadas);
    }

    
}
