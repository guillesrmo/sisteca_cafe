/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guia_remision;


/**
 *
 * @author memo
 */
public class pre {
    public static void main(String[] args) {
        int a=0;
        int b=0;
        int c=1;
     /*   for (int i = 27; i < 77; i++) {
            
            System.out.println("JSONObject detalle_linea_"+c+"= new JSONObject();"); 
            System.out.println("if (guia.size()>"+(i+b+1)+") {"); 
            System.out.println("detalle_linea_"+c+".put(\"unidad_de_medida\",\"NIU\");"); 
            System.out.println("detalle_linea_"+c+".put(\"codigo\", guia.get("+(i+b)+"));");
            System.out.println("detalle_linea_"+c+".put(\"descripcion\", guia.get("+(i+b+1)+"));" +"");
            //System.out.println("detalle_linea_"+i+".put(\"descripcion\", factura.get("+(i+21+b)+")+\" \"+factura.get("+(i+22+b)+")+\" \"+factura.get("+(i+23+b)+")+\" \"+factura.get("+(i+24+b)+")+\" \"+factura.get("+(i+25+b)+")+\" \"+factura.get("+(i+26+b)+"));");
            System.out.println("detalle_linea_"+c+".put(\"cantidad\", guia.get("+(i+b+2)+"));"); 
            System.out.println("}");            
            a=a+1;
            b=b+2;
            c++;
        }*/
        for (int i = 27; i < 77; i++) {
            
            System.out.println("if (guia.size()>"+(i+a+1)+") {"); 
            System.out.println("lista.add(detalle_linea_"+c+");"); 
            System.out.println("}");            
            a=a+2;
            c++;
        
    }
    }
    
}
