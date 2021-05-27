
package controller;

import model.Boleta;
import model.Caja;
import utils.Conexion;
import model.Jugada;
import model.Redoblona;
import model.ResumenJugada;
import model.TextFieldResumen;
import Tickets.Ticket;
import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; 
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.text.Text;

public class ViewQuinielaController implements Initializable {

    private TextField txtMonto1;
    private Label lblNumero2;

    private ArrayList<String>quinielas;
    private ArrayList<String>numeros;
    private ArrayList<String>turnosHabilitados;
    private ArrayList<String>turnos;
    private ArrayList<String>Sposiciones;
    private ArrayList<Integer>Nposiciones;
    private ArrayList<Jugada>jugadas;
    private ArrayList<Jugada>jugadas2;
    private ArrayList<Boleta>boletas;
    private JugadaController jugadaController;
    private BoletaController boletaController; 
    private CajaController cajaController;
    private PagoController pagoController;
    private TextFieldResumenController textFieldController;
    private ObservableList<Jugada> obsTablaJugadas;
    private ArrayList<Redoblona> redoblonas;
    private ArrayList<ResumenJugada> jugadasResumen;
    private ArrayList<TextFieldResumen> textResumen;
    private ArrayList<TextFieldResumen> textResumenRedoblona;
    private ResumenJugada resumen;
    private StringBuilder vistaPrevia;
    private Jugada jugada;
    private Caja caja;
    private int tipo;
    private int total; 
     private int posicion2;
     private String numero1;
     private String numero2;
     private int monto;
     private String estado;
     private String user;
     private TextFieldResumen resumenSeleccionado;
     private int contResums;
     Conexion con;
    Connection cone2;
    private Label lblVistaPrevia;
    @FXML
    private AnchorPane panel;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblTicket;
    @FXML
    private Label lblFecha;
  
    
    @FXML
    private TextField txtMonto;
    @FXML
    private Button btnAgregar1;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtPosicion1;
    @FXML
    private Button btnAgregar2;
    @FXML
    private TextField txtPosicion2;
    @FXML
    private CheckBox chkRedoblona;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField txtQuiniela1;
    @FXML
    private TextField txtTurno1;
    private VBox panelResumen;
    @FXML
    private TextField txtNumero1;
    @FXML
    private TextField txtNumero2;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label lblNuemro2;
    @FXML
    private Label lblPosicion2;
    @FXML
    private TableView<ResumenJugada> tblJugadas;
    @FXML
    private TableColumn<?, ?> colN;
    @FXML
    private TableColumn<?, ?> colJugada;
    private ObservableList<ResumenJugada>jugadasTabla;
    public TextField txtHora;
    @FXML
    private Text textHora;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.jugadasTabla = FXCollections.observableArrayList();
        this.colN.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colJugada.setCellValueFactory(new PropertyValueFactory("resumen"));
        
        contResums = 1;       
        this.tipo=1;
        quinielas=new ArrayList();
        turnos=new ArrayList();
        numeros=new ArrayList();
        this.boletas=new ArrayList();
        Sposiciones=new ArrayList();
        Nposiciones=new ArrayList();
        jugadas=new ArrayList();
        this.deshablilitarNumero2();
        this.textResumenRedoblona = new ArrayList();
        jugadas2=new ArrayList();
        redoblonas = new ArrayList();
        this.estado="AGREGAR";
        this.textResumen = new ArrayList();
        txtPosicion1.setText("1");
        vistaPrevia = new StringBuilder();
        btnEliminar.setVisible(false);
        obsTablaJugadas = FXCollections.observableArrayList();
        jugadaController=new JugadaController();
        textFieldController = new TextFieldResumenController();
        jugadasResumen=new ArrayList();
        textResumen = new ArrayList();
        boletaController=new BoletaController();
        cajaController=new CajaController();
        pagoController = new PagoController();
           
        int total=0;
        lblFecha.setText(jugadaController.fecha());
        this.textHora.setText(jugadaController.hora());
        this.setHoras();           
       

            txtNombre.setOnKeyPressed(e->{
            switch(e.getCode()){
               
                case DOWN: this.txtTurno1.requestFocus(); ;break;
                case ENTER:txtTurno1.requestFocus() ;break;
                           }
               });
              
            txtTurno1.setOnKeyPressed(e->{
            switch(e.getCode()){
                case UP: this.txtNombre.requestFocus(); ;break;
                case DOWN: this.txtQuiniela1.requestFocus(); ;break;
                case ENTER:if(this.validarTurno(txtTurno1.getText()))this.txtQuiniela1.requestFocus();break;
                     case F1 :  if(turnos.contains("11")){turnos.remove("11");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("11");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                 this.txtNumero1.requestFocus();}break;
  
                 case F2 :  if(turnos.contains("14")){turnos.remove("14");
                           String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("14");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                  this.txtNumero1.requestFocus();}break;
                  
                 case F3 :  if(turnos.contains("17")){turnos.remove("17");
                           String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("17");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                  this.txtNumero1.requestFocus();}break;
                  
                 case F4 :  if(turnos.contains("21")){turnos.remove("21");
                           String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("21");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                  this.txtNumero1.requestFocus();}break;
                           }
               });
         
            txtQuiniela1.setOnKeyPressed(e->{
            switch(e.getCode()){
                case UP: this.txtTurno1.requestFocus(); break;
                case DOWN: this.txtNumero1.requestFocus(); ;break;
                case ENTER:if(this.validarQuiniela(txtQuiniela1.getText()))this.txtNumero1.requestFocus();break;
                 case F5 :  if(true){quinielas.clear();
                            quinielas.add("N");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;
                  
                 case F6 :   if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;
                  
                  case F7 :  if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;  
                   
                  case F8 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");quinielas.add("C");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                   
                   case F9 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");quinielas.add("C");quinielas.add("E");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                     case F10 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");quinielas.add("C");quinielas.add("E");quinielas.add("O");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                     case F11 :if(true){quinielas.clear();
                            quinielas.add("P");quinielas.add("F");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                     case F12 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("C");quinielas.add("E");quinielas.add("O");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 

                       } 
            
            });
            
           txtNumero1.setOnKeyPressed(e->{
            switch(e.getCode()){
                case UP: this.txtQuiniela1.requestFocus(); ;break;
               // case SPACE:this.cargarBoleta();break;
                case DOWN: this.txtPosicion1.requestFocus(); ;break;
                case ENTER:if(this.validarNumeros(txtNumero1.getText())){
                    //if(this.tipo==1)this.guardarSimple();
                       this.txtPosicion1.requestFocus();                       
                    }break;                         
                  case N :  if(quinielas.contains("N")){quinielas.remove("N");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                           }else { quinielas.add("N");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);}break;
                 case P :  if(quinielas.contains("P")){quinielas.remove("P");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                           }else { quinielas.add("P");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);}break;
                  case E :  if(quinielas.contains("E")){quinielas.remove("E");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                           }else { quinielas.add("E");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);}break;           
                  case F :  if(quinielas.contains("F")){quinielas.remove("F");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                           }else { quinielas.add("F");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);}break;
                   case C :  if(quinielas.contains("C")){quinielas.remove("C");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                           }else { quinielas.add("C");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);}break;
                   case O :  if(quinielas.contains("O")){quinielas.remove("O");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                           }else { quinielas.add("O");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);}break;                       
            }
               });
           
           
         txtPosicion1.setOnKeyPressed(e->{
         switch(e.getCode()){
         case DOWN: this.txtMonto.requestFocus(); ;break;
         case UP: this.txtNumero1.requestFocus(); ;break;
         case ENTER:if(this.validarPosicion1(txtPosicion1.getText()))this.txtMonto.requestFocus() ;break;
                           }
               });
           
           txtMonto.setOnKeyPressed(e->{
            switch(e.getCode()){
                case UP: this.txtPosicion1.requestFocus(); ;break;
                case DOWN:this.txtNumero2.requestFocus();
                 ;break;
                case ENTER:if(this.validarMonto(txtMonto.getText())){
                    if(this.tipo==1){
                        this.guardarSimple();
                        this.txtNumero1.requestFocus();
                        
                    }
                   else{
                        this.txtNumero2.requestFocus();
                    }
                }break;
                           }
               });
