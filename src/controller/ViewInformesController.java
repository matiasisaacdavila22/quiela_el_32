/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Reportes.reporteGanada;
import Reportes.reporteJugada;
import Reportes.reportePagada;
import Reportes.reporteResumens;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.WindowConstants;
import model.Boleta;
import model.Jugada;
import model.Resumen;
import model.Pago;
import controller.JugadaController;
import controller.ResumenAgrupadoController;
import model.ResumenAgrupado;
/**
 * FXML Controller class
 *
 * @author holasur
 */
public class ViewInformesController implements Initializable {

    @FXML
    private DatePicker dpDesde;
    @FXML
    private DatePicker dpHasta;
    @FXML
    private TableView<Boleta> tblBoletas;
    @FXML
    private TableColumn<?, ?> colBId;
    @FXML
    private TableColumn<?, ?> colBMonto;
    @FXML
    private TableView<Boleta> tblGanadas;
    @FXML
    private TableColumn<?, ?> colGId;
    @FXML
    private TableColumn<?, ?> colGMonto;
    @FXML
    private TableView<Boleta> tblPagadas;
    @FXML
    private TableColumn<?, ?> colPId;
    @FXML
    private TableColumn<?, ?> colPMonto;
    @FXML
    private Text lblFacturado;
    @FXML
    private Text lblPagos;
    @FXML
    private Text lblBalanceTotal;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnInformesBoletas;
    @FXML
    private Button btnInformeGanadas;
    @FXML
    private Button btnInformesPagadas;
    @FXML
    private Text lblBJugadas;
    @FXML
    private Text lblBTotal;
    @FXML
    private Text lblGGanadas;
    @FXML
    private Text lblGTotoal;
    @FXML
    private Text lblPPagadas;
    @FXML
    private Text lblPTotal;
    @FXML
    private AnchorPane panelInformes;
    
    private ObservableList<Boleta>boletas;
    private ObservableList<Boleta>boletasPagadas;
    private ObservableList<Boleta>boletasGanadas;
    private ArrayList<Jugada>jugadas;
    private BoletaController boletaController;
    private PagoController pagoController;
    private ResumenController resumenController;
    private JugadaController jugadaController;
    private ResumenAgrupadoController resumenAgrupadoController;
    
    private java.sql.Date fecha;
    private java.sql.Date fecha2;
    @FXML
    private TableView<?> tblResumen;
    @FXML
    private TableColumn<?, ?> colPId1;
    @FXML
    private TableColumn<?, ?> colPMonto1;
    @FXML
    private Button btnInformeResumens;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.jugadaController=new JugadaController();
       this.boletaController=new BoletaController();
       this.pagoController = new PagoController();
       this.resumenController = new ResumenController();
       this.resumenAgrupadoController = new ResumenAgrupadoController();
       
       this.jugadas = new ArrayList();
       this.boletas = FXCollections.observableArrayList();
       this.boletasPagadas = FXCollections.observableArrayList();
       this.boletasGanadas = FXCollections.observableArrayList();
       
