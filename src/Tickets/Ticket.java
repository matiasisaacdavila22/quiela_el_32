
 /*
char[] CORTAR_PAPEL=new char[]{0x1B,'m'}; // codigo q corta el papel
imp.write(CORTAR_PAPEL); // mando a la impreosra

el codigo para abrir una gaveta de dinero

char ABRIR_GAVETA[]={(char)27,(char)112,(char)0,(char)10,(char)100};
imp.write(ABRIR_GAVETA);
*/
package Tickets;


import java.util.ArrayList;

import javax.print.Doc;

import javax.print.DocFlavor;

import javax.print.DocPrintJob;

import javax.print.PrintService;

import javax.print.PrintServiceLookup;

import javax.print.SimpleDoc;

import javax.swing.JOptionPane;

public class Ticket{

static ArrayList<String> CabezaLineas=new ArrayList<String>();

static ArrayList<String> subCabezaLineas=new ArrayList<String>();

static ArrayList<String> items=new ArrayList<String>();

static ArrayList<String> totales=new ArrayList<String>();

static ArrayList<String> LineasPie=new ArrayList<String>();

public static void resetCabezalineas(){
    CabezaLineas.clear();
}

public static void resetSubCabezalineas(){
    subCabezaLineas.clear();
}
public static void resetItems(){
    items.clear();
}
public static void resetTotals(){
    totales.clear();
}
public static void resetLineasPie(){
    LineasPie.clear();
}

public static void AddCabecera(String line){
    CabezaLineas.add(line);}

public static void AddSubCabecera(String line){
    subCabezaLineas.add(line);}

public static void AddItem(String cantidad,String item,String price){
    OrdenItem newItem = new OrdenItem(' ');

items.add(newItem.GeneraItem(cantidad,item, price));

}

public static void AddTotal(String name,String price){

OrderTotal newTotal = new OrderTotal(' ');

totales.add(newTotal.GeneraTotal(name, price));

}

public static void AddPieLinea(String line){
    LineasPie.add(line);}

public static String DibujarLinea(int valor){
String raya="";for(int x=0;x<valor;x++){raya+="=";}
return raya;

}


public static String DarEspacio(){
    return "\n";}

public static void ImprimirDocumento(){
String cadena="";

for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++ ){
    cadena+=CabezaLineas.get(cabecera);
}

for(int subcabecera=0;subcabecera<subCabezaLineas.size(); subcabecera++){
    cadena+=subCabezaLineas.get(subcabecera);
}

for(int ITEM=0;ITEM<items.size();ITEM++){
    cadena+=items.get (ITEM);
}

for(int total=0;total<totales.size();total++)
{cadena+=totales.get(total);
}

for(int pie=0;pie<LineasPie.size();pie++){
    cadena+=LineasPie.get(pie);
}

 

DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

PrintService service = PrintServiceLookup.lookupDefaultPrintService();

DocPrintJob pj = service.createPrintJob();

byte[]bytes =cadena.getBytes();

Doc doc = new SimpleDoc(bytes, flavor,null);

try{

pj.print(doc,null);


}catch(Exception e){ }

}

}
