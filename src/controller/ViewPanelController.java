/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Collections;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import model.Boleta;
import model.Jugada;
import model.Resumen;
import model.Pago;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import utils.herramietasTest;

/**
 * FXML Controller class
 *
 * @author yo
 */

public class ViewPanelController implements Initializable {
    
    @FXML
    private HBox hbCaja;
    @FXML
    private Label lblFecha;
    @FXML
    private HBox hbCargaDatos;
    @FXML
    private HBox hbReporte;
    private HBox hboxs[];
    @FXML
    private VBox panelPrincipal;
    private int posicionHboxs;
    @FXML
    private HBox hbQuiniela;
    @FXML
    private HBox hbBorratina;
    @FXML
    private TableView<Boleta> tblBoleta;
    @FXML
    private TableColumn<?, ?> colBId;
    @FXML
    private TableColumn<?, ?> colBFecha;
    @FXML
    private TableColumn<?, ?> colBNombre;
    @FXML
    private TableColumn<?, ?> colBTotal;
    @FXML
    private TableColumn<?, ?> colBEstado;
    @FXML
    private TableColumn<?, ?> colBPagado;
    @FXML
    private TableColumn<?, ?> colBFechaPago;
    private TableColumn<?, ?> colBMonto;
    @FXML
    private TableView<Resumen> tblJugada;
    @FXML
    private TableColumn<?, ?> colJId;
    @FXML
    private TableColumn<?, ?> colJTurno;
    @FXML
    private TableColumn<?, ?> colJQuiniela;
    @FXML
    private TableColumn<?, ?> colJNumero;
    @FXML
    private TableColumn<?, ?> colJPosicion;
    @FXML
    private TableColumn<?, ?> colJMonto;
    @FXML
    private TableColumn<?, ?> colJEstado;
    
    private ObservableList<Boleta>boletas;
    private ObservableList<Jugada>jugadas;
    private BoletaController boletaController;
    private PagoController pagoController;
    private ResumenController resumenController;
    private JugadaController jugadaController;
    private Pago pago;
    @FXML
    private DatePicker dp;
    private java.sql.Date fecha;
    @FXML
    private TableColumn<?, ?> colBTotalPago;
    private boolean eventKey;
    @FXML
    private AnchorPane panelIzq;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnPagar;
    @FXML
    private Text lblTotal;
    @FXML
    private TextField txtTotal;
    private Timer timer;
    @FXML
    private Text txtHora2;
    @FXML
    private Button btnLogout;
  
    private CajaController cajaController;
    
    @FXML
    private Button btnAnular;
    
    private herramietasTest herramientas ;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.herramientas = new herramietasTest(); //simuladores de jugadas
        this.herramientas.crearJugadas(50);
        this.btnAnular.setVisible(false);
        this.btnPagar.setVisible(false);
        this.txtTotal.setVisible(false);
        this.lblTotal.setVisible(false);
        this.eventKey = false;
        this.jugadaController=new JugadaController();
       this.boletaController=new BoletaController();
       this.pagoController = new PagoController();
       this.resumenController = new ResumenController();
       this.cajaController = new CajaController();
       this.lblFecha.setText(jugadaController.fecha());
       this.txtHora2.setDisable(true);
       this.hboxs = new HBox[]{hbQuiniela,hbBorratina,hbCaja,hbCargaDatos,hbReporte};
       this.posicionHboxs = 0;
       this.setHboxs(posicionHboxs);
       this.boletas = FXCollections.observableArrayList();
       this.jugadas = FXCollections.observableArrayList();
       this.checarCaja();
       this.colBId.setCellValueFactory(new PropertyValueFactory("id"));
       this.colBFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
       this.colBNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       this.colBTotal.setCellValueFactory(new PropertyValueFactory("total"));
       this.colBEstado.setCellValueFactory(new PropertyValueFactory("estado"));
       this.colBPagado.setCellValueFactory(new PropertyValueFactory("pagado"));
       this.colBFechaPago.setCellValueFactory(new PropertyValueFactory("fechaPago"));
       this.colBTotalPago.setCellValueFactory(new PropertyValueFactory("totalPago"));
       
