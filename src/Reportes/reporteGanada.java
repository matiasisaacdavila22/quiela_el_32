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

public class reporteGanada implements JRDataSource{
    JugadaController jugadaController = new JugadaController();
    BoletaController boletaController = new BoletaController();
    PagoController pagoController = new PagoController();
    private int id = 0;
    private int aux = 0;
    private boolean linea = true;
    private boolean ultima = true;
    private Boleta boleta;

    public reporteGanada() {
    }
    
    ArrayList<Jugada> jugadas = new ArrayList();
 
    private int index = -1; 

      public reporteGanada(ArrayList<Jugada>jugadas){
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
        if(index < jugadas.size()){
 
        if(index == 0){
            id = jugadas.get(index).getIdBoleta();
         }else{
            aux = id;
            id = jugadas.get(index).getIdBoleta();
            linea = id == aux;  
            if(!linea){
                index--;

            return index < jugadas.size(); 
            }
         }
          return index < jugadas.size();   
        }else if(this.ultima){
            linea = false;
            this.ultima = false;
            index--;
            return index < jugadas.size();
        }
        return index < jugadas.size();
     }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object value = null;

             this.boleta = boletaController.consultarBoletaById(jugadas.get(index).getIdBoleta());
                Pago pago = pagoController.buscarPagoIdBoleta(boleta.getId());
          
            if(pago != null){
                  System.out.println("el pago es :"+pago.getMonto());
                Date fecha = pago.getFechaPago();
                this.boleta.setPagado(true);
                this.boleta.setFechaPago((java.sql.Date) fecha);
                this.boleta.setTotalPago(pago.getMonto());
                System.out.println("la boleta tiene un pago se :"+boleta.getTotalPago());
            }
        
        String fieldName = jrf.getName();
 
           if(!linea){
               System.out.println(linea);
               System.out.println("la boleta esta pagada : "+this.boleta.isPagado());
           switch(fieldName){
            
            case "Id":
                value = jugadas.get(index).idBoleta;
            break;
            
            case "Nombre":
                 value = " ";
            break;
            
            case "Quiniela":
                value = " ";                
            break;
            
            case "Turno":
                value = " ";                
            break;
            
            case "Numero":
                value = " ";                
            break;
            
             case "Posicion":
                value = " ";                
            break;
            
           case "Monto":
                value = "TOTAL";                
            break;
            
            case "Linea":
                if(boleta.isPagado()){
                value = boleta.getTotalPago();   
                }else{
                    value = pagoController.calcularPago2(boleta);
                }
            break;
            
            case "Pagado":
                if(boleta.isPagado()){
                      value = boleta.getFechaPago(); 
                }else{
                    value = "--/--/--";
                }
                             
            break;
            
        }
              
        return value;
        
             }else if(linea){
               System.out.println(linea);
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
                
                value = jugadaController.calcularPremio(jugadas.get(index));   
              
            break;
            
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
           return value;
    }
 
    
    public static JRDataSource getDataSource(ArrayList<Jugada> jugadas){
        System.out.println("entro a getDataSource  Ganadas");
        return new reporteGanada(jugadas);
    }

    
}