//            btnAgregar1.setOnKeyPressed(e->{
//            switch(e.getCode()){
//                case UP: this.txtMonto.requestFocus(); ;break;
//                case ENTER:this.guardarSimple() ;break;
//                           }
//               });
 
              
         txtNumero2.setOnKeyPressed((KeyEvent e)->{
         switch(e.getCode()){
             case UP: this.txtMonto.requestFocus(); ;break;
             case DOWN: this.txtPosicion2.requestFocus(); ;break;
          case ENTER:if(this.validarNumero2(txtNumero2.getText()))this.txtPosicion2.requestFocus(); ;break;                           }
               });
         
              txtPosicion2.setOnKeyPressed(e->{
         switch(e.getCode()){
         case UP: this.txtNumero2.requestFocus(); ;break;
         case LEFT: this.btnAgregar2.requestFocus() ;break;
         case ENTER:if(this.validarPosicion2(txtPosicion2.getText()))this.guardarDoble(txtNumero1.getText());break;//agregar
                           }
               });
                    btnAgregar2.setOnKeyPressed(e->{
            switch(e.getCode()){
                case UP: this.txtPosicion2.requestFocus(); break;
                case ENTER:this.guardarDoble(txtNumero1.getText()) ;break;
                           }
               });
               
               panel.setOnKeyReleased((javafx.scene.input.KeyEvent e) -> {
                 switch(e.getCode()){  
                 case ESCAPE :   Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("NO SE GUARDARAN LOS CAMBIOS!!");
                       
                     if (alerta.showAndWait().get() == ButtonType.OK){
                         this.cerrarVentana(); break;         
                               }  
                case SPACE:this.cargarBoleta();break;
                            case F1 :  if(turnos.contains("11")){turnos.remove("11");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("11");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                 this.txtNumero1.requestFocus();}break;
  
                 case F2 :  if(turnos.contains("14")){turnos.remove("14");
                           String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("14");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                  this.txtNumero1.requestFocus();}break;
                  
                 case F3 :  if(turnos.contains("17")){turnos.remove("17");
                           String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("17");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                  this.txtNumero1.requestFocus();}break;
                  
                 case F4 :  if(turnos.contains("21")){turnos.remove("21");
                           String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                             this.txtNumero1.requestFocus();
                           }else { turnos.add("21");
                            String s = this.setReset(turnos);
                            txtTurno1.setText(s);
                  this.txtNumero1.requestFocus();}break;
                  
                 case F5 :  if(true){quinielas.clear();
                            quinielas.add("N");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;
                  
                 case F6 :   if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;
                  
                  case F7 :  if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;  
                   
                  case F8 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");quinielas.add("C");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                   
                   case F9 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");quinielas.add("C");quinielas.add("E");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                     case F10 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("F");quinielas.add("C");quinielas.add("E");quinielas.add("O");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                     case F11 :if(true){quinielas.clear();
                            quinielas.add("P");quinielas.add("F");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break; 
                     case F12 :if(true){quinielas.clear();
                            quinielas.add("N");quinielas.add("P");quinielas.add("C");quinielas.add("E");quinielas.add("O");
                            String s = this.setReset(quinielas);
                            txtQuiniela1.setText(s);
                             this.txtNumero1.requestFocus();
                          }break;
                     case C : this.copiarResumen();
                          break;                          
                }          
              
    }
 ); 
//    TimerTask timerTask = new TimerTask()
//     {
//         public void run() 
//         {
//              textHora.setText(jugadaController.hora());
//         }
//     };
//
//     Timer timer = new Timer();
//     timer.scheduleAtFixedRate(timerTask, 0, 1000);   
     
 }             



private String setReset(ArrayList<String> turnos){
  StringBuilder sb = new StringBuilder();
  for(String s : turnos){
      sb.append(s);
      sb.append("-");
  }
  return sb.toString();
}

private void cerrarVentana() {
    Node source = (Node) panel;
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}
 
private void guardarSimple(){
    ArrayList<String>numerosValidados = new ArrayList();
     this.resetCacheImpresora();
           if(!chkRedoblona.isSelected()){
            if(this.validarNumeros(txtNumero1.getText())){
             for(String numero: numeros){
               if(validarJugadaSimple(numero)){
                   numerosValidados.add(numero);
               }else{ 
                   txtTurno1.requestFocus(); 
               return;
              }
             }
            }
             this.resumenTbl(Nposiciones, quinielas, turnos, txtNombre.getText(), total, numerosValidados, total, monto);
             txtNumero1.requestFocus();
             txtNumero1.setText("");
           }else{                     
             txtNumero2.requestFocus();  
           }
}
private void guardarDoble(String numero) {

   if(validarJugadaDoble(numero)){
     this.resumenRedoblonaTbl(Nposiciones, posicion2, quinielas, turnos, numero, numero2, monto);
              txtNumero1.requestFocus();
               txtNumero1.setText("");
                txtNumero2.setText("");
      }  else System.out.println("validacion error ");      
    } 

private boolean validarJugadaSimple(String numero){
     return validarNumero1(numero);
}

private boolean validarJugadaDoble(String numero){
          boolean aux =validarNumero1(numero) && validarNumero2();
          return aux;
}

private boolean validarNumero1(String numero){
if(this.validarTurno(txtTurno1.getText())){
  if(this.validarQuiniela(txtQuiniela1.getText())){
     if(this.validarNumero(numero)){
       if(this.validarPosicion1(txtPosicion1.getText())){  
        if(this.validarMonto(txtMonto.getText())){
                     return true;}
                           else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO MONTO");
                                alerta.showAndWait();
                                return false;}
                          }
                           else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO POSICION");
                                alerta.showAndWait();
                                return false;}
   
                         }
                          else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO NUMERO1");
                                alerta.showAndWait();
                                return false;}
    
                        }
                          else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO QUINIELA");
                                alerta.showAndWait();
                                return false;}
    
                        }
                           else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO TURNO");
                                alerta.showAndWait();
                                return false;}
    
}
private boolean validarNumero2(){  
   
     if(this.validarNumero2(String.valueOf(this.numero2))){
        if(this.validarPosicion2(String.valueOf(posicion2))){
              return true;
        }else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO POSICION2");
                                alerta.showAndWait();
                                return false;}
        }else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("NO ES VALIDO EL CAMPO NUMERO2");
                                alerta.showAndWait();
                                return false;}
    
       
}

