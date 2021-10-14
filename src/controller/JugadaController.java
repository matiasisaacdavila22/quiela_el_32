/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.Conexion;
import model.Jugada;
import model.Boleta;
import model.Ganada;
import model.Resumen;
import model.ResumenJugada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
////////////////////////////////////////////////////////////////
import static utils.Config.*;


/**
 *
 * @author mipc
 */
public class JugadaController {
  
  private GanadaController ganadaController;
  private static double matriz[][]={ {7, 70, 600,3500}, {3.5 , 35, 300, 1750}, {2.33, 23.33, 200, 1166},//1-3
                                   {1.75, 17.5, 150, 875},{1.4, 14, 120, 700},{1.16, 11.66, 100, 583},//4-6
                                   {1, 10, 85,71, 500},{0.87, 8.75, 75, 438,5},{0.77, 7.77, 66.66, 388.88},//7-9
                                   {0.7, 7, 60, 350},{0.63, 6.36, 54.54, 318},{0.58, 5.83, 50, 291.66},//10-12
                                   {0.54, 5.38, 46.15, 269.23},{0.50, 5, 42.85, 250},{0.46, 4.66, 33.33, 233.33},//13-15
                                   {0.43, 4.37, 40, 218.75},{0.41, 4.12, 35.29, 205.88},{0.38, 3.88, 33.33, 194.44},//16-18
                                   {0.37, 3.68, 31.58, 184.21},{0.35, 3.5, 30, 175}};
  
 private static double matrizRedoblon[][]={ {1280, 640, 336}, {256, 128, 64}, {64, 32, 16},{1, 1, 1}};



   private Conexion mysql=new Conexion();
   private Connection cn = mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
   public ArrayList<Jugada> generarJugada(ArrayList<Integer> posiciones, ArrayList<String> quinielas, ArrayList<String> turnos, String nombre,int tipo, String numero, int cifras, int monto) {
    
       ArrayList<Jugada> jugadas = new ArrayList();
//conso
        for (String turno : turnos) {
            for (String quiniela: quinielas) {
                 for (Integer posicion : posiciones) {
                   if(!(((turno.equals(primera))||(turno.equals(tercera))) && (quiniela.equals("O")))){
                    Jugada j = new Jugada(tipo, numero, cifras, posicion, monto, quiniela, turno, false);
                    jugadas.add(j);                           
                     }
                }    
            }
        }
       return jugadas;
    } 
   
public ResumenJugada generarResumenJugadas(ArrayList<Integer> posiciones, ArrayList<String> quinielas, ArrayList<String> turnos, String nombre,int tipo, String numero, int cifras, int monto) {
        
        StringBuilder jugada = new StringBuilder();
        jugada.append(numero);
        jugada.append("  ");
           for (String quiniela : quinielas) {
            jugada.append(quiniela);
             }jugada.append("  ");
            for (String turno : turnos) {
                jugada.append(turno);
                jugada.append("/");}
                 jugada.append("  ");
                for (Integer posicion : posiciones) {
                    jugada.append(String.valueOf(posicion));
                      jugada.append("-");      
                  } jugada.append("  ");
                  jugada.append("$");
                  jugada.append(monto);
                ResumenJugada resumen=new ResumenJugada(numero,jugada);
              return resumen;
    }

