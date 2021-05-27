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
import controller.PagoController;
import java.util.Date;
import model.Pago;
import model.Resumen;
import model.ResumenAgrupado;

public class reporteResumens implements JRDataSource{
    JugadaController jugadaController = new JugadaController();
    BoletaController boletaController = new BoletaController();
    PagoController pagoController = new PagoController();
    private int id = 0;
    private int aux = 0;
    private boolean linea = true;
    private boolean ultima = true;
    private Boleta boleta;

    public reporteResumens() {
    }
    
   ArrayList<ResumenAgrupado> jugadas = new ArrayList();
 
    private int index = -1; 

      public reporteResumens(ArrayList<ResumenAgrupado> jugadas){
          this.jugadas = jugadas;
      }
    
     public ArrayList<ResumenAgrupado> getjugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<ResumenAgrupado> jugadas) {
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
      String fieldName = jrf.getName();
      
        switch(fieldName){
             
            case "Numero":
                 value = jugadas.get(index).getNumero(); 
            break;
            
            case "Cantidad":
                value = jugadas.get(index).getCantidad();                
            break;
            
            case "Monto":
                value = jugadas.get(index).getTotal();                
            break;
            
            case "Turno":
                value = jugadas.get(index).getTurno();                
            break;
            
             case "Quiniela":
                value = jugadas.get(index).getQuinielas();                
            break;

            case "Pagado":
                value = jugadas.get(index).getTotal()*4;                      
            break;
            
        }

        
        return value;
    }
 
    
    public static JRDataSource getDataSource(ArrayList<ResumenAgrupado> jugadas){
        System.out.println("entro a getDataSource  Resumenes");
        return new reporteResumens(jugadas);
    }

    
}