private void prepararRdoblona(String nombre, String numero1, String numero2, int monto){//decrepado
        redoblonas=crearRedoblona(nombre, numero1, numero2, monto);
        for(Redoblona redoblona : redoblonas){
            jugadas.add(redoblona.getUno());
            jugadas.add(redoblona.getDos());
        }           
    }
    
    private ArrayList<Redoblona> crearRedoblona(String nombre, String numero1, String numero2, int monto) {//deprecado
               
                ArrayList<Redoblona> redoblonas=new ArrayList();
                int tipo=2;
                int cifras=2;
               
                Date fecha = new Date();
                java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
             
                String hora=jugadaController.hora();
                              
                for(int i=0;i<quinielas.size();i++){
                   for(int j=0;j<turnos.size();j++){     
                if(!(((turnos.get(j).equals("11"))||(turnos.get(j).equals("17"))) && (quinielas.get(i).equals("O")))){
                Jugada jugada1=new Jugada(tipo,numero1, cifras, Nposiciones.get(0), monto, quinielas.get(i), turnos.get(j),false); 
                Jugada jugada2=new  Jugada(tipo, numero2, cifras, posicion2, monto, quinielas.get(i), turnos.get(j), false); 
                Redoblona redoblona= new Redoblona(jugada1,jugada2);
                redoblonas.add(redoblona);
                   }
                }
                }
                return redoblonas;
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
    private void setRedoblona(MouseEvent event){ 
        if(chkRedoblona.isSelected()){
            habilitarNumero2();
            this.tipo=2;
        }
        else{
            deshablilitarNumero2();
            this.tipo=1;
        }
    }     

  private void habilitarNumero2() {

        lblPosicion2.setVisible(true);
        lblNuemro2.setVisible(true);
        txtPosicion2.setVisible(true);
        txtNumero2.setVisible(true);
        txtTurno1.requestFocus();
        btnAgregar2.setVisible(true);
        btnAgregar1.setVisible(false);
   
        this.Nposiciones.clear();
     }

    private void deshablilitarNumero2() {
       
        lblPosicion2.setVisible(false);
        lblNuemro2.setVisible(false);
        this.txtPosicion1.setText("");
        txtQuiniela1.setText("");
        txtTurno1.setText("");
        txtMonto.setText("");
        txtNumero1.setText("");
 
        txtTurno1.requestFocus();
        txtNumero2.setText("");
        txtPosicion2.setText("");

        txtPosicion2.setVisible(false);
        txtNumero2.setVisible(false);       
 
        btnAgregar2.setVisible(false);
        btnAgregar1.setVisible(true);

        this.Nposiciones.clear();
        chkRedoblona.setSelected(false);
        this.tipo = 1;
        }

    private void setPosicionTodas() {
             this.txtPosicion1.setText("11-14-17-21");
    }

    @FXML
    private void guardarBoleta(MouseEvent event) {
                this.cargarBoleta();}
    
private void cargarBoleta(){
       this.resetCacheImpresora();
       this.generarJugadas(this.jugadasTabla);  
        if(!jugadas.isEmpty()){
       int totalBoleta= Integer.parseInt(lblTotal.getText());//totalBoleta();
       String nombre = txtNombre.getText();
              
       if(boletaController.generarBoleta(totalBoleta,nombre)){
         this.jugadas.stream().forEach((n)->jugadaController.insertar(n));
         Boleta boleta=boletaController.consultarUltimaBoleta();  
         int idBoleta=boleta.getId();
         this.imprimirResumen(this.jugadasTabla, idBoleta,totalBoleta);/////7  tiene que imprimir las jugadas no el obsTablaJugadas
         this.resetCacheImpresora();
         this.tblJugadas.getItems().clear();
         this.jugadas2.addAll(jugadas);
         this.boletas.add(boleta);
         this.deshablilitarNumero2();
         txtNombre.setText("");
         txtNombre.requestFocus(); 
         this.total=0;
         this.lblTotal.setText("0");
         jugadas.clear();
       }  
    }else{
                                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("informacion");
                                alerta.setContentText("ERROR AL CARGAR JUGADAS");
                                alerta.showAndWait();
                                }
  }
public void resetTextField(){
    txtNumero1.setStyle("-fx-background-color: ##ffffff;");
    txtTurno1.setStyle("-fx-background-color: #ffffff;");
    txtQuiniela1.setStyle("-fx-background-color: #ffffff;");
    txtPosicion1.setStyle("-fx-background-color: #ffffff;");
    txtMonto.setStyle("-fx-background-color: #ffffff;");
    txtNumero1.setStyle("-fx-background-color: #ffffff;");
    txtNumero1.setStyle("-fx-background-color: #ffffff;");
}

public ArrayList<Jugada> getJugadas(){
    return this.jugadas2;
}


public void initAtributos(Jugada j,String user ) {
    this.user=user;
    System.out.println("////////////////////el usuario es :"+this.user);
        
        this.jugada=j;
//        tblJugadas.setVisible(false);
    
        jugadas.clear();
        jugadaController=new JugadaController();
        btnAgregar1.setVisible(false);
        btnAgregar2.setVisible(false);
        btnGuardar.setVisible(false);
        chkRedoblona.setDisable(false);
        
        jugadas.add(j);
       
        this.txtNombre.setText(jugada.getNombre());
        this.txtNumero1.setText(String.valueOf(jugada.getNumero()));
        this.txtMonto.setText(String.valueOf(jugada.getMonto()));
        this.lblTicket.setText(String.valueOf(j.getIdBoleta()));
        this.txtPosicion1.setText(String.valueOf(j.getPosicion()));                  
        lblFecha.setText(""+j.getFecha());
        this.txtQuiniela1.setText(j.getQuiniela());
        this.txtTurno1.setText(j.getTurno()); 
            
}

    @FXML
    private void eliminarResumen(MouseEvent event) {
       
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("ESTA SEGURO QUE QUIERE BORRAR ESTA JUGADA");
                                
      if (alerta.showAndWait().get() == ButtonType.OK){
           ResumenJugada resumen = this.tblJugadas.getSelectionModel().getSelectedItem();
           this.jugadasTabla.remove(resumen);
           this.tblJugadas.refresh();
           this.estado="AGREGAR";
           chkRedoblona.setVisible(true);
           this.chkRedoblona.setDisable(false);
           this.btnGuardar.setDisable(false);
           this.total=this.TotalBoletaResumen(this.jugadasTabla);
           this.lblTotal.setText(String.valueOf(this.total));
           btnEliminar.setVisible(false);          
             if(tipo==2){
              btnAgregar2.setText("AGREGAR");
              this.chkRedoblona.setSelected(true);
              this.habilitarNumero2();
              
             }else if(tipo==1){
                 btnAgregar1.setText("AGREGAR");
                 this.deshablilitarNumero2();
             }
          }
      else{
          this.estado="AGREGAR";
          this.btnGuardar.setDisable(false);
           btnGuardar.setVisible(true);
           btnEliminar.setVisible(false);
           this.chkRedoblona.setDisable(false);
           chkRedoblona.setVisible(true);
          if(tipo==1){ 
              btnAgregar1.setText("AGREGAR");
          }else  
             btnAgregar2.setText("AGREGAR");   
      }
}

private int TotalBoletaResumen(ObservableList<ResumenJugada>jugadasTabla){
    int totalBoleta=0;
    for( ResumenJugada tfr : jugadasTabla){
        totalBoleta = totalBoleta + this.totalResumen(tfr);
    }
    return totalBoleta;
}
private int totalResumen(ResumenJugada resumen){
     
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(resumen.getResumen());
  
  String tipo = resultado[0];
  
  String [] numeros = this.limpiarFormato(resultado[1]);
  
  String [] quinielas = this.limpiarFormato(resultado[2]);
 
  String [] turnos = this.limpiarFormato(resultado[3]);
  
  String [] posiciones = this.limpiarFormato(resultado[4]);
  
  int monto=Integer.parseInt(resultado[5]);
 
int totalResumen = 0;
if("R".equals(tipo)){  
    for(String quiniela : quinielas){
        for(String turno : turnos){//restriccion de jugada oros no juega a las 11hs ni a las 17hs
            if(!(quiniela.equals("O") && ((turno.equals("11") || turno.equals("17"))))){
                totalResumen = totalResumen + monto;
            }
        }
    }
 return totalResumen;
}else if("S".equals(tipo)) {  
   for(String quiniela : quinielas){
        for(String turno : turnos){//restriccion de jugada oros no juega a las 11hs ni a las 17hs
            if(!(quiniela.equals("O") && ((turno.equals("11") || turno.equals("17"))))){
                totalResumen = totalResumen + monto*numeros.length*posiciones.length;
            }
        }
    }
  return totalResumen;
}
return 0;
}

 private void generarResumen(ArrayList<Jugada> jugadas){
     this.panelResumen.getChildren().removeAll(panelResumen.getChildren());
     ArrayList<Integer>idRedoblona = new ArrayList();
     ArrayList<String>numeros = new ArrayList();
     ArrayList<Integer>posiciones = new ArrayList();
     ArrayList<String>quinielas = new ArrayList();
     ArrayList<String>turnos = new ArrayList();
       jugadasResumen.clear();
       textResumen.clear();
       
        if(!jugadas.isEmpty()){
         for(Jugada ja : jugadas){
             if(ja.getTipo()==1){
                 if(!numeros.contains(ja.getNumero())){
                  posiciones.clear();
                  quinielas.clear();
                  turnos.clear();
              String num=ja.getNumero();
                 numeros.add(num);
                 ArrayList<Jugada>aux = new ArrayList();
                 for(Jugada ja2 : jugadas){
                      if(ja2.getNumero().equals(num))aux.add(ja2);
                 }
                 for(Jugada ja3 : aux){
                 if(!posiciones.contains(ja3.getPosicion()))posiciones.add(ja3.getPosicion());
                 if(!quinielas.contains(ja3.getQuiniela()))quinielas.add(ja3.getQuiniela());
                 if(!turnos.contains(ja3.getTurno()))turnos.add(ja3.getTurno());
                  }
         
         String nombre= ja.getNombre();
         int tipo = ja.getTipo();
      String numero= ja.getNumero();
         int cifras = ja.getCifras();
         int monto = ja.getMonto();
         this.resumen= jugadaController.generarResumenJugadas(posiciones, quinielas, turnos, nombre, tipo, numero, cifras, monto);
         this.jugadasResumen.add(resumen);
            
             TextFieldResumen nuevo = new TextFieldResumen(contResums, resumen.getResumen().toString());
             nuevo.setText(resumen.getResumen().toString());
             nuevo.setStyle("-fx-font-size: 16pt;");
             textResumen.add(nuevo);
             this.panelResumen.getChildren().add(nuevo);
             
         }   
     }    
   }
 } 
       
 }        
 
private void generarResumenRedoblona(ArrayList<Jugada> jugadas) {
             this.redoblonas=listarRedoblonas(jugadas);
              ArrayList<Integer> idRedoblona=new ArrayList();
             
              for(Redoblona r : redoblonas){
                  ArrayList<Integer>posiciones = new ArrayList();
                  ArrayList<String>quinielas = new ArrayList();
                  ArrayList<String>turnos = new ArrayList();
                  String aux1 = r.getUno().getNumero();
                  String aux2 = r.getDos().getNumero();
                if(!idRedoblona.contains(r.getUno().getIdRedoblona())){   
                   for(Redoblona r2 : redoblonas){  
                       if(r2.getUno().getNumero().equals(aux1) && r2.getDos().getNumero().equals(aux2)){
                            idRedoblona.add(r2.getUno().getIdRedoblona());
                              if(!posiciones.contains(r2.getUno().getPosicion()))posiciones.add(r2.getUno().getPosicion());
                              if(!posiciones.contains(r2.getDos().getPosicion()))posiciones.add(r2.getDos().getPosicion());
                              if(!quinielas.contains(r2.getUno().getQuiniela()))quinielas.add(r2.getUno().getQuiniela());
                              if(!turnos.contains(r2.getUno().getTurno()))turnos.add(r2.getUno().getTurno());
                       }
                   }
       String numero1= aux1;
        String numero2= aux2;
         int monto = r.getUno().getMonto();
         this.resumen= jugadaController.generarResumenRedoblona(posiciones, quinielas, turnos, numero1, numero2,  monto);
         this.jugadasResumen.add(resumen);
          //por el momento no es necesario distingir entre resumenes de simples y dobles ya que los resumenes no tiene relevancia en la app  
             TextFieldResumen nuevo = new TextFieldResumen(contResums, resumen.getResumen().toString());
             nuevo.setText(resumen.getResumen().toString());
             nuevo.setStyle("-fx-font-size: 14pt;");
             textResumen.add(nuevo);
             this.panelResumen.getChildren().add(nuevo);
          }
  }
 }


private ArrayList<Redoblona> listarRedoblonas(ArrayList<Jugada>jugadas){
    ArrayList<Redoblona>auxRedoblonas=new ArrayList();
    ArrayList<Integer>idRedoblona=new ArrayList();
    for(Jugada j : jugadas){
        if(j.getTipo()==2){
            if(!idRedoblona.contains(j.getIdRedoblona())){
                Redoblona r=this.armarRedoblona(j);
                idRedoblona.add(j.getIdRedoblona());
                auxRedoblonas.add(r);
            }
        }
    }
    return auxRedoblonas;
}
        

private Redoblona armarRedoblona(Jugada jugada){
    if(jugada.getTipo()==2){
        Jugada pareja=this.buscarPareja(jugada);
        if(jugada.getPosicion()<pareja.getPosicion()){
            Redoblona redoblona=new Redoblona(jugada,pareja);
            return redoblona;
        }else if(jugada.getPosicion()>pareja.getPosicion()){
            Redoblona redoblona=new Redoblona(pareja,jugada);
            return redoblona;
    }
}return null; 
}   
     
private Jugada buscarPareja(Jugada jugada1){
    if(jugada1.getIdRedoblona()!=0){
        
        for(Jugada j : this.jugadas){
            if((j.getIdRedoblona()== jugada1.getIdRedoblona()) && (j.getNumero()!= jugada1.getNumero()))return j;
        }return null;
    }return null;
}

 private void setMonto(KeyEvent event) {
        if(!txtMonto.getText().isEmpty()){
        if(this.esNumero(txtMonto.getText())){
         StringBuilder aux2 = new StringBuilder();
         aux2.append("$");
         aux2.append(txtMonto.getText());
            }else txtMonto.setText("");
        }
    }

private void resetCacheImpresora() {
    
    
//Ticket ticket = new Ticket();
Ticket.resetCabezalineas();
Ticket.resetItems();
Ticket.resetLineasPie();
Ticket.resetSubCabezalineas();
Ticket.resetTotals();

    } 

    private void setNumero1(KeyEvent event) {
          String filtroNombre = this.txtNumero1.getText();
          if(!txtNumero1.getText().isEmpty()){
              if(esNumero(filtroNombre)){
                 if(this.jugadaController.contarCifras(filtroNombre)<=4){
                  }
          }
          else{
          this.txtNumero1.setText("");
        }
    }
}

private boolean validarTurno(String t) {
    
    StringBuilder sb = new StringBuilder();
    String turno=t;
    String[] resultado=limpiarFormato(turno);
      ArrayList<String> turnosValidos = validarHoraiosTurno(resultado);
      if(!turnosValidos.isEmpty()){
     txtTurno1.setStyle("-fx-background-color: #BAFF9B;");
     for(String s: turnosValidos){
         sb.append(s);
         sb.append("-");
     }
     txtTurno1.setText(sb.toString());
     this.turnos = turnosValidos;
     return true;
     }else{
         txtTurno1.setStyle("-fx-background-color: #FF7637;");}
          this.txtTurno1.requestFocus();
         return false;
     }     
  
public boolean validarFormato(String turno) {
    return turno.matches("^([11,14,17,21]{2}[-]?){1,5}$");
}

public ArrayList<String> validarHoraiosTurno(String[] turno) {
    ArrayList todosTurnos = this.setHoras();
    ArrayList<String> misTurnos = new ArrayList();
    
    for(String s : turno){
     if(s.length()==2){
         if(esNumero(s)){
          if( todosTurnos.contains(s) && !misTurnos.contains(s))misTurnos.add(s);
          if( todosTurnos.contains(s) && !misTurnos.contains(s))misTurnos.add(s);
          if( todosTurnos.contains(s) && !misTurnos.contains(s))misTurnos.add(s);
          if( todosTurnos.contains(s) && !misTurnos.contains(s))misTurnos.add(s);
         }
        else if("**".equals(s)){
         if(todosTurnos.contains("11"))misTurnos.add("11");
         if(todosTurnos.contains("14"))misTurnos.add("14");
         if(todosTurnos.contains("17"))misTurnos.add("17");
         if(todosTurnos.contains("21"))misTurnos.add("21");
         return misTurnos;
          }
        }  
     }
    return misTurnos;
 }
 
private ArrayList<String> setHoras() {
       ArrayList turnosHabilitados2 = new ArrayList();
       String horaActual = jugadaController.hora();
        try {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                  Date horaMatutina = dateFormat.parse("12:00");
                  Date horaVespertina =dateFormat.parse("14:30");
                  Date horaTarde =dateFormat.parse("17:30");
                  Date horaNocturna= dateFormat.parse("21:00");
                  Date hora= dateFormat.parse(horaActual);
                                      
                if (hora.before(horaMatutina)){
                      turnosHabilitados2.add("11");
                      turnosHabilitados2.add("14");
                      turnosHabilitados2.add("17");
                      turnosHabilitados2.add("21");
                      return turnosHabilitados2;
                }
               else if (hora.before(horaVespertina)){
                      turnosHabilitados2.add("14");
                      turnosHabilitados2.add("17");
                      turnosHabilitados2.add("21");
                      return turnosHabilitados2;
                }
              else if (hora.before(horaTarde)){
                      turnosHabilitados2.add("17");
                      turnosHabilitados2.add("21");
                      return turnosHabilitados2;
                }
              else if (hora.before(horaNocturna)){
                    turnosHabilitados2.add("21");
                    return turnosHabilitados2;
                 }
                
        } catch (ParseException ex) {
            System.out.println("Posee errores");
           
        }
        return turnosHabilitados2;
}

private boolean validarQuiniela(String q) {

    this.quinielas.clear();
    StringBuilder sb = new StringBuilder();
    String quiniela=q;
    String[] resultado=limpiarFormato(quiniela);
    agregarQuinielas(resultado, quinielas);
     if(!quinielas.isEmpty()){
         txtQuiniela1.setStyle("-fx-background-color: #BAFF9B;");
       for(String s: this.quinielas){
         sb.append(s);
         sb.append("-");
     }
      txtQuiniela1.setText(sb.toString());
     return true;
     
     }else{
         txtQuiniela1.setStyle("-fx-background-color: #FF7637;");
         this.txtQuiniela1.requestFocus();
         return false;
     }   
  }

public boolean validarFormatoQuiniela(String turno) {
    return turno.matches("^([n|p|f|e|c|o|N|P|F|E|C|O]{1}[-]?){1,6}$");
}
public  String[] limpiarFormato(String quinielas) {
    
  String patron = "-";
  Pattern p1 = Pattern.compile(patron);
   String[] resultado= p1.split(quinielas);
 
   return resultado;
}
public void agregarQuinielas(String[] quini, ArrayList<String> quinielas) {
  
  for(String s : quini){
      if(s.length()==1){
        if(!esNumero(s)){
         if("N".equals(s.toUpperCase()) && !quinielas.contains(s.toUpperCase()))quinielas.add(s.toUpperCase());
         if("P".equals(s.toUpperCase()) && !quinielas.contains(s.toUpperCase()))quinielas.add(s.toUpperCase());
         if("F".equals(s.toUpperCase()) && !quinielas.contains(s.toUpperCase()))quinielas.add(s.toUpperCase()); 
         if("E".equals(s.toUpperCase()) && !quinielas.contains(s.toUpperCase()))quinielas.add(s.toUpperCase());
         if("C".equals(s.toUpperCase()) && !quinielas.contains(s.toUpperCase()))quinielas.add(s.toUpperCase());
         if("O".equals(s.toUpperCase()) && !quinielas.contains(s.toUpperCase()))quinielas.add(s.toUpperCase());
   
     }
 }else if("**".equals(s)){
         quinielas.clear();
         quinielas.add("N");
         quinielas.add("P");
         quinielas.add("F");
         quinielas.add("E");
         quinielas.add("C");
         quinielas.add("O");
         return;
       }      
     }
 }

private boolean validarNumero(String numero) {
      if(this.esNumero(numero) && (numero.length()<=4)){
          return true;
      }else{
         return false;
      }
}

private boolean validarNumeros(String text) {
   numeros.clear();
   String[] resultado = limpiarFormato(text);
 
 for(String s: resultado){
    if(validarNumero(s)){
           numeros.add(s);
   }else{
         txtNumero1.setStyle("-fx-background-color: #FF7637;"); 
         this.txtNumero1.requestFocus();
         return false;
      }
  }
 txtNumero1.setStyle("-fx-background-color: #BAFF9B;");
 return true;
} 


private boolean validarPosicion1(String text) {
            this.Nposiciones.clear();
            StringBuilder sb = new StringBuilder();
            String posiciones=text;
            String[] resultado=limpiarFormato(posiciones);
           agregarPosiciones(resultado, Nposiciones);
           
     if(!Nposiciones.isEmpty()){
         if(!chkRedoblona.isSelected()){
         txtPosicion1.setStyle("-fx-background-color: #BAFF9B;");
       for(Integer s: Nposiciones){
         sb.append(String.valueOf(s));
         sb.append("-");
     }
      txtPosicion1.setText(sb.toString());
     return true;
     }else if (chkRedoblona.isSelected()){
         if(Nposiciones.size()>1){
             txtPosicion1.setStyle("-fx-background-color: #FF7637;");
         this.txtPosicion1.requestFocus();
         return false;
         }
     else {
           txtPosicion1.setStyle("-fx-background-color: #BAFF9B;");
           sb.append(String.valueOf(text));
           txtPosicion1.setText(sb.toString());
       return true;
     }
     }
     }else{
         txtPosicion1.setStyle("-fx-background-color: #FF7637;");
          this.txtPosicion1.requestFocus();
         return false;    
     }return false;
}
private void agregarPosiciones(String[] resultado, ArrayList<Integer> Nposiciones) {
    for(String s : resultado){
      if(esNumero(s)){
          if(Integer.parseInt(s)<21){
               if(!Nposiciones.contains(s))Nposiciones.add(Integer.parseInt(s));
         
        }
 }
}
}

private boolean validarPosicion2(String text) {
 if(esNumero(text)){
    if(!Nposiciones.isEmpty()){
      if(Integer.parseInt(text)<21 && Integer.parseInt(text)>4 && Integer.parseInt(text)>=Nposiciones.get(0)){
                  this.posicion2=Integer.parseInt(text);
                   txtPosicion2.setStyle("-fx-background-color: #BAFF9B;");  
                     return true;
              }
              txtPosicion2.setStyle("-fx-background-color: #FF7637;");
              this.txtPosicion2.requestFocus();
              return false;
              }
              txtPosicion2.setStyle("-fx-background-color: #FF7637;");
              this.txtPosicion2.requestFocus();
              return false;
              
              }
              txtPosicion2.setStyle("-fx-background-color: #FF7637;");
              this.txtPosicion2.requestFocus();
              return false;
  }

private boolean validarMonto(String text) {
            
  if(this.esNumero(text)){
      if(text.length()<=4){
          monto=Integer.parseInt(text);
          txtMonto.setStyle("-fx-background-color: #BAFF9B;");
          return true;
      }
  }else{
         txtMonto.setStyle("-fx-background-color: #FF7637;"); 
         this.txtMonto.requestFocus();
         return false;
    }return false;
  }

private boolean validarNumero2(String text) {
            
  if(this.esNumero(text)){
      if(text.length()<=4){
          this.numero2= text;
          txtNumero2.setStyle("-fx-background-color: #BAFF9B;");
           System.out.println("el numero uno es :"+numero2);
          return true;
      }else{
         txtNumero2.setStyle("-fx-background-color: #FF7637;"); 
         this.txtNumero2.requestFocus();
         return false;
      }
  }else{
    txtNumero2.setStyle("-fx-background-color: #FF7637;");
  //  this.txtNumero2VistaPrevia.setText("");
    this.txtNumero2.requestFocus();
    return false;
} 
    }

private void editarResumen(ResumenJugada resumen ) {
    System.out.println("entro a editar resumen simple");
  this.tipo=1; 
  this.btnEliminar.setVisible(true);
  this.estado="EDITAR";
  btnAgregar1.setText("EDITAR");
  this.chkRedoblona.setSelected(false);
  this.deshablilitarNumero2();
  this.chkRedoblona.setVisible(false);
  this.btnGuardar.setDisable(true);
          
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(resumen.getResumen());
  txtNumero1.setText(resultado[1]);
  validarNumeros(txtNumero1.getText());
  
  txtQuiniela1.setText(resultado[2]);
  validarQuiniela(txtQuiniela1.getText());
  
  txtTurno1.setText(resultado[3]);
  validarTurno(txtTurno1.getText());
  
  txtPosicion1.setText(resultado[4]);
  validarPosicion1(txtPosicion1.getText());
  
  txtMonto.setText(resultado[5]);
  validarMonto(txtMonto.getText());

}
   
private void copiarResumen(){
        this.estado = "AGREGAR";
        this.btnAgregar1.setText("COPIAR");
}

private void editarResumenRedoblona(ResumenJugada resumen) {
    System.out.println("entro a editar redoblona");
  this.tipo=2;
  this.btnEliminar.setVisible(true);
  this.estado="EDITAR";
  btnAgregar2.setText("EDITAR");
  this.chkRedoblona.setSelected(true);
  this.chkRedoblona.setDisable(true);
  this.btnGuardar.setDisable(true);
  
  this.habilitarNumero2();
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(resumen.getResumen());
    
  String [] numeros=this.limpiarFormato(resultado[1]);
  String [] posiciones = this.limpiarFormato(resultado[4]);
  
  txtNumero1.setText(numeros[0]);
    System.out.println("numero uno "+txtNumero1.getText());
  validarNumeros(txtNumero1.getText());
  txtNumero2.setText(numeros[1]);
  System.out.println("numero uno "+txtNumero2.getText());
  validarNumeros(txtNumero2.getText());
      
  txtQuiniela1.setText(resultado[2]);
  validarQuiniela(txtQuiniela1.getText());
  
  txtTurno1.setText(resultado[3]);
   validarTurno(txtTurno1.getText());
   
  txtPosicion1.setText(posiciones[0]);
  validarPosicion1(txtPosicion1.getText());
  
  txtPosicion2.setText(posiciones[1]);
  validarPosicion2(txtPosicion2.getText());
  
  txtMonto.setText(resultado[5]);
  validarMonto(txtMonto.getText());
  
   Nposiciones.clear();
   Nposiciones.add(Integer.parseInt(txtPosicion1.getText()));
        
}
private void generarJugadas(ObservableList<ResumenJugada>jugadasTabla){
    String nombre=txtNombre.getText();
    for(ResumenJugada rj : jugadasTabla){
            this.Nposiciones.clear();
            this.quinielas.clear();
            this.turnos.clear();
            this.numeros.clear();
            String patron = "_";
            Pattern p1 = Pattern.compile(patron);
            String[] resultado= p1.split(rj.getResumen()); 
            String tipo= resultado[0];
            String [] numeros = this.limpiarFormato(resultado[1]);
            for(String n : numeros){this.numeros.add(n);}
            int monto=Integer.parseInt(resultado[5]);
            String [] posiciones = this.limpiarFormato(resultado[4]);
            for(String p : posiciones){Nposiciones.add(Integer.parseInt(p));}
            String [] quinielas = this.limpiarFormato(resultado[2]);
            for(String q : quinielas){this.quinielas.add(q);}
            String [] turnos = this.limpiarFormato(resultado[3]);
            for(String t : turnos){this.turnos.add(t);}
            
            if("R".equals(tipo)){
                generarJugadasDobles(nombre,numeros,monto,posiciones,quinielas,turnos);
            }else if("S".equals(tipo)){
                generarJugadasSimples(nombre,numeros,monto,posiciones,quinielas,turnos);
            }           
    }
    
}
private void generarJugadasSimples(String nombre, String [] numeros, int monto, String [] posiciones, String [] quinielas, String [] turnos){ 
  
    for(String na : this.numeros){
      int cifras=na.length();
      ArrayList<Jugada> aux = jugadaController.generarJugada(Nposiciones, this.quinielas, this.turnos, nombre, tipo, na, cifras, monto); 
      aux.stream().forEach((n)->this.jugadas.add(n));
     } 
   }

private void generarJugadasDobles(String nombre, String [] numeros, int monto, String [] posiciones, String [] quinielas, String [] turnos) {

  String numero1 = numeros[0];
  String numero2 = numeros[1];
  int cifras=2;
  int tipo=2;
  Date fecha = new Date();
  java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
  String hora=jugadaController.hora();
  for(String quiniela : quinielas){
     for(String turno : turnos){     
        if(!(((turno.equals("11"))||(turno.equals("17"))) && (quiniela.equals("O")))){
                Jugada jugada1=new Jugada(tipo,numero1, cifras, Nposiciones.get(0), monto, quiniela, turno,false); 
                Jugada jugada2=new  Jugada(tipo, numero2, cifras, posicion2, monto, quiniela, turno, false); 
                Redoblona redoblona= new Redoblona(jugada1,jugada2);
                this.jugadas.add(redoblona.getUno());
                this.jugadas.add(redoblona.getDos());
                   }
                }
  }
}

private boolean setHoras(Jugada j) {
         String horaActual = jugadaController.hora();
        try {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                  java.util.Date horaMatutina = dateFormat.parse("12:00");
                  java.util.Date horaVespertina =dateFormat.parse("14:30");
                  java.util.Date horaTarde =dateFormat.parse("17:15");
                  java.util.Date horaNocturna= dateFormat.parse("21:00");
                  java.util.Date hora= dateFormat.parse(horaActual);
                
                if(j.getTurno().contains("11")){   
                    
                  if (hora.before(horaMatutina)){
                      return true;
                      } else   return false;}
        
               else if (j.getTurno().contains("14")){
                    if (hora.before(horaVespertina)){
                        
                      return true;
                    } else   return false;}  
               
               else if (j.getTurno().contains("17")){
                    if (hora.before(horaTarde)){
                      return true;
                    } else   return false;} 
                
               else if (j.getTurno().contains("21")){
                    if (hora.before(horaNocturna)){
                      return true;
                    } else   return false;} 
                
        } catch (ParseException ex) {
            System.out.println("Posee errores");
           
        }return false;
}
public int tiempoJugada(Jugada j) {
        java.util.Date fechaInicial = j.getFecha();
        java.util.Date fechaFinal = new java.util.Date();
        int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        return dias;
    }

private void eliminarJugada(MouseEvent event) {
 
    Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("ESTA SEGURO QUE QUIERE BORRAR ESTA JUGADA");
                       
      if (alerta.showAndWait().get() == ButtonType.OK){
          
          this.jugadas.stream().forEach((n)->jugadaController.eliminarJugada(n));
           
      }              
        this.cerrarVentana();
      }
 private void eliminarBoleta( int idBoleta){
                    
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                                alerta.setHeaderText(null);
                                alerta.setTitle("ALERTA");
                                alerta.setContentText("ESTA SEGURO QUE QUIERE BORRAR ESTA JUGADA");
                                
      if (alerta.showAndWait().get() == ButtonType.OK){
         if(boletaController.eliminarBoleta(idBoleta))jugadaController.eliminarJugada(idBoleta);
         }    
      
      else{
              btnEliminar.setVisible(false);
          }
      }   

private void imprimirResumen(ObservableList<ResumenJugada>jugadasTabla, int idBoleta,int totalBoleta) {
ArrayList<ResumenJugada> resumens = new ArrayList();
ArrayList<ResumenJugada> primera = new ArrayList();
ArrayList<ResumenJugada> matutina = new ArrayList();
ArrayList<ResumenJugada> tarde = new ArrayList();
ArrayList<ResumenJugada> nocturna = new ArrayList();
//ArrayList<String> numeros=new ArrayList();
for(ResumenJugada r : jugadasTabla){  
    
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getResumen());
  String st = resultado[1];
  String [] numeros = this.limpiarFormato(resultado[1]);
  String nS= r.getResumen().toString(); 
  int id = 1;  
  if(resultado[0].equals("S")){
  for(String n : numeros){
      StringBuilder stResumen = new StringBuilder();
      String  nS2 = nS.replaceFirst(st, n);
      stResumen.append(nS2);
      ResumenJugada r2 = new ResumenJugada(String.valueOf(id), stResumen);
      resumens.add(r2);
      id++;
  }
  resumens.stream().forEach((n)->System.out.println(n.getResumen()));
}else if(resultado[0].equals("R")){
    resumens.add(r);
}
}
//resumenRedoblona.stream().forEach((n)->resumens.add(n));
Date date=new Date();

SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
String hora=jugadaController.hora();

Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora);//.format(date));

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 32 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANE Y COBRE YA ...");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

 String nombre=txtNombre.getText();

