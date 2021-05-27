/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Tickets.Stracto;
import Tickets.Ticket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Ganada;
import model.Jugada;


/**
 * FXML Controller class
 *
 * @author yo
 */
public class ViewCargaDatos2Controller implements Initializable {
   
    private int turno;
    private int quiniela;
    private int posicion;
  
    private ObservableList<Ganada>provincia;
    private ObservableList<Ganada>nacional;
    private ObservableList<Ganada>santafe;
    private ObservableList<Ganada>entrerios;
    private ObservableList<Ganada>cordoba;
    private ObservableList<Ganada>oros;
    
    private ObservableList<Ganada> listQuinielas[];
    
    private String turnos[]; 
    private String quinielas[]; 
  
    @FXML
    private TextField txtNumero;
    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private HBox lblMatutina;
    @FXML
    private HBox lblNocturna;
    @FXML
    private HBox lblTarde;
    @FXML
    private HBox lblVespertina;
    @FXML
    private HBox lblNacional;
    @FXML
    private HBox lblProvincia;
    @FXML
    private HBox lblSantafe;
    @FXML
    private HBox lblEntrerios;
    @FXML
    private HBox lblCordoba;
    @FXML
    private HBox lblOros;
    
    private HBox lblTurnos[];
    private HBox lblQuinielas[];
    
    @FXML
    private TableView<Ganada> tblProvincia;
    @FXML
    private TableColumn<?, ?> colProvincia;
    @FXML
    private TableColumn<?, ?> colNumeroP;
    @FXML
    private TableView<Ganada> tblNacional;
    @FXML
    private TableColumn<?, ?> colNacional;
    @FXML
    private TableColumn<?, ?> colNumeroN;
    
    private TableView<Ganada> tblQuinielas[];
    
    @FXML 
    private DatePicker dp;
    private java.sql.Date fecha;
    
    private BoletaController boletaController;
    private GanadaController ganadaController;
    private JugadaController jugadaController;
    private int posicionJugadaTabla;
    @FXML
    private TableView<?> tblSantafe;
    @FXML
    private TableColumn<?, ?> colNumeroS;
    @FXML
    private TableColumn<?, ?> colSantafe;
    @FXML
    private TableView<?> tblEntrerios;
    @FXML
    private TableColumn<?, ?> colNumeroE;
    @FXML
    private TableColumn<?, ?> colEntrerios;
    @FXML
    private TableView<?> tblCordoba;
    @FXML
    private TableColumn<?, ?> colNumeroC;
    @FXML
    private TableColumn<?, ?> colCordoba;
    @FXML
    private TableView<?> tblOros;
    @FXML
    private TableColumn<?, ?> colNumeroO;
    @FXML
    private TableColumn<?, ?> colOros;
    
    private String estado;
    @FXML
    private Label lblFecha;
    @FXML
    private Button btnImprimirStracto;
    private boolean changes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.estado = "agregar";
        this.changes = false;
        jugadaController = new JugadaController();
        ganadaController = new GanadaController();
        boletaController = new BoletaController();
        this.quinielas = new String[]{"N", "P", "F", "E", "C", "O"};
        this.turnos = new String[]{"11", "14", "17", "21"};
        this.quiniela = 0;
        this.turno = 0;
        this.lblTurnos = new HBox[]{lblMatutina,lblVespertina,lblTarde,lblNocturna};
        this.lblQuinielas = new HBox[]{lblNacional,lblProvincia,lblSantafe,lblEntrerios,lblCordoba,lblOros};
        this.tblQuinielas = new TableView[]{tblNacional,tblProvincia,tblSantafe,tblEntrerios,tblCordoba,tblOros};
        
         provincia = FXCollections.observableArrayList();
         nacional = FXCollections.observableArrayList();
         santafe = FXCollections.observableArrayList();
         entrerios = FXCollections.observableArrayList();
         cordoba = FXCollections.observableArrayList();
         oros = FXCollections.observableArrayList();
         
         
        this.listQuinielas = new ObservableList[]{nacional,provincia,santafe,entrerios,cordoba,oros};
        
        this.colNacional.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colProvincia.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colSantafe.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colEntrerios.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colCordoba.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colOros.setCellValueFactory(new PropertyValueFactory("numero"));
        