       this.colBId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colPId.setCellValueFactory(new PropertyValueFactory("id"));
         this.colGId.setCellValueFactory(new PropertyValueFactory("id"));
//       this.colBFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
//       this.colBNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       this.colBMonto.setCellValueFactory(new PropertyValueFactory("total"));
       this.colPMonto.setCellValueFactory(new PropertyValueFactory("total"));
       this.colGMonto.setCellValueFactory(new PropertyValueFactory("total"));
//       this.colBEstado.setCellValueFactory(new PropertyValueFactory("estado"));
//       this.colBPagado.setCellValueFactory(new PropertyValueFactory("pagado"));
//       this.colBFechaPago.setCellValueFactory(new PropertyValueFactory("fechaPago"));
//       this.colBTotalPago.setCellValueFactory(new PropertyValueFactory("totalPago")); 
       panelInformes.setOnKeyReleased((javafx.scene.input.KeyEvent e) -> {
                 switch(e.getCode()){  
                 case ESCAPE : 
                         this.cerrarVentana(); break;         
                               } 
    });
 }                       
    
    public java.sql.Date fechaActual(){
     java.util.Date fecha = new java.util.Date();
     java.sql.Date fechaSql=new java.sql.Date(fecha.getTime());
     return fechaSql;
 } 
   public void setFecha(){  
   if(dpDesde.getValue()!=null){  
     if(dpHasta.getValue() == null){   
     LocalDate ld = dpDesde.getValue();
     java.util.Date date = java.sql.Date.valueOf(ld);
     this.fecha=(java.sql.Date) date;
     this.boletas = this.boletaController.consultarxFecha(fecha);
     this.mostrarBoletas(this.boletas);
        }else if(dpHasta.getValue() != null){  
     LocalDate ld = dpDesde.getValue();
     java.util.Date date = java.sql.Date.valueOf(ld);
     this.fecha=(java.sql.Date) date;
     
     LocalDate ld2 = dpHasta.getValue();
     java.util.Date date2 = java.sql.Date.valueOf(ld2);
     this.fecha2=(java.sql.Date) date2;     
     this.boletas = this.boletaController.consultarEntreFechas(fecha,fecha2);
     this.mostrarBoletas(this.boletas);
     dpHasta.setValue(null);
        }  
     } 
}
   public void mostrarBoletas( ObservableList<Boleta> boletas){
      
        ObservableList<Boleta>boletasCheckPagos;
        boletasCheckPagos = FXCollections.observableArrayList(); 
           
        boletasCheckPagos = this.pagoController.checarPagos(boletas);
        boletasPagadas = this.boletasPagadas(boletas);
        this.boletasGanadas = this.boletasGandas(boletas);
        
        this.tblGanadas.setItems(boletasGanadas);
        this.tblPagadas.setItems(boletasPagadas);
        this.tblBoletas.setItems(boletasCheckPagos); 
        
        this.lblBJugadas.setText(""+boletas.size());
        this.lblGGanadas.setText(""+boletasGanadas.size());
        this.lblPPagadas.setText(""+boletasPagadas.size());
        
        int totalBoletas = this.calcularTotalBoletas(boletas);
        int totalGanadas = this.calcularTotalGanadas(boletasGanadas);
        int totalPagadas = this.calcularTotalPagadas(boletasPagadas);
        int totalBalance = totalBoletas - totalPagadas;
        
        this.lblBTotal.setText(""+totalBoletas);
        this.lblGTotoal.setText(""+totalGanadas);
        this.lblPTotal.setText(""+totalPagadas);
        
        this.lblFacturado.setText(""+totalBoletas);
        this.lblPagos.setText(""+totalPagadas);
        this.lblBalanceTotal.setText(""+totalBalance);
       }
      private void cerrarVentana() {
    Node source = (Node) panelInformes;
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
}

    @FXML
    private void buscaBoletas(MouseEvent event) {
        this.setFecha();
    }
    private ObservableList<Boleta> boletasPagadas(ObservableList<Boleta> boletas){
        ObservableList<Boleta> aux = FXCollections.observableArrayList();
        boletas.stream().filter(boleta -> (boleta.isPagado())).forEachOrdered(boleta -> {
            aux.add(boleta);
        });
        return aux;
    }
    
    private ObservableList<Boleta> boletasGandas(ObservableList<Boleta> boletas){
        ObservableList<Boleta> aux = FXCollections.observableArrayList();
        boletas.stream().filter(boleta -> (boleta.isEstado())).forEachOrdered(boleta -> {
            aux.add(boleta);
        });
        return aux;
    }
    private int calcularTotalBoletas(ObservableList<Boleta> boletas){
        int aux = 0;
        for(Boleta boleta : boletas){
            aux = aux + boleta.getTotal();
        }
        return aux;
    }
    private int calcularTotalGanadas(ObservableList<Boleta> boletas){
        int aux = 0;
        for(Boleta boleta : boletas){     
            int aux2 = pagoController.calcularPago2(boleta);
            aux = aux + aux2;
           }
        return aux;
    }
    
    private int calcularTotalPagadas(ObservableList<Boleta> boletas){
        int aux = 0;
        for(Boleta boleta : boletas){
            aux = aux + boleta.getTotalPago();
        }
        return aux;
    }

    @FXML
    private void generarInformeBoletas(MouseEvent event) {
        this.prepararJugadas(this.boletas);
       
          try{
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/jugadas.jasper"));
            
            JasperPrint jprint = JasperFillManager.fillReport(report, null, reporteJugada.getDataSource(this.jugadas));
            
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
        }catch(JRException ex){
            ex.getMessage();
        }
            
    
    }

    @FXML
    private void informeGanadas(MouseEvent event) {
         this.prepararGanadas(this.boletasGanadas);
                try{
        
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/ganadas.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report, null, reporteGanada.getDataSource(this.jugadas));
            
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
        }catch(JRException ex){
            ex.getMessage();
        } 
    } 
    public void prepararJugadas(ObservableList<Boleta> boletas){
        boletas.forEach(boleta -> {
            this.jugadas.addAll(this.traerJugadas(boleta));
            });
    }
    
   public ArrayList<Jugada> traerJugadas(Boleta boleta){
       ArrayList<Jugada> j = new ArrayList();
       j = this.jugadaController.buscarJugadas(boleta.getId());
       return j;
    } 
   
   public void prepararGanadas(ObservableList<Boleta> boletas){
       this.jugadas.clear();
       ArrayList<Jugada> aux = new ArrayList();
       for(Boleta boleta : boletas){
           this.jugadas.addAll(this.traerJugadas(boleta));
       }
       for(Jugada jugada : jugadas){
           if(jugada.isGano()){
               aux.add(jugada);
               Pago pago = pagoController.buscarPagoIdBoleta(jugada.getIdBoleta());
            if(pago != null){
                Date fecha = pago.getFechaPago();
                jugada.setFecha((java.sql.Date) fecha);
                jugada.setTotalPago(pago.getMonto());
              }
           }
       }
       this.jugadas = aux;
       }

    @FXML
    private void informerPagadas(MouseEvent event) {
       this.prepararPagadas(this.boletasPagadas);
                try{
        
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/pagadas.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report, null, reportePagada.getDataSource(this.jugadas));
            
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
        }catch(JRException ex){
            ex.getMessage();
        }  
    }
    
      public void prepararPagadas(ObservableList<Boleta> boletas){
       this.jugadas.clear();
       ArrayList<Jugada> aux = new ArrayList();
       for(Boleta boleta : boletas){
           this.jugadas.addAll(this.traerJugadas(boleta));
       }
       for(Jugada jugada : jugadas){
           if(jugada.isGano()){
               aux.add(jugada);
               Pago pago = pagoController.buscarPagoIdBoleta(jugada.getIdBoleta());
            if(pago != null){
                Date fecha = pago.getFechaPago();
                jugada.setFecha((java.sql.Date) fecha);
                jugada.setTotalPago(pago.getMonto());
              }
           }
       }
       this.jugadas = aux;
       }

    @FXML
    private void informerResumen(MouseEvent event) {
       ObservableList<Resumen> resumens = FXCollections.observableArrayList();
       resumens = this.prepararResumens(this.boletas);
      ArrayList<ResumenAgrupado> resumenes = this.resumenAgrupadoController.listResumenAgrupados(resumens);
     //  System.out.println("el resumen es que generamos de resumens :"+resumens.get(0).getTurno());
                try{
        
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/resumens.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report, null, reporteResumens.getDataSource(resumenes));
            
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
            
        }catch(JRException ex){
            ex.getMessage();
        } 
    }
    
    public ObservableList<Resumen> prepararResumens(ObservableList<Boleta> boletas){
        ObservableList<Resumen> resumens = FXCollections.observableArrayList(); 
        boletas.forEach(boleta -> {
            resumens.addAll(this.resumenController.crearResumen(boleta.getId()));
            });
        return resumens;
    }
                
}
            

    