ticket.AddSubCabecera("boleta : "+idBoleta);
ticket.AddSubCabecera(ticket.DarEspacio());
ticket.AddSubCabecera("Nombre : "+nombre);
ticket.AddSubCabecera(ticket.DarEspacio());

 ArrayList<Jugada>aux = new ArrayList();   
for(ResumenJugada r : resumens){  
   String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getResumen());
  
  String [] turnos = this.limpiarFormato(resultado[2]);
  for(String t : turnos){this.turnos.add(t);}
    if(this.turnos.contains("11")){
        primera.add(r);}
    if(this.turnos.contains("14")){
        matutina.add(r);} 
    if(this.turnos.contains("17")){
        tarde.add(r);} 
    if(this.turnos.contains("21")){
        nocturna.add(r);}   
}
if(!primera.isEmpty()){
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("PRIMERA:");
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("NUMERO POS  QUINIELA   $$$ ");
  ticket.AddSubCabecera(ticket.DarEspacio());
    for(ResumenJugada r : primera){ 
    this.Nposiciones.clear();
    this.quinielas.clear();
    this.turnos.clear();
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getResumen());
 int tipo;
 tipo =(resultado[0].equals("S"))? 1 : 2;
  
  String numero = resultado[1];
  String monto = resultado[5];
  String posiciones = resultado[4];
  String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
            if(!q.equals("O")){
                sbq.append(q);
                sbq.append("-");
            }
        }
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
    StringBuilder sb = new StringBuilder("                           ");
            
  sb.replace(0,4, String.valueOf(numero));
     sb.replace(8,9,String.valueOf(posiciones));
     sb.replace(12,19,String.valueOf(quinielas));
     //if(sb.length()<22)     
       sb.setLength(27);
     sb.replace(23,27, String.valueOf(monto));
  ticket.AddSubCabecera(sb.toString());    
  ticket.AddSubCabecera(ticket.DarEspacio());
     }
     