        this.colNumeroN.setCellValueFactory(new PropertyValueFactory("posicion"));
        this.colNumeroP.setCellValueFactory(new PropertyValueFactory("posicion"));
        this.colNumeroS.setCellValueFactory(new PropertyValueFactory("posicion"));
        this.colNumeroE.setCellValueFactory(new PropertyValueFactory("posicion"));
        this.colNumeroC.setCellValueFactory(new PropertyValueFactory("posicion"));
        this.colNumeroO.setCellValueFactory(new PropertyValueFactory("posicion"));
        
        this.setQuiniela(quiniela);
        this.setTurno(turno);
       

       panelPrincipal.setOnKeyReleased((javafx.scene.input.KeyEvent e) -> {
           if (this.validar(this.txtNumero.getText())){
               //this.agregarNumero(this.txtNumero.getText());
              this.chercarAction(this.txtNumero.getText());
                }else{switch(e.getCode()){
                    
               case DOWN :if(this.changes)this.actualizarJugadas();
                         this.upTurno(turno);
                         this.setTurno(turno);
                         break;
                         
               case UP : if(this.changes)this.actualizarJugadas();
                            this.downTurno(turno);
                            this.setTurno(turno);
                             break; 
                                    
               case RIGHT : this.upQuiniela(quiniela);
                            this.setQuiniela(quiniela);
                            break;
                            
               case LEFT : this.downQuiniela(quiniela);
                             this.setQuiniela(quiniela);
                             break;
                 
                  case ENTER : this.actualizarJugadas();break;  
                        
                case ESCAPE :if(this.changes)this.actualizarJugadas(); this.cerrarVentana(); break;       
                         
               }
          }
    });
       
     dp.setOnKeyPressed(e->{
       switch(e.getCode()){
    
       case ENTER: this.setFecha();
       this.txtNumero.requestFocus();break; } 
     }); 
      this.fecha = this.fechaActual();
      this.dp.setValue(LocalDate.now());
      this.setFecha();
      this.mostrarGanadas(this.fecha, turnos[turno]);
 }
    
private void chercarAction(String numero){
    if("agregar".equals(this.estado)){
        this.agregarNumero(numero);
    }else{
        this.modificarNumero(numero);
    }
}    
    
    
 public java.sql.Date fechaActual(){
     java.util.Date fecha = new java.util.Date();
     java.sql.Date fechaSql=new java.sql.Date(fecha.getTime());
     return fechaSql;
 }    
        
