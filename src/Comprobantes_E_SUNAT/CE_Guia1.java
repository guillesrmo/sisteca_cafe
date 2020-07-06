/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comprobantes_E_SUNAT;

/**
 *
 * @author User
 */
import Controller.Controller_Nubefact;
import guia_remision.leer_conexion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CE_Guia1 {


    /*
#########################################################
#### PASO 1: CONSEGUIR LA RUTA Y TOKEN ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# - Regístrate gratis en www.nubefact.com/register
# - Ir la opción API (Integración).
# IMPORTANTE: Para que la opción API esté activada necesitas escribirnos a soporte@nubefact.com o llámanos al teléfono: 01 468 3535 (opción 2) o celular (WhatsApp) 955 598762.
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */
//  RUTA para enviar documentos
    private String RUTA = "https://eu135.chat-api.com/instance137954/sendMessage?token=q2a0cchqkwzidruo";
  //  private String RUTA = "https://www.pse.pe/api/v1/576036088bc04375a924acc3e41760abfd1686843c8841718df8a062ffc7481f";
//  TOKEN para enviar documentos    
     String TOKEN = "eyJhbGciOiJIUzI1NiJ9.IjEyODAyMWIyZjhlNTQxMWE5OWI3MzJkMGExNzA4ZmU2YzNmYzg5MDYwZGI0NGViOWE2ZTIxODE0Y2ZmNmZhOGMi.jtMWs6RWz7w-B2gVzvJ8E6w5iWdExTHWdUXxmQowj3M";
  //  private String TOKEN = "eyJhbGciOiJIUzI1NiJ9.ImY4ZmYwMWU3MjkyMzQ2Y2Q4N2VkYTQ2ZTk2ZDZjMjRjNzRmYmEzNDU2NDk1NDk3MDhmMmFjZmUwN2ZmOGU3NGEi.5pe0glXxI4stL3Xll5b_75IWSvw_LuZh74c2sICkjj0";

    public void apiConsume(String numero) {
        List<String> respuesta = new ArrayList<String>();
        try {
         //   List<String> guia = new Controller_Nubefact().Guia_persona(id_Venta);
            /*
#########################################################
#### PASO 2: GENERAR EL ARCHIVO PARA ENVIAR A NUBEFACT ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# - MANUAL para archivo JSON en el link: https://goo.gl/WHMmSb
# - MANUAL para archivo TXT en el link: https://goo.gl/Lz7hAq
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
             */
            HttpClient cliente = new DefaultHttpClient();
            HttpPost post = new HttpPost(RUTA);
         //   post.addHeader("Authorization", "Token token=" + TOKEN); // Cabecera del token
            post.addHeader("Content-Type", "application/json"); // Cabecera del Content-Type

            JSONObject objetoCabecera = new JSONObject(); // Instancear el  segundario

            objetoCabecera.put("phone", "051"+numero);
            objetoCabecera.put("body", "ENCUESTA DE SATISFACCION PARA LOS INTEGRANTES DE CAE , RELLENE EL FORMUALARIO INGRESANDO AL LINK  https://docs.google.com/forms/d/e/1FAIpQLSevoKClWsVNieUwjMPQd2QAGDzbQhVA5QPOs7GvxW5yYS8UZg/viewform?usp=sf_link . DE PARTE DE CONSORCIO MADRE DE DIOS PROVEEDOR DEL PNAE QALI WARMA. CUALQUIER CONSULTA COMUNICARSE CON ESTE NUMERO AREA DE DISTRIBUCION");
            


      
            /*
#########################################################
#### PASO 3: ENVIAR EL ARCHIVO A NUBEFACT ####
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
# SI ESTÁS TRABAJANDO CON ARCHIVO JSON
# - Debes enviar en el HEADER de tu solicitud la siguiente lo siguiente:
# Authorization = Token token="8d19d8c7c1f6402687720eab85cd57a54f5a7a3fa163476bbcf381ee2b5e0c69"
# Content-Type = application/json
# - Adjuntar en el CUERPO o BODY el archivo JSON o TXT
# SI ESTÁS TRABAJANDO CON ARCHIVO TXT
# - Debes enviar en el HEADER de tu solicitud la siguiente lo siguiente:
# Authorization = Token token="8d19d8c7c1f6402687720eab85cd57a54f5a7a3fa163476bbcf381ee2b5e0c69"
# Content-Type = text/plain
# - Adjuntar en el CUERPO o BODY el archivo JSON o TXT
+++++++++++++++++++++++++++++++++++++++++++++++++++++++
             */

            StringEntity parametros = new StringEntity(objetoCabecera.toString(), "UTF-8");
            post.setEntity(parametros);
            HttpResponse response = cliente.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String linea = "";
            if ((linea = rd.readLine()) != null) {

                JSONParser parsearRsptaJson = new JSONParser();
                JSONObject json_rspta = (JSONObject) parsearRsptaJson.parse(linea);
                System.out.println("Comprobantes_E_SUNAT.CE_Guia1.apiConsume()"+json_rspta);
                //System.out.println(json_rspta.get("enlace"));
                /*System.out.println(json_rspta.get("tipo_de_comprobante"));
                System.out.println(json_rspta.get("serie"));
                System.out.println(json_rspta.get("numero"));
                System.out.println(json_rspta.get("enlace"));
                System.out.println(json_rspta.get("aceptada_por_sunat"));
                System.out.println(json_rspta.get("sunat_description"));
                System.out.println(json_rspta.get("sunat_note"));
                System.out.println(json_rspta.get("sunat_responsecode"));
                System.out.println(json_rspta.get("sunat_soap_error"));
                System.out.println(json_rspta.get("pdf_zip_base64"));
                System.out.println(json_rspta.get("xml_zip_base64"));
                System.out.println(json_rspta.get("cdr_zip_base64"));
                System.out.println(json_rspta.get("cadena_para_codigo_qr"));
                System.out.println(json_rspta.get("codigo_hash"));*/
              /*  respuesta.add(json_rspta.get("tipo_de_comprobante").toString());
                respuesta.add(json_rspta.get("serie").toString());
                respuesta.add(json_rspta.get("numero").toString());
                respuesta.add(json_rspta.get("enlace").toString());
                respuesta.add(json_rspta.get("aceptada_por_sunat").toString());
                respuesta.add(json_rspta.get("sunat_description").toString());
                respuesta.add(json_rspta.get("sunat_note").toString());
                respuesta.add(json_rspta.get("sunat_responsecode").toString());
                respuesta.add(json_rspta.get("sunat_soap_error").toString());
                respuesta.add(json_rspta.get("pdf_zip_base64").toString());
                respuesta.add(json_rspta.get("xml_zip_base64").toString());
                respuesta.add(json_rspta.get("cdr_zip_base64").toString());
                respuesta.add(json_rspta.get("cadena_para_codigo_qr").toString());
                respuesta.add(json_rspta.get("codigo_hash").toString());
                //descargar_pdf(json_rspta.get("enlace")+".pdf",directorio+"\\"+json_rspta.get("numero")+"-"+factura.get(4)+"-"+json_rspta.get("tipo_de_comprobante")+"-"+json_rspta.get("serie")+"-"+json_rspta.get("numero")+"-"+factura.get(1)+"-"+factura.get(2)+".pdf");

                if (json_rspta.get("errors") != null) {
                    JOptionPane.showMessageDialog(null, "Error => " + json_rspta.get("errors"), "Alerta!", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error => " + json_rspta.get("errors"));
                } else {

                    JSONParser parsearRsptaDetalleOK = new JSONParser();
                    JSONObject json_rspta_ok = (JSONObject) parsearRsptaDetalleOK.parse(json_rspta.get("invoice").toString());

                }*/
            }
        } catch (UnsupportedEncodingException ex1) {
            System.err.println("Error UnsupportedEncodingException: " + ex1.getMessage());
        } catch (IOException ex2) {
            System.err.println("Error IOException: " + ex2.getMessage());
        } catch (Exception ex3) {
            System.err.println("Exepction: " + ex3.getMessage());
        }
      
    }
}