///////////////////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera(ticket.DarEspacio());
}
if(!matutina.isEmpty()){
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("MATUTINA:");
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("NUMERO POS  QUINIELA   $$$ ");
  ticket.AddSubCabecera(ticket.DarEspacio());
    for(ResumenJugada r : matutina){  
    this.Nposiciones.clear();
    this.quinielas.clear();
    this.turnos.clear();
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getResumen());
  int tipo=1;
  String numero = resultado[1];
  String monto=resultado[5];
  String posiciones = resultado[4];
  String quinielas1 = resultado[2];
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
    StringBuilder sb = new StringBuilder("                           ");
      
     sb.replace(0,4, String.valueOf(numero));
     sb.replace(8,9,String.valueOf(posiciones));
     sb.replace(12,19,String.valueOf(quinielas));
     sb.setLength(27);
     sb.replace(23,27, String.valueOf(monto));
  ticket.AddSubCabecera(sb.toString());    
  ticket.AddSubCabecera(ticket.DarEspacio());
     }
    
///////////////////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera(ticket.DarEspacio());
}  

if(!tarde.isEmpty()){
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("TARDE:");
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("NUMERO POS  QUINIELA   $$$ ");
  ticket.AddSubCabecera(ticket.DarEspacio());
    for(ResumenJugada r : tarde){  
    this.Nposiciones.clear();
    this.quinielas.clear();
    this.turnos.clear();
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getResumen());
  int tipo=1;
  String numero = resultado[1];
  String monto=resultado[5];
  String posiciones = resultado[4];
    String[] quini = limpiarFormato(resultado[2]);
  StringBuilder sbq = new StringBuilder();
        for(String q : quini){
            if(!q.equals("O")){
                sbq.append(q);
                sbq.append("-");
            }
        }
  String quinielas1 = sbq.toString();
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
    StringBuilder sb = new StringBuilder("                           ");
      
     sb.replace(0,4, String.valueOf(numero));
     sb.replace(8,9,String.valueOf(posiciones));
     sb.replace(12,19,String.valueOf(quinielas));
     sb.setLength(27);
     sb.replace(23,27, String.valueOf(monto));
  ticket.AddSubCabecera(sb.toString());    
  ticket.AddSubCabecera(ticket.DarEspacio());
     }
    