    public String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatoFecha.format(fecha);
        return fechaFormato;
    }
   public Date fechaSql() {
        Date fecha = new Date();
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        return fechaSql;
    }

    public String hora() {
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormato = formatoHora.format(hora);
        return horaFormato;

    }

    public int contarCifras(String numero) {  
        return numero.length();
    }

    public boolean insertar(Jugada jugada) {
  
       sSQL = "insert into jugada (tipo, numero, cifras, posicion, monto, quiniela, turno,idRedoblona,idBoleta)"
                + "values (?,?,?,?,?,?,?,?,(select idboleta from boleta WHERE idboleta = (SELECT MAX(idboleta)from boleta)))";
        try {
            PreparedStatement pst2 = cn.prepareStatement(sSQL);
            pst2.setInt(1, jugada.getTipo());
            pst2.setString(2, jugada.getNumero());
            pst2.setInt(3, jugada.getCifras());
            pst2.setInt(4, jugada.getPosicion());
            pst2.setInt(5, jugada.getMonto());
            pst2.setString(6, jugada.getQuiniela());
            pst2.setString(7, jugada.getTurno());
            pst2.setInt(8, jugada.getIdRedoblona());
         
            int n = pst2.executeUpdate();
            if (n != 0) {
                 pst2.close();
                return true;
            } else {
                pst2.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
            return false;
        }
    }
    public ArrayList<Jugada> buscarJugadas(int idBoleta) {
        ArrayList<Jugada> jugadas = new ArrayList();
        try {
        
            ResultSet r;
            try (Statement orden = cn.createStatement()) {
                sSQL = "SELECT * from jugada where jugada.idBoleta="+idBoleta;
                r = orden.executeQuery(sSQL);
                while (r.next()) {
                    
                    int idJugada = r.getInt("idJugada");
                    int tipo =r.getInt("tipo");
                    String numero = r.getString("numero");
                    int cifras = r.getInt("cifras");
                    int posicion = r.getInt("posicion");
                    int monto = r.getInt("monto");
                    
                    String quiniela = r.getString("quiniela");
                    String turno = r.getString("turno");
                    
                    Date fecha = r.getDate("fecha");
                    boolean gano = r.getBoolean("gano");
                    int idRedoblona = r.getInt("idRedoblona");
                    
                    
                    Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                    jugadas.add(jugada);
                    
                }
                 orden.close();
            }
            r.close();
          
            return jugadas;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            return jugadas;
        }
    }
//public ObservableList<Jugada> buscarJugadasxId(int idBoleta) {
//         
//        ObservableList<Jugada> j;
//        j = FXCollections.observableArrayList();
//        try {
//           this.con = new Conexion();
//    
//
//            Statement orden = cone2.createStatement();
//            String sSQL = "SELECT * from Jugadas where Jugadas.idBoleta="+idBoleta;
//            ResultSet r = orden.executeQuery(sSQL);
//
//            System.out.println("entrar a la consulta");
//            while (r.next()) {
//
//                int idJugada = r.getInt("idJugada");
//                int tipo =r.getInt("tipo");
//               String numero = r.getString("numero");
//                int cifras = r.getInt("cifras");
//                int posicion = r.getInt("posicion");
//                int monto = r.getInt("monto");
//
//                String quiniela = r.getString("quiniela");
//                String turno = r.getString("turno");
//                String nombre = r.getString("nombre");
//               
//                Date fecha = r.getDate("fecha");
//                int idPago = r.getInt("idPago");
//                String hora = r.getString("hora");
//                boolean gano = r.getBoolean("gano");
//                boolean pago = r.getBoolean("pago");
//                int idRedoblona = r.getInt("idRedoblona");
//                int idGanada = r.getInt("idGanada");
//
//                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, idPago, hora, nombre, gano, pago, idBoleta, idRedoblona, idGanada);
//                j.add(jugada);
//                
//            }
//            orden.close();
//            r.close();
//            return j;
//
//        } catch (SQLException ex) {
//            System.out.println("error :" + ex);
//            return j;
//        }
//    }
//public ArrayList<Jugada> consultaJugadas() {
//        
//      ArrayList<Jugada>j = new ArrayList();
//
//        try {
//            this.con = new Conexion();
//    
//
//            Statement orden = cone2.createStatement();
//            String sSQL = "SELECT Jugadas.*,Boletas.* from Jugadas join Boletas on Jugadas.idBoleta=Boletas.id order by idBoleta desc";
//
//            ResultSet r = orden.executeQuery(sSQL);
//
//            System.out.println("entrar a la consulta");
//            while (r.next()) {
//
//                int idJugada = r.getInt("idJugada");
//                int tipo =r.getInt("tipo");
//               String numero = r.getString("numero");
//                int cifras = r.getInt("cifras");
//                int posicion = r.getInt("posicion");
//                int monto = r.getInt("monto");
//
//                String quiniela = r.getString("quiniela");
//                String turno = r.getString("turno");
//                String nombre = r.getString("nombre");
//                int idBoleta = r.getInt("idBoleta");
//                int id = r.getInt("id");
//                Date fecha = r.getDate("fecha");
//                int idPago = r.getInt("idPago");
//                String hora = r.getString("hora");
//                boolean gano = r.getBoolean("gano");
//                boolean pago = r.getBoolean("pago");
//                int idRedoblona = r.getInt("idRedoblona");
//                int idGanada = r.getInt("idGanada");
//
//                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, idPago, hora, nombre, gano, pago, idBoleta, idRedoblona, idGanada);
//                j.add(jugada);
//              
//            }
//             orden.close();
//            r.close();
//            return j;
//
//        } catch (SQLException ex) {
//            System.out.println("error :" + ex);
//            return null;
//        }
//    }
//public ArrayList<Jugada> consultar(String buscar) {
//        System.out.println("entro a consultar.....");
//        ArrayList<Jugada> j = new ArrayList();
//      //  j = FXCollections.observableArrayList();
//
//        try {
//            this.con = new Conexion();
//       
//
//            Statement orden = cone2.createStatement();
//            String sSQL = "SELECT Jugadas.*,Boletas.* from Jugadas join Boletas on Jugadas.idBoleta=Boletas.id order by idBoleta desc";
//
//            ResultSet r = orden.executeQuery(sSQL);
//
//            System.out.println("entrar a la consulta");
//            while (r.next()) {
//
//                int idJugada = r.getInt("idJugada");
//                int tipo =r.getInt("tipo");
//                String numero = r.getString("numero");
//                int cifras = r.getInt("cifras");
//                int posicion = r.getInt("posicion");
//                int monto = r.getInt("monto");
//
//                String quiniela = r.getString("quiniela");
//                String turno = r.getString("turno");
//                String nombre = r.getString("nombre");
//                int idBoleta = r.getInt("idBoleta");
//                int id = r.getInt("id");
//                Date fecha = r.getDate("fecha");
//                int idPago = r.getInt("idPago");
//                String hora = r.getString("hora");
//                boolean gano = r.getBoolean("gano");
//                boolean pago = r.getBoolean("pago");
//                int idRedoblona = r.getInt("idRedoblona");
//                int idGanada = r.getInt("idGanada");
//
//                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, idPago, hora, nombre, gano, pago, idBoleta, idRedoblona, idGanada);
//                j.add(jugada);
//                System.out.println("creo jugada de forma correcata");
//            }
//             orden.close();
//            r.close();
//            return j;
//
//        } catch (SQLException ex) {
//            System.out.println("error :" + ex);
//            return null;
//        }
//    }
//
    public boolean eliminarJugada(Jugada j) {
       
        String sSQL = "delete from jugada where idJugada=?";
        try {
                  
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, j.getIdJugada());
            int n = pst.executeUpdate();
            if (n != 0) {
                pst.close();
                return true;
            } else {
                pst.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("error : " + e);
            return false;
        }
    } 
       
public boolean eliminarJugada(int idBoleta) {
       
       sSQL = "delete from jugada where idboleta=?";
        try {
     
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, idBoleta);
            int n = pst.executeUpdate();
            if (n != 0) {
                pst.close();
                return true;
            } else {
                pst.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("error : " + e);
            return false;
        }
    }
    
//
//    public int tiempoJugada(Jugada j) {
//        Date fechaInicial = j.getFecha();
//        Date fechaFinal = new Date();
//        int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
//        return dias;
//    }
//
//    public void actualizarXfecha(ObservableList<Jugada> jugadas) {
//        jugadas.stream().filter((n) -> tiempoJugada(n) > 7).forEach((n) -> eliminarJugada(n));
//    }
//
public void actualizarJugadas(Ganada ganada){
    ArrayList<Jugada> jugadas = new ArrayList();
    ArrayList<String> numeros = new ArrayList();
    numeros = this.desarmarNumero(ganada.getNumero());
    for(String numero : numeros){
        jugadas = this.getJugadas(ganada, numero);

    }
    
    
}
    public ArrayList<Jugada> getJugadas(Ganada ganada, String num) {
         ArrayList<Jugada> jugadas = new ArrayList();
    
        try {
             Statement orden=cn.createStatement();
             sSQL = "SELECT * from jugada where jugada.fecha='" + ganada.getFecha() + "' and jugada.quiniela='" + ganada.getQuiniela() + "' and jugada.turno='" + ganada.getTurno() + "' and jugada.numero=" +num+ "' and jugada.posicion >='" + ganada.getPosicion();//\n"
             ResultSet r = orden.executeQuery(sSQL);
             while (r.next()) {

                int idJugada = r.getInt("idJugada");
                int tipo = r.getInt("tipo");
                String numero = r.getString("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                String turno = r.getString("turno");
                boolean gano = r.getBoolean("gano");
                 int idBoleta = r.getInt("idBoleta");
                int idRedoblona = r.getInt("idRedoblona");
                Date fecha = r.getDate("fecha");
 
                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                jugadas.add(jugada);              
            }
            orden.close();
            r.close();
            return jugadas;

        } catch (SQLException ex) {
            System.out.println("error :" + ex);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("informacion");
            alerta.setContentText("FALLOLA BUSQUEDA");
            alerta.showAndWait();
        }
        return null;
    }
//
//    public ArrayList<Jugada> buscameGanadores(ArrayList<Jugada> listaCoinsidencias, Ganada ganada) {
//        listaCoinsidencias.stream().forEach((n)->System.out.println("los numeros son "+n.getNumero()));
//        ArrayList<Jugada> aux = new ArrayList();
//        for (Jugada jugada : listaCoinsidencias) {
//           if(!jugada.isGano()){
//               int premio = jugada.getPosicion();
//                if(jugada.getTipo()==3)premio=20;
//                if(jugada.getTipo()==5)premio=18;
//                if(jugada.getTipo()==8)premio=20;
//            if(premio>= ganada.getPosicion()) {
//                jugada.setGano(true);
//                jugada.setIdGanada(ganada.getIdGanada());
//                this.actualizarJugada(jugada);}
//            }
//        }
//        return aux;
//    }
//
public boolean actualizarJugada(Jugada jugada) {
        String sSQL = "update Jugada set gano=? where idJugada=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setBoolean(1, jugada.isGano());                    
            pst.setInt(2, jugada.getIdJugada());

            int n = pst.executeUpdate();

            if (n != 0) {
                pst.close();
               return true;
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("informacion");
                alerta.setContentText("ERROR AL ACTUALIZAR EL REGISTRO");
                alerta.showAndWait();
                pst.close();
                return false;
            }

        } catch (Exception e) {
            System.out.println("error :" + e);

        }return false;
    }

//    public void buscameGanadores(Ganada ganada) {
//        ArrayList<String> numeros = this.desarmarNumero(ganada.getNumero());
//        ArrayList<Jugada> jugadas;
//        Date fecha = ganada.getFecha();
//        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
//        String hora = ganada.getHora();
//        for (int i = 0; i < numeros.size(); i++) {
//            Ganada ganada2 = new Ganada(ganada.getIdGanada(),numeros.get(i), ganada.getPosicion(), ganada.getQuiniela(), ganada.getTurno(), fechaSql, hora);
//            
//            jugadas = this.pedirJugadasBase(ganada2);
//            this.buscameGanadores(jugadas, ganada2);
//        }
//        }
    public ArrayList<String> desarmarNumero(String numero) {

        ArrayList<String> lista = new ArrayList();        
        String una = numero.substring(3);
         String dos = numero.substring(2);
          String tres = numero.substring(1);
           String cuatro = numero.substring(0);
        lista.add(una);lista.add(dos);lista.add(tres);lista.add(cuatro);
        return lista;
    }

//    private ArrayList<Integer> desarmarNumero(int numero, ArrayList<Integer> lista) {
//       /* int cifras = contarCifras(numero);
//        if (cifras > 1) {
//            lista.add(numero);
//            numero = (int) (numero % (Math.pow(10, (cifras - 1))));
//            return desarmarNumero(numero, lista);
//        }
//        lista.add(numero);*/
//        return lista;
//    }

    public boolean jugadaGano(Jugada j) {
        if (j.isGano() && j.getTipo()==1) {
            return true;
        } else if (j.isGano() && j.getTipo()==2) {     
            Jugada jugadaPareja = this.buscarPareja(j);
            if (jugadaPareja.isGano()) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

public Jugada buscarPareja(Jugada jugada) {

        try {
              
            Statement orden = cn.createStatement();
           sSQL = "SELECT * from jugada where jugada.idboleta=" + jugada.getIdBoleta() + " and jugada.idRedoblona=" + jugada.getIdRedoblona() + " and jugada.idJugada !=" +jugada.getIdJugada() ;
            ResultSet r = orden.executeQuery(sSQL);
            while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo = r.getInt("tipo");
                String numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                String turno = r.getString("turno");
                //String nombre = r.getString("nombre");
                boolean gano = r.getBoolean("gano");
                int idBoleta = r.getInt("idBoleta");
                int idRedoblona = r.getInt("idRedoblona");
                Date fecha = r.getDate("fecha");               
              
                Jugada j = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
               
                    orden.close();
                    r.close();
                    return j;
               }
              orden.close();
                     r.close();

        } catch (SQLException ex) {
            System.out.println("error :" + ex);

        }
        return null;
    }
//
//public ArrayList<Jugada> buscarBorratina(Jugada jugada) {
//     ArrayList<Jugada> jugadas = new ArrayList();
//        try {
//            con = new Conexion();
//    
//            Statement orden = cone2.createStatement();
//            String sSQL2 = "SELECT * from Jugadas where Jugadas.idboleta=" + jugada.getIdBoleta() + " and Jugadas.idRedoblona=" + jugada.getIdRedoblona();
//            ResultSet r = orden.executeQuery(sSQL2);
//            while (r.next()) {
//                int idJugada = r.getInt("idJugada");
//                int tipo = r.getInt("tipo");
//               String  numero = r.getString ("numero");
//                int cifras = r.getInt("cifras");
//                int posicion = r.getInt("posicion");
//                int monto = r.getInt("monto");
//                String quiniela = r.getString("quiniela");
//                String turno = r.getString("turno");
//                String nombre = r.getString("nombre");
//                boolean gano = r.getBoolean("gano");
//                boolean pago = r.getBoolean("pago");
//                int idBoleta = r.getInt("idBoleta");
//                int idGanada = r.getInt("idGanada");
//                int idRedoblona = r.getInt("idRedoblona");
//                Date fecha = r.getDate("fecha");
//                int idPago = r.getInt("idPago");
//                String hora = r.getString("hora");
//
//                
//                Jugada j = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, idPago, hora, nombre, gano, pago, idBoleta, idRedoblona, idGanada);
//              System.out.println("entro el :" + j.getNumero() + " en la posicion :" + j.getPosicion() + "y el estado de gano es : " + j.isGano());
//                jugadas.add(j);
//                }
//             orden.close();
//            r.close();
//            return jugadas;
//                
//        } catch (SQLException ex) {
//            System.out.println("error :" + ex);
//
//        }
//        return jugadas;
//    }    
//    
//public boolean isTienePremio(Jugada j) {
//        if (this.jugadaGano(j)) {
//            return true;
//        }else return false;
//    }

public int calcularPremio(Jugada j) {
            if(j.isGano() && j.getTipo()==1){
              int total = this.premio(j);
              return total;
            }else {
                System.out.println("entro a calcular premio redoblona ***************");
                 if(j.isGano() && j.getTipo()==2){
                     System.out.println("j is gano y es de tipo 2***************");
                    Jugada pareja=this.buscarPareja(j);                 
                    int total = this.premio(j,pareja);  
                    return total;
                      }
            return 0;
         }
    }
    
public int premio(Jugada j){
   int fila=j.getPosicion()-1;
   int columna=j.getCifras()-1;
   int monto=j.getMonto();
   int multiplicador=(int) this.matriz[fila][columna];
   int premio=monto*multiplicador; 
   return premio;
    }
// private Jugada jugadaUno(ArrayList<Jugada>pareja){
//     if(pareja.get(0).getPosicion()< pareja.get(1).getPosicion())return pareja.get(0);
//     else return pareja.get(1);
// }
//   private Jugada jugadaDos(ArrayList<Jugada>pareja){
//     if(pareja.get(0).getPosicion()< pareja.get(1).getPosicion())return pareja.get(1);
//     else return pareja.get(0);
// }
//   
private int premio(Jugada j, Jugada g){
    System.out.println("entro por fina calular el premio******");
    Jugada uno;Jugada dos;
             if(this.sonPareja(j, g)){
                if(j.isGano() && g.isGano()){
                    if(j.getPosicion()<g.getPosicion()){
                       uno=j; dos=g;}
                    else{
                         uno=g; dos=j;
                    }
                      int columna;
                      if(dos.getPosicion()==5)columna=0;
                      else if(dos.getPosicion()==10)columna=1;
                      else columna=2;
                   int fila;
                     if(uno.getPosicion()==1)fila=0; 
                     else if(uno.getPosicion()==5)fila=1;
                     else if(uno.getPosicion()==10)fila=2;
                     else fila=3;
                     int monto=uno.getMonto();
  
                     int multiplicador=(int) this.matrizRedoblon[fila][columna];
                     int premio=monto*multiplicador; 
                     return premio;
                    }
                   
        }return 0;
  
}
public boolean sonPareja(Jugada uno, Jugada dos){
   if(uno.getIdRedoblona()==dos.getIdRedoblona()){
      if(uno.getIdBoleta()== dos.getIdBoleta()){
        if(uno.getTipo() == 2 && dos.getTipo() == 2){
                    return true;
            }
        }
    }
   return false;
}

//
//void actualizarNumero(Jugada jugada, int parseInt) {
//            String sSQL = "update Jugadas set numero=? where idJugada=?";
//
//        try {
//            con = new Conexion();
//       
//            PreparedStatement pst = cone2.prepareStatement(sSQL);
//            pst.setString(1, jugada.getNumero());
//            
//            pst.setInt(3, jugada.getIdJugada());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
//                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//                alerta.setHeaderText(null);
//                alerta.setTitle("informacion");
//                alerta.setContentText("SE ACTUALIZO EL REGISTRO JUGADAS");
//                alerta.showAndWait();
//                pst.close();
//                
//            } else {
//                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//                alerta.setHeaderText(null);
//                alerta.setTitle("informacion");
//                alerta.setContentText("ERROR AL ACTUALIZAR EL REGISTRO");
//                alerta.showAndWait();
//               pst.close();
//            }
//
//        } catch (Exception e) {
//            System.out.println("error :" + e);
//
//        }
//    }
//
//public boolean isTienePremio(ArrayList<Jugada> jugadas) {
//    System.out.println("la borratina es de   :"+jugadas.size() );
//        int ganadas=0;
//        int borratina=jugadas.get(0).getTipo();
//        boolean pagado=true;
//        for(Jugada jugada : jugadas){
//            if(jugada.isGano())ganadas++;
//            pagado=pagado && jugada.isPago();
//        }
//       if(borratina==3 && ganadas==3 && !pagado){
//         System.out.println("borratina de 3 con ganadores :"+ganadas+" y el estado del pago es :"+pagado);
//           return true;
//      }
//       if(borratina==5 && ganadas >=3 && !pagado){
//           System.out.println("borratina de 5 con ganadores :"+ganadas+" y el estado del pago es :"+pagado);
//           return true;
//       } 
//       if(borratina==8 && ganadas>=6 && !pagado){System.out.println("borratina de 8 con ganadores :"+ganadas+" y premio es :"+pagado);
//        return true;
//    }
//           return false;
//  }
//public int isTienePremio(ArrayList<Jugada> jb1, ArrayList<Jugada> jb2 ) {
//        int ganadas=0;
//        boolean pagado=false;
//        for(int i=0;i<jb1.size();i++){
//            if(jb1.get(i).isGano()|| jb2.get(i).isGano())ganadas++;
//              pagado=pagado || (jb1.get(i).isPago() || jb2.get(i).isPago());
//        }
//      
//       if(ganadas>=6 && !pagado){System.out.println("borratina de 8 con ganadores :"+ganadas+" y el estado del pago es :"+pagado);
//        return ganadas;
//    }
//           return ganadas+10;
//  }
//
//    
//public int calcularPremioBorratina(ArrayList<Jugada> jugadas) {
//    GanadaController controladorGanada=new GanadaController();
//    ArrayList<Integer>posiciones = new ArrayList();
//        int premio;
//        for(int i=0;i<jugadas.size();i++){
//         if(jugadas.get(i).isGano()){
//        //  Ganada ganada= controladorGanada.buscar(jugadas.get(i).getIdGanada());
//          posiciones.add(jugadas.get(i).getIdGanada()); //el id de ganada contne la posicion de la ganada
//         }
//           }
//               
//         if(jugadas.get(0).getTipo()==3){
//        premio=this.calcularBorratina3(3,posiciones);
//        return premio;
//        } 
//           if(jugadas.get(0).getTipo()==5){
//               System.out.println("detecto que la borratina es tipo 5 y que tiene premio entro a acalcular ");
//       premio=this.calcularBorratina5(5, jugadas);
//         return premio;
//           }
//            if(jugadas.get(0).getTipo()==8){
//        premio=this.calcularBorratina8(8, jugadas);
//        return premio;
//           }
//    return 0;
//    }
//
//private int calcularBorratina3(int i, ArrayList<Integer> posiciones) {
//        if(posiciones.get(0)==0 && posiciones.get(1)==1 && posiciones.get(2)==2)return 35000;
//        else if(posiciones.get(0)<3 && posiciones.get(1)<3 && posiciones.get(2)<3)return 16000;
//        else if(posiciones.get(0)<4 && posiciones.get(1)<4 && posiciones.get(2)<4)return 2800;
//        else if(posiciones.get(0)<7 && posiciones.get(1)<7 && posiciones.get(2)<7)return 1600;
//        else if(posiciones.get(0)<10 && posiciones.get(1)<10 && posiciones.get(2)<10)return 650;
//        else if(posiciones.get(0)<15 && posiciones.get(1)<15 && posiciones.get(2)<15)return 300;
//        else if(posiciones.get(0)<20 && posiciones.get(1)<20 && posiciones.get(2)<20)return 120;
//        else return 0;
//    }
//private int calcularBorratina5(int i, ArrayList<Jugada> jugadas) {
//    int ganadas=0;
//   for(Jugada jugada : jugadas){
//            if(jugada.isGano()){
//                System.out.println("ganadas son "+ganadas);
//                ganadas++;}
//                 }
//    System.out.println("la cantidad de ganadas que detectamos ene sta instancia es de :"+ganadas+"de cantidad de jugadas "+jugadas.size());
//        if(ganadas==5)return 10000;
//        if(ganadas==4)return 500;
//        if(ganadas==3)return 65;
//        
//       else return 0;
//    }
//private int calcularBorratina8(int i, ArrayList<Jugada> jugadas) {
//    int ganadas=0;
//    ArrayList<String>numeros=new ArrayList();
//     for(Jugada jugada : jugadas){
//            if(jugada.isGano()){
//                ganadas++;
//                 if(!numeros.contains(jugada.getNumero())){
//                     numeros.add(jugada.getNumero());
//                 }
//            System.out.println("ganada de boratina  es "+ganadas+"///////////////////////y numeros aderidos son "+numeros.size());
//          }
//     }
//        if(numeros.size()==8)return 8000;
//        if(ganadas==7)return 328;
//        if(ganadas==6)return 35;
//        
//       else return 0;
//    }
//
//
public void actualizarJugada(int id) {
   
   sSQL = "update Jugada set gano=?,idGanada=? where idGanada=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setBoolean(1, false);
            pst.setInt(2, 0);
   
            pst.setInt(3, id);
            int n = pst.executeUpdate();

            if (n != 0) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("informacion");
                alerta.setContentText("SE ACTUALIZO EL REGISTRO JUGADAS");
                alerta.showAndWait();
                pst.close();
                
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setTitle("informacion");
                alerta.setContentText("ERROR AL ACTUALIZAR EL REGISTRO");
                alerta.showAndWait();
               pst.close();
            }

        } catch (Exception e) {
            System.out.println("error :" + e);

        }
    }

