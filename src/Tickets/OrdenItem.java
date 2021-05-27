/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;


public class OrdenItem{

   char[] temp=new char[]{ ' ' };

public OrdenItem(char delimit){
    temp=new char[]{delimit };
    }

public String GetItemCantidad(String ordenItem){

       String[] delimitado=ordenItem.split(""+temp);

return delimitado[0];

}

public String GetItemNombre(String ordenItem){

String[] delimitado=ordenItem.split(""+temp);

return delimitado[1];

}

public String GetItemPrecio(String ordenItem){

String[] delimitado=ordenItem.split(""+temp);

return delimitado[2];

}

public String GeneraItem(String cantidad, String nombre, String precio){
    return cantidad+temp[0]+nombre+temp[0]+precio;}

}