///////////////////////////////////////////////////////////////////////////////////////////////

ticket.AddSubCabecera(ticket.DarEspacio());
}
if(!nocturna.isEmpty()){
     ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("NOCTURNA:");
  ticket.AddSubCabecera(ticket.DarEspacio());
  ticket.AddSubCabecera("NUMERO POS  QUINIELA   $$$ ");
  ticket.AddSubCabecera(ticket.DarEspacio());
    for(ResumenJugada r : nocturna){  
    this.Nposiciones.clear();
    this.quinielas.clear();
    this.turnos.clear();
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(r.getResumen());
  int tipo=1;
  String numero = resultado[1];
  String monto=resultado[5];
  String posiciones = resultado[4];
  String quinielas1 = resultado[2];
  String quinielasT = "TODAS";
  String quinielas;
  quinielas=(quinielas1.length()==12)? quinielasT : quinielas1;
    StringBuilder sb = new StringBuilder("                           ");
      
     sb.replace(0,4, String.valueOf(numero));
     sb.replace(8,9,String.valueOf(posiciones));
     sb.replace(12,19,String.valueOf(quinielas));
     sb.setLength(27);
     sb.replace(23,27, String.valueOf(monto));
  ticket.AddSubCabecera(sb.toString());    
  ticket.AddSubCabecera(ticket.DarEspacio());
     }
      
///////////////////////////////////////////////////////////////////////////////////////////////


ticket.AddSubCabecera(ticket.DarEspacio());
}
///////////////////////////////////////////////////////////////////////////////////////////////
//this.total = this.TotalBoletaResumen(resumens);
///////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddTotal("",ticket.DibujarLinea(29));

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("TOTAL $"," "+totalBoleta);

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("",ticket.DarEspacio());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("(RECLAMOS DENTRO DE LAS 48HS)");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("Gracias por su visita");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DibujarLinea(29));

/////////////////////////////////////////////////////////////////////////////////
ticket.ImprimirDocumento();
        
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

    @FXML
    private void agregarNumero1(MouseEvent event) {
       this.guardarSimple();
    }

    @FXML
    private void agregarNumero2(MouseEvent event) {
       this.guardarDoble(this.txtNumero1.getText());
    }

    @FXML
    private void setTurno1(KeyEvent event) {
    }

    @FXML
    private void seleccionarResumen(MouseEvent event) {
        this.seleccionarResumen();
    }    
    
private void seleccionarResumen(){
  
  ResumenJugada resumenJugada = this.tblJugadas.getSelectionModel().getSelectedItem();
  if(resumenJugada != null){        
      
  String patron = "_";
  Pattern p1 = Pattern.compile(patron);
  String[] resultado= p1.split(resumenJugada.getResumen());
  String type = resultado[0];
  System.out.println("entro a entro a seleccionar resumen con type : "+type);
   if("R".equals(type)){
         System.out.println("entro a entro a editar resumenJugadaRedoblona");
      this.editarResumenRedoblona(resumenJugada);
  }else if("S".equals(type)){
        System.out.println("entro a seleccionar resumenJugada");
      this.editarResumen(resumenJugada);
  }
 }  
} 
    
  private void resumenTbl(ArrayList<Integer> posiciones, ArrayList<String>quinielas, ArrayList<String> turnos, String  nombre, int tipo, ArrayList<String> numeros, int cifras, int monto) {
  StringBuilder resumenJugada= new StringBuilder();
     resumenJugada.append("S");
     resumenJugada.append("_");
      for(String n : numeros){
         resumenJugada.append(n);
         resumenJugada.append("-");
     }
      resumenJugada.append("_");
     for(String q : quinielas){
         resumenJugada.append(q);
         resumenJugada.append("-");
     }
     resumenJugada.append("_");
     for(String t : turnos){
         resumenJugada.append(t);
         resumenJugada.append("-");
     }
     resumenJugada.append("_");
    for(Integer p : posiciones){
        resumenJugada.append(String.valueOf(p));
        resumenJugada.append("-");
    }
     resumenJugada.append("_");    
     resumenJugada.append(String.valueOf(monto));
if(this.estado.contains("AGREGAR")){
     ResumenJugada newResumenJugada = new ResumenJugada(Integer.toString(contResums), resumenJugada);
     this.jugadasTabla.add(newResumenJugada);
     this.tblJugadas.setItems(jugadasTabla); 
     contResums++;
            
      this.total= this.TotalBoletaResumen(this.jugadasTabla);
      this.lblTotal.setText(String.valueOf(this.total));
      this.btnAgregar1.setText("AGREGAR");
     }
else if(this.estado.contains("EDITAR")){
     ResumenJugada resumen = this.tblJugadas.getSelectionModel().getSelectedItem();
     resumen.setResumen(resumenJugada);
     this.tblJugadas.refresh();
     this.estado="AGREGAR";
    this.btnAgregar1.setText("AGREGAR");
    this.btnGuardar.setDisable(false);
    this.chkRedoblona.setDisable(false);
    this.chkRedoblona.setVisible(true);
    this.total=this.TotalBoletaResumen(this.jugadasTabla);
    this.lblTotal.setText(String.valueOf(this.total));
    btnEliminar.setVisible(false);
}
      
}