//public ArrayList<Jugada> buscameGanadores(int id) {
//    ArrayList<Jugada> j =new ArrayList();
//       try {
//            this.con = new Conexion();
//      
//            Statement orden = cone2.createStatement();
//            String sSQL = "SELECT * from Jugadas where Jugadas.idGanada="+id;
//            ResultSet r = orden.executeQuery(sSQL);
//          while (r.next()) {
//                int idJugada = r.getInt("idJugada");
//                int tipo =r.getInt("tipo");
//                String  numero = r.getString ("numero");
//                int cifras = r.getInt("cifras");
//                int posicion = r.getInt("posicion");
//                int monto = r.getInt("monto");
//                String quiniela = r.getString("quiniela");
//                String turno = r.getString("turno");
//                String nombre = r.getString("nombre");
//                int idBoleta = r.getInt("idBoleta");
//                Date fecha = r.getDate("fecha");
//               int idPago = r.getInt("idPago");
//                String hora = r.getString("hora");
//                boolean gano = r.getBoolean("gano");
//                boolean pago = r.getBoolean("pago");
//                int idRedoblona = r.getInt("idRedoblona");
//                int idGanada = r.getInt("idGanada");
//
//                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, idPago, hora, nombre, gano, pago, idBoleta, idRedoblona, idGanada);
//                j.add(jugada);
//                           }
//           orden.close();
//            r.close();
//            return j;
//
//        } catch (SQLException ex) {
//            System.out.println("error :" + ex);
//            return null;
//        }
//    }
//
ArrayList<Jugada> consultar(Date fecha, String quiniela, String turno) {

     ArrayList<Jugada> jugadas = new ArrayList();
    
        try {

            Statement orden=cn.createStatement();
            sSQL="SELECT * from Jugada where CAST(Jugada.fecha AS DATE)='"+fecha+"' and Jugada.quiniela='"+quiniela+"' and Jugada.turno='"+turno+"'";
            ResultSet r =orden.executeQuery(sSQL);  
           while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo =r.getInt("tipo");
                String  numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                int idBoleta = r.getInt("idBoleta");
                boolean gano = r.getBoolean("gano");
                int idRedoblona = r.getInt("idRedoblona");
                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                jugadas.add(jugada);
           }
         orden.close();
            r.close();    
           return jugadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return jugadas;
    }