       this.colJId.setCellValueFactory(new PropertyValueFactory("id"));
       this.colJTurno.setCellValueFactory(new PropertyValueFactory("turno"));
       this.colJQuiniela.setCellValueFactory(new PropertyValueFactory("quiniela"));
       this.colJNumero.setCellValueFactory(new PropertyValueFactory("numero"));
       this.colJPosicion.setCellValueFactory(new PropertyValueFactory("posiciones"));
       this.colJMonto.setCellValueFactory(new PropertyValueFactory("monto"));
       this.colJEstado.setCellValueFactory(new PropertyValueFactory("gano"));
                      
       
              panelPrincipal.setOnKeyReleased((javafx.scene.input.KeyEvent e) -> {
                 switch(e.getCode()){
                    
               case DOWN : this.eventKey = true;
                           this.upHboxs(this.posicionHboxs);
                           this.setHboxs(this.posicionHboxs);
                           break;
               case UP :   this.eventKey = true;
                             this.downHboxs(this.posicionHboxs);
                             this.setHboxs(this.posicionHboxs);
                             break; 
               case  F1: this.openFormQuiniela();break; 
               case  F2: System.out.println("borratina");break; 
               case  F3: this.openFormCaja();;break; 
               case  F4: this.openFormCargaDatos();break; 
               case  F5: this.openFormInformes();break; 
               case ENTER:if(this.eventKey){
                   if(this.posicionHboxs == 0){
                            this.openFormQuiniela();break; 
                          }else if(this.posicionHboxs == 1){
                              System.out.println("borratina");break; 
                          }else if(this.posicionHboxs == 2){
                              this.openFormCaja();break; 
                          }else if(this.posicionHboxs == 3){
                              this.openFormCargaDatos();break; 
                          }else if(this.posicionHboxs == 4){
                              openFormInformes();break; 
                          }
                     }
                }
          }
     ); 
       dp.setOnKeyPressed(e->{
       switch(e.getCode()){
            case ENTER: this.setFecha();
           }
       }); 
 
      this.fecha = this.fechaActual();
      this.dp.setValue(LocalDate.now());
      this.setFecha();
      this.mostrarBoletas(this.fecha);
      this.paintTable();
      
       txtBuscar.setOnKeyPressed(e->{
       switch(e.getCode()){
            case ENTER: this.buscar(txtBuscar.getText());
           }
       }); 
       
    TimerTask timerTask = new TimerTask()
     {
         public void run() 
         {
               txtHora2.setText(jugadaController.hora());
         }
     };