private void resumenRedoblonaTbl(ArrayList<Integer> posiciones,int posicion2, ArrayList<String> quinielas, ArrayList<String> turnos, String numero1, String numero2, int monto) {
    
    StringBuilder resumen= new StringBuilder();
     resumen.append("R");
     resumen.append("_");
     resumen.append(numero1);
     resumen.append("-");
     resumen.append(numero2);
      resumen.append("_");
     for(String q : quinielas){
         resumen.append(q);
         resumen.append("-");
     }
     resumen.append("_");
     for(String t : turnos){
         resumen.append(t);
         resumen.append("-");
     }
     resumen.append("_");
    for(Integer p : posiciones){
        resumen.append(String.valueOf(p));
        resumen.append("-");
    }
    resumen.append(String.valueOf(posicion2));
     resumen.append("_");
    
     resumen.append(String.valueOf(monto));
if(this.estado.contains("AGREGAR")){
           
     ResumenJugada newResumenJugada = new ResumenJugada(Integer.toString(contResums), resumen);
     this.jugadasTabla.add(newResumenJugada);
     this.tblJugadas.setItems(jugadasTabla); 
     contResums++;
            
      this.total=this.TotalBoletaResumen(jugadasTabla);
      this.lblTotal.setText(String.valueOf(this.total));
      this.btnAgregar2.setText("AGREGAR");
}
else if(this.estado.contains("EDITAR")){     
    ResumenJugada resumen2 = this.tblJugadas.getSelectionModel().getSelectedItem();
     resumen2.setResumen(resumen);
     this.tblJugadas.refresh();
     this.estado="AGREGAR";
    this.btnAgregar2.setText("AGREGAR");
    this.btnGuardar.setDisable(false);
    this.chkRedoblona.setDisable(false);
    this.chkRedoblona.setVisible(true);
    this.total=this.TotalBoletaResumen(this.jugadasTabla);
    this.lblTotal.setText(String.valueOf(this.total));
    btnEliminar.setVisible(false);
 }   
} 

}
    
   


        

 
  

    

     


    
 