public ResumenJugada generarResumenRedoblona(ArrayList<Integer> Nposiciones, ArrayList<String> quinielas, ArrayList<String> turnos, String numero1, String numero2, int monto) {
        StringBuilder jugada = new StringBuilder();
        jugada.append("R:");
        jugada.append(numero1);
        jugada.append("_");
        jugada.append(numero2);
        jugada.append("  ");
        for (String quiniela : quinielas) {
            jugada.append(quiniela);
                  }jugada.append("  ");
            for (String turno : turnos) {
                jugada.append(turno);
                jugada.append("/");}
                 jugada.append("  ");
                for (Integer posicion : Nposiciones) {
                    jugada.append(String.valueOf(posicion));
                      jugada.append("-");      
                  }jugada.append("  ");
                  jugada.append("$");
                  jugada.append(monto);
                ResumenJugada resumen=new ResumenJugada(numero1,jugada);
              return resumen;
  
    }

public ArrayList<Jugada> consultarFecha(Date fecha) {
             ArrayList<Jugada> jugadas = new ArrayList();
    
        try {
                
            Statement orden=cn.createStatement();
            String sSQL3="SELECT * from Jugada where Jugada.fecha='"+fecha+"'";
            ResultSet r =orden.executeQuery(sSQL3);  
           while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo =r.getInt("tipo");
                String  numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                String turno = r.getString("turno");
                int idBoleta = r.getInt("idBoleta");
                boolean gano = r.getBoolean("gano");
                int idRedoblona = r.getInt("idRedoblona");
                 int idPago = r.getInt("idPago");
                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);             
                jugadas.add(jugada);
           }
             orden.close();
            r.close();  
       return jugadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return jugadas;

    }

public ArrayList<Jugada> consultarEntreFechas(java.sql.Date fecha1, java.sql.Date fecha2) {
                  ArrayList<Jugada> jugadas = new ArrayList();
        try {

            Statement orden=cn.createStatement();
          String sSQL3="SELECT * from Jugada where fecha BETWEEN '"+(java.sql.Date)fecha1+"' AND '"+(java.sql.Date)fecha2+"'";
            ResultSet r =orden.executeQuery(sSQL3);  
           while (r.next()) {
                int idJugada = r.getInt("idJugada");
                int tipo =r.getInt("tipo");
               String  numero = r.getString ("numero");
                int cifras = r.getInt("cifras");
                int posicion = r.getInt("posicion");
                int monto = r.getInt("monto");
                String quiniela = r.getString("quiniela");
                  String turno = r.getString("turno");
                int idBoleta = r.getInt("idBoleta");
                Date fecha = r.getDate("fecha");
                boolean gano = r.getBoolean("gano");
                int idRedoblona = r.getInt("idRedoblona");

                Jugada jugada = new Jugada(idJugada, tipo, numero, cifras, posicion, monto, quiniela, turno, (java.sql.Date) fecha, gano, idBoleta, idRedoblona);
                jugadas.add(jugada);
           }
            orden.close();
            r.close();   
       return jugadas;
       } catch (SQLException ex) {
            System.out.println("error :"+ex);
             
        }
    return jugadas;

    }
 public Jugada buscarPareja(Jugada jugada, ArrayList<Jugada> jugadas){
     for(Jugada j : jugadas){
         if(j.getIdRedoblona() == jugada.getIdRedoblona() && j.getIdJugada() != jugada.getIdJugada()){
             return j;
         }
     }
     return null;
 }
    
    }

    





  
    