public void setFecha(){
     
     if(dp.getValue()!=null){
      LocalDate ld = dp.getValue();
       
     java.util.Date date = java.sql.Date.valueOf(ld);
     this.fecha=(java.sql.Date) date;
     this.mostrarGanadas(this.fecha,turnos[turno]);
            
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatoFecha.format(this.fecha);
       
    this.lblFecha.setText(fechaFormato);
      }    
} 
  
    private void upTurno(int turno){
        if(turno < 3){
            this.turno++;
        }
          System.out.println(this.turno);
        }   
    
    
    private void downTurno(int turno){    
        if(turno > 0){
            this.turno--;
        }
          System.out.println(turno);
    }
  
    
   private void upQuiniela(int quiniela){
        if(quiniela < 5){
            this.quiniela++;
        }
    }
   
   private void downQuiniela(int quiniela){
        if(quiniela > 0){
            this.quiniela--;
        }
    }
   
   private void setTurno(int turno){

       for(int i = 0; i < lblTurnos.length; i++){
           if(i == turno ){
               lblTurnos[i].setStyle("-fx-background-color: #333645;");
           }else{
               lblTurnos[i].setStyle("-fx-background-color: #FFFFFF;");
             }
       }
       this.txtNumero.requestFocus();
       
       this.mostrarGanadas(this.fecha, turnos[turno]);
             if(listQuinielas[quiniela].size()<20){
          System.out.println("cantidad de ganadas son :"+listQuinielas[quiniela].size());
            txtNumero.setVisible(true);
         this.txtNumero.requestFocus();
         }
        else{
            txtNumero.setVisible(false);
        }
     }
    
  private void setQuiniela(int quiniela){
       System.out.println("entro a set quiniela :"+quiniela);
       for(int i = 0; i < lblQuinielas.length; i++){
           if(i == quiniela ){
               lblQuinielas[i].setStyle("-fx-background-color: #333645;");
           }else{
               lblQuinielas[i].setStyle("-fx-background-color: #FFFFFF;");
             }
       }
       this.txtNumero.requestFocus();
       
      if(listQuinielas[quiniela].size()<20){
          System.out.println("cantidad de ganadas son :"+listQuinielas[quiniela].size());
            txtNumero.setVisible(true);
         this.txtNumero.requestFocus();
         }
        else{
            txtNumero.setVisible(false);
        }
   }
  
    @FXML
    private void seleccionarN(MouseEvent event) {
        this.quiniela = 0;
        this.setQuiniela(quiniela);
        Ganada ganada = this.tblQuinielas[quiniela].getSelectionModel().getSelectedItem();
        if(ganada != null){        
        this.txtNumero.setVisible(true);
        txtNumero.setText(ganada.getNumero());
        this.estado = "modificar";
        this.txtNumero.requestFocus();
         this.txtNumero.selectAll();
        }
    }

    @FXML
    private void seleccionarP(MouseEvent event) {
        this.quiniela = 1;
        this.setQuiniela(quiniela);
        Ganada ganada = this.tblQuinielas[quiniela].getSelectionModel().getSelectedItem();
        if(ganada != null){
        this.txtNumero.setVisible(true);
        txtNumero.setText(ganada.getNumero());
        this.estado = "modificar";
        this.txtNumero.requestFocus();
         this.txtNumero.selectAll();
        }
    }

    @FXML
    private void seleccionarS(MouseEvent event) {
        this.quiniela = 2;
        this.setQuiniela(quiniela);        
        Ganada ganada = this.tblQuinielas[quiniela].getSelectionModel().getSelectedItem();
       if(ganada != null){        
        this.txtNumero.setVisible(true);
        txtNumero.setText(ganada.getNumero());       
        this.estado = "modificar";
        this.txtNumero.requestFocus();
        this.txtNumero.selectAll();
       }
    }

    @FXML
    private void seleccionarE(MouseEvent event) {
        this.quiniela = 3;
        this.setQuiniela(quiniela);        
        Ganada ganada = this.tblQuinielas[quiniela].getSelectionModel().getSelectedItem();
        if(ganada != null){
        this.txtNumero.setVisible(true);
        txtNumero.setText(ganada.getNumero());
        this.estado = "modificar";
        this.txtNumero.requestFocus();  
       this.txtNumero.selectAll();
        }
    }

    @FXML
    private void seleccionarC(MouseEvent event) {
        this.quiniela = 4;
        this.setQuiniela(quiniela);        
        Ganada ganada = this.tblQuinielas[quiniela].getSelectionModel().getSelectedItem();
       
        if(ganada != null){
        this.txtNumero.setVisible(true);
        txtNumero.setText(ganada.getNumero());
        this.estado = "modificar";
        this.txtNumero.requestFocus();
        this.txtNumero.selectAll();
        }
    }

    @FXML
    private void seleccionarO(MouseEvent event) {
        this.quiniela = 5;
        this.setQuiniela(quiniela);        
        Ganada ganada = this.tblQuinielas[quiniela].getSelectionModel().getSelectedItem();
        if(ganada != null){
        txtNumero.setText(ganada.getNumero());      
        this.estado = "modificar";
        this.txtNumero.setVisible(true);
        this.txtNumero.requestFocus();   
        this.txtNumero.selectAll();
        }
    }
    
    private void agregarNumero(String numero){
           
             posicion = listQuinielas[quiniela].size()+1;
             Ganada ganada = new Ganada(numero, posicion, quinielas[quiniela], turnos[turno], this.fecha);
             this.listQuinielas[quiniela].add(ganada);            
             this.tblQuinielas[quiniela].setItems(this.listQuinielas[quiniela]);
             this.ganadaController.insertar(ganada);
             //
             this.changes = true;           
             this.txtNumero.setText("");
         if(listQuinielas[quiniela].size()<20){
          System.out.println("cantidad de ganadas son :"+listQuinielas[quiniela].size()+1);
            txtNumero.setVisible(true);
         this.txtNumero.requestFocus();
         }
        else{
            txtNumero.setVisible(false);
        }
       
    }
       
    private void modificarNumero(String numero){  
             posicion = this.tblQuinielas[quiniela].getSelectionModel().getSelectedIndex();
             this.listQuinielas[quiniela].get(posicion).setNumero(numero);  
             this.tblQuinielas[quiniela].refresh();
             Ganada updateGanada = this.listQuinielas[quiniela].get(posicion);
             ganadaController.actualizarGanada(updateGanada); 
             this.changes = true; 
         
             this.txtNumero.setText("");
//             this.txtNumero.requestFocus();
//           this.tblQuinielas[quiniela].getSelectionModel().setSelection(posicion);
             
            this.estado = "agregar";  
           

           } 

    
//    private void eliminarNumero(){
//        posicion = this.tblQuinielas[quiniela].getSelectionModel().getSelectedIndex();
//        this.listQuinielas[quiniela].remove(posicion);
//    }
    
    private boolean validar(String numero){

                if(esNumero(numero)){
                    if(numero.length()==4){
                        this.txtNumero.setStyle("-fx-background-color: #BAFF9B;");
                         return true;
                    }else{
                        this.txtNumero.setStyle("-fx-background-color: #FF7637;");
                         return false;
                    }
                }return false;
            }            
      
    public boolean esNumero(String val){
        try{
            Integer.parseInt(val);
            return true;
        }catch(Exception e){
            return false;
        }
 }

    public void mostrarGanadas(Date fechaSQL, String turno){

         this.listQuinielas[1]=ganadaController.consultar(fechaSQL,"P", turno);
         this.tblQuinielas[1].setItems(this.listQuinielas[1]);
         
         this.listQuinielas[0]=ganadaController.consultar(fechaSQL,"N", turno);
         this.tblQuinielas[0].setItems(this.listQuinielas[0]);
         
         this.listQuinielas[2]=ganadaController.consultar(fechaSQL,"F", turno);
        this.tblQuinielas[2].setItems(this.listQuinielas[2]);
        
         this.listQuinielas[3]=ganadaController.consultar(fechaSQL,"E", turno);
         this.tblQuinielas[3].setItems(this.listQuinielas[3]);
         
         this.listQuinielas[4]=ganadaController.consultar(fechaSQL,"C", turno);
         this.tblQuinielas[4].setItems(this.listQuinielas[4]);
         
        this.listQuinielas[5]=ganadaController.consultar(fechaSQL,"O", turno);
        this.tblQuinielas[5].setItems(this.listQuinielas[5]);

    } 
    
 private void cerrarVentana() {
    Node source = (Node) panelPrincipal;
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
 private void resetCacheImpresora() {
    
    
//Ticket ticket = new Ticket();
Ticket.resetCabezalineas();
Ticket.resetItems();
Ticket.resetLineasPie();
Ticket.resetSubCabezalineas();
Ticket.resetTotals();

    } 
  
private void actualizarJugadas(){ 

    ObservableList<Ganada> lisGanadas;
    ArrayList<Jugada> jugadas;
        for(int i = 0; i < this.listQuinielas.length; i++){ 
           lisGanadas = ganadaController.consultar(fecha,quinielas[i], turnos[turno]);
           jugadas =jugadaController.consultar( fecha,quinielas[i], turnos[turno]);
           
              for(Jugada jugada : jugadas){
               boolean aux = jugada.isGano();
               jugada.setGano(false);
               
                for(Ganada ganada : lisGanadas){
                 if(!jugada.isGano()){
                  if(jugada.getPosicion() >= ganada.getPosicion()){  
                   ArrayList<String> numeros = jugadaController.desarmarNumero(ganada.getNumero());
                   if(numeros.contains(jugada.getNumero()))jugada.setGano(true);
                 }
               }
              }
         if(!jugada.isGano()== aux)jugadaController.actualizarJugada(jugada);       
    }
}
        this.boletaController.actualizarBoletas(fecha);
        this.changes = false;
}

    @FXML
    private void imprimirStracto(MouseEvent event) {
          this.vistaImprecion();
          this.resetCacheImpresora();
    }

 private void vistaImprecion(){
          Stracto st= new Stracto(this.listQuinielas[1], this.listQuinielas[0], this.listQuinielas[2], this.listQuinielas[3], this.listQuinielas[4], this.listQuinielas[5], turnos[turno]);
          st.iprimirStracto();
 } 

}

  