     Timer timer = new Timer();
     timer.scheduleAtFixedRate(timerTask, 0, 1000);  

    }

    private void paintTable(){
              
    tblBoleta.setRowFactory(row -> new TableRow<Boleta>(){
   
    @Override
    public void updateItem(Boleta item, boolean empty){
        super.updateItem(item, empty);

        if (!isEmpty()) {
            if(item.isEstado()){
                if(item.isPagado()){
                    setStyle("-fx-background-color: green;");
                }else{
                   setStyle("-fx-background-color: rgba(186, 41, 11, 0.65)");
                }
            }else{
               setStyle("-fx-background-color: rgba(28, 183, 236, 0.65)");
            }
            
        }
    }
});
    }
    private void paintTableJugada(){
              
    tblJugada.setRowFactory(row -> new TableRow<Resumen>(){
   
    @Override
    public void updateItem(Resumen item, boolean empty){
        super.updateItem(item, empty);

        if (!isEmpty()) {
            if(item.isGano()){
                    setStyle("-fx-background-color: green;");
                }else{
                    setStyle("-fx-background-color: rgba(28, 183, 236, 0.65)");
                }
            }else{
               setStyle("-fx-background-color: rgba(28, 183, 236, 0.65)");
            }
            
        }
    });
    }
    
    private void upHboxs(int posicionHboxs){   
        
        if(posicionHboxs < 4){
            this.posicionHboxs++;
        }                  
    }
    
    private void downHboxs(int posicionHboxs){  
      
        if(posicionHboxs > 0){
            this.posicionHboxs--;
        }        
       }
   
  private void setHboxs(int turno){

       for(int i = 0; i < this.hboxs.length; i++){
           if(i == this.posicionHboxs ){
               this.hboxs[i].setStyle("-fx-background-color: #0c0224;");
              
           }else{
               this.hboxs[i].setStyle(" -fx-background-color: #cdcdcd;");
             }
       }
     }
  
     @FXML
    private void openFormQuiniela(MouseEvent event) {
        this.openFormQuiniela();
    }
  

    @FXML
    private void openFormBorratina(MouseEvent event) {
    }
   
    @FXML
    private void openFormCaja(MouseEvent event) {
        openFormCaja();
    }
    
    private void openFormCaja(){
         try {
            this.eventKey = false;    
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewCaja.fxml")); 
            Parent root = loader.load();
            ViewCajaController controlador = loader.getController();
            
              Scene scene= new Scene(root);
              Stage stage= new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            ObservableList<Boleta>aux = FXCollections.observableArrayList();
                           
        } catch (IOException ex) {
            Logger.getLogger(ViewPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openFormCagaDatos(MouseEvent event) {
        openFormCargaDatos();
    }

    @FXML
    private void openFormReporte(MouseEvent event) {
        openFormInformes();
    }

     private void openFormQuiniela(){
            try {
            this.eventKey = false;    
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewQuiniela.fxml")); 
            Parent root = loader.load();
            ViewQuinielaController controlador = loader.getController();
            
              Scene scene= new Scene(root);
              Stage stage= new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            ObservableList<Boleta>aux = FXCollections.observableArrayList();
                  this.mostrarBoletas(this.fecha);
                  this.paintTable();
            
        } catch (IOException ex) {
            Logger.getLogger(ViewPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
     
          private void openFormCargaDatos(){
            try {
            this.eventKey = false;
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewCargaDatos2.fxml")); 
            Parent root = loader.load();
            ViewCargaDatos2Controller controlador = loader.getController();
            
              Scene scene= new Scene(root);
              Stage stage= new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            ObservableList<Boleta>aux = FXCollections.observableArrayList();
                  this.mostrarBoletas(this.fecha);
                  this.paintTable();
        } catch (IOException ex) {
            Logger.getLogger(ViewPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
          
     private void openFormCajaInicial(){
         try {
            this.eventKey = false;    
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewCajaInicial.fxml")); 
            Parent root = loader.load();
            ViewCajaInicialController controlador = loader.getController();
            
              Scene scene= new Scene(root);
              Stage stage= new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle( StageStyle.UNDECORATED );
            stage.setScene(scene);
            stage.showAndWait();
            ObservableList<Boleta>aux = FXCollections.observableArrayList();
                           
        } catch (IOException ex) {
            Logger.getLogger(ViewPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
         private void openFormInformes(){
            try {
            this.eventKey = false;    
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/viewInformes.fxml")); 
            Parent root = loader.load();
            ViewInformesController controlador = loader.getController();
            
              Scene scene= new Scene(root);
              Stage stage= new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            
            this.mostrarBoletas(this.fecha);
            this.paintTable();
            
        } catch (IOException ex) {
            Logger.getLogger(ViewPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
 } 

    public void mostrarBoletas(Date fechaSQL){
        ObservableList<Boleta>boletasCheckPagos;
        boletasCheckPagos = FXCollections.observableArrayList();
         this.boletas = this.boletaController.consultarxFecha(fechaSQL);
         boletasCheckPagos = this.pagoController.checarPagos(boletas);
         this.tblBoleta.setItems(boletasCheckPagos); 
         this.tblJugada.getItems().clear();
         this.deshabilitarPago();
         this.btnAnular.setVisible(false);
     } 
  public void mostrarBoletas(ObservableList<Boleta> boletas){
         ObservableList<Boleta>boletasCheckPagos;
         boletasCheckPagos = FXCollections.observableArrayList();
         boletasCheckPagos = this.pagoController.checarPagos(boletas);
         this.tblBoleta.setItems(boletasCheckPagos); 
         this.tblJugada.getItems().clear();
         this.deshabilitarPago();
         this.btnAnular.setVisible(false);
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
     this.mostrarBoletas(fecha);
     this.deshabilitarPago();
            
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatoFecha.format(this.fecha);
       
   // this.lblFecha.setText(fechaFormato);
      }    
} 
public void eventKey(){
  
        this.eventKey = true;
}

private void buscar(String text) {
    ObservableList<Boleta> busqueda = FXCollections.observableArrayList(); 
    if(text.isEmpty()){this.mostrarBoletas(this.fecha);
    }else if(this.esNumero(text)){
        busqueda = this.boletaController.consultarXId(Integer.parseInt (text));
        this.mostrarBoletas(busqueda); 
        this.txtBuscar.setText("");
    }else if(text.equals("gano")){   
          busqueda = this.boletaController.consultarxState(true, this.fecha);
          this.mostrarBoletas(busqueda); 
           this.txtBuscar.setText("");
      }else{
         busqueda = this.boletaController.consultarxNombre(text, this.fecha);
          this.mostrarBoletas(busqueda); 
           this.txtBuscar.setText("");
    }
    }
public boolean esNumero(String val){
        try{
            Integer.parseInt(val);
            return true;
        }catch(Exception e){
            return false;
        }
 }

    @FXML
    private void seleccionarBoleta(MouseEvent event) {
        Boleta boleta = this.tblBoleta.getSelectionModel().getSelectedItem();
        ObservableList<Resumen> jugadas = FXCollections.observableArrayList(); 
        jugadas = this.resumenController.crearResumen(boleta.getId());
        this.tblJugada.setItems(jugadas);
        paintTableJugada();
        if(boleta.isEstado()){
            if(!boleta.isPagado()){
                int totalpago = pagoController.calcularPago(boleta);
                this.habilitarPago();
                this.txtTotal.setText(""+totalpago);
                this.pago = new Pago(boleta.getId(),this.fechaActual(),totalpago);
            }else if(boleta.isPagado()){
                   this.deshabilitarPago();
               }
        }else if(!boleta.isEstado()){
             this.deshabilitarPago();
             if(this.isDelDia(boleta)){
                 if(this.isCancelable(boleta)){
                     this.btnAnular.setVisible(true);
                 }else{
                     this.btnAnular.setVisible(false);
                 }
             }else{
                     this.btnAnular.setVisible(false);
             }
        }
        
    }

    @FXML
    private void generarPago(MouseEvent event) {
        this.generarPago(this.pago);       
    }

    private void generarPago(Pago pago){
         Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("SE GENERARA UN PAGO DE BOLETA!!");
                       
                     if (alerta.showAndWait().get() == ButtonType.OK){
                         this.pagoController.insertar(pago);  
                         this.mostrarBoletas(this.fecha);
                         this.paintTable();
                         this.deshabilitarPago();
                               }
    }
 private void habilitarPago(){          
     this.btnPagar.setVisible(true);
     this.txtTotal.setVisible(true);
     this.lblTotal.setVisible(true);
}
 private void deshabilitarPago(){
     this.btnPagar.setVisible(false);
     this.txtTotal.setVisible(false);
     this.txtTotal.setText("");
     this.lblTotal.setVisible(false);
 }

    @FXML
    private void cerrarPrograma(MouseEvent event) {
              Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("El ProGrama Se Cerrara!!");
                       
                     if (alerta.showAndWait().get() == ButtonType.OK){
                         this.cerrarVentana();         
                               }  
    }
    private void cerrarVentana() {
    Node source = (Node) panelPrincipal;
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
    
    private void checarCaja(){
        java.sql.Date fechaActual = this.fechaActual();
        if(!cajaController.checarCaja(fechaActual)){
            this.openFormCajaInicial();
        }
    }
    private boolean isDelDia(Boleta boleta){
        Date fechaActual = this.fechaActual();
        return (fechaActual.toString().equals(boleta.getFecha().toString()));
    }
    private boolean isCancelable(Boleta boleta){
        ArrayList<Jugada> jugadas = new ArrayList();
        jugadas = jugadaController.buscarJugadas(boleta.getId());
        String horaActual = jugadaController.hora();
       try{
         DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                  java.util.Date horaMatutina = dateFormat.parse("12:00");
                  java.util.Date horaVespertina =dateFormat.parse("14:30");
                  java.util.Date horaTarde =dateFormat.parse("17:30");
                  java.util.Date horaNocturna= dateFormat.parse("21:00");
                  java.util.Date hora= dateFormat.parse(horaActual);
                  
         for(Jugada jugada : jugadas){
   
            if(jugada.turno.equals("11")){
                if(hora.after(horaMatutina)){
                    return false;
                }
            }else if(jugada.turno.equals("14")){
                if(hora.after(horaVespertina)){
                    return false;
                }
            }else if(jugada.turno.equals("17")){
                if(hora.after(horaTarde)){
                    return false;
                }
            }else if(jugada.turno.equals("21")){
                if(hora.after(horaNocturna)){
                    return false;
                }
            }
        }return true;
      } catch (ParseException ex) {
            System.out.println("Posee errores");
           
        }            

 return true;
    }

    @FXML
    private void anularBoleta(MouseEvent event) {
        Boleta boleta = this.tblBoleta.getSelectionModel().getSelectedItem();
                      Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("Se Eliminara la Boleta : "+boleta.getId());
                       
                     if (alerta.showAndWait().get() == ButtonType.OK){
                         this.boletaController.eliminarBoleta(boleta.getId());
                         this.btnAnular.setVisible(false);
                         this.tblBoleta.getItems().remove(boleta);
                         this.tblBoleta.refresh();
                         this.tblJugada.getItems().clear();
                       }else{
                         this.btnAnular.setVisible(false);
                     } 
    }
}    