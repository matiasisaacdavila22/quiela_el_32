
package Tickets;


import java.text.SimpleDateFormat;

import java.util.*;

public class Main {
    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
      


Date date=new Date();

SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");

SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");

Ticket ticket = new Ticket();


ticket.AddCabecera("RESTAURANTE XXXX");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("EXPEDIDO EN: ----------");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("AV. TAMAULIPAS NO. 5 LOC. 101");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("LIMA, XXXXXXXXXXXX");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("RFC: CSI-020226-MV4");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera("Caja # 1 - Ticket # 1");

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera("LE ATENDIO: JUAN");

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(""+fecha.format(date) + " " + hora.format(date));

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DibujarLinea(29));

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddItem("1","Articulo Prueba","15.00");

ticket.AddItem("","",ticket.DarEspacio());

ticket.AddItem("2","Articulo Prueba","25.00");

ticket.AddItem("","",ticket.DarEspacio());

ticket.AddTotal("",ticket.DibujarLinea(29));

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("SUBTOTAL","29.75");

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("IVA","5.25");

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("TOTAL","35.00");

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("RECIBIDO","50.00");

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("CAMBIO","15.00");

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddTotal("",ticket.DarEspacio());

ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("EL xxx ES NUESTRA PASION...");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("VIVE LA EXPERIENCIA EN xxx");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("Gracias por su visita");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.ImprimirDocumento();

}

}