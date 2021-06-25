/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;


import Tickets.Ticket;
import java.text.SimpleDateFormat;

import java.util.*;

public class TicketJugada {
    
    public static void main(String[] args) {
    
Date date=new Date();

SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");

SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");

Ticket ticket = new Ticket();

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(""+fecha.format(date) + " " + hora.format(date));

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("** EL 33 **");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera("JUEGUE GANE Y COBRE YA MYRAN");

ticket.AddCabecera(ticket.DarEspacio());

ticket.AddCabecera(ticket.DibujarLinea(29));

ticket.AddCabecera(ticket.DarEspacio());

/////////////////////////////////////////////////////////////////////////////////////
ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DarEspacio());

ticket.AddSubCabecera(ticket.DibujarLinea(29));

ticket.AddSubCabecera(ticket.DarEspacio());
/////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddItem("1","Articulo Prueba","15.00");

ticket.AddItem("","",ticket.DarEspacio());

ticket.AddItem("2","Articulo Prueba","25.00");

ticket.AddItem("","",ticket.DarEspacio());
///////////////////////////////////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("(RECLAMOS DENTRO DE LAS 48HS");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea("Gracias por su visita");

ticket.AddPieLinea(ticket.DarEspacio());

ticket.AddPieLinea(ticket.DibujarLinea(29));

ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DarEspacio());
ticket.AddPieLinea(ticket.DibujarLinea(29));
ticket.AddPieLinea(ticket.DibujarLinea(29));
/////////////////////////////////////////////////////////////////////////////////
ticket.ImprimirDocumento();

}
}
 

    


