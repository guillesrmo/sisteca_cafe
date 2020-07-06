/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pro
 */
public class Service_RUC_DNI {

    public static void main(String[] args) throws MalformedURLException, IOException {
        String RUC = "http://apifacturalo-master.pe:8084/api/services/ruc/20549500553";
        String DNI = "https://apiperu.dev/api/dni/0481605";
        String respuesta = "";
        try {
            respuesta = peticionHttpGet(DNI);
         //   System.out.println("Web_Service.Service_RUC_DNI.main()"+respuesta);
            JSONArray jsonarray = new JSONArray("[" + respuesta + "]");

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                if (jsonobject.get("success").equals(true)) {

                    /*System.out.println("" + jsonobject.getJSONObject("data").getString("name"));
                System.out.println("" + jsonobject.getJSONObject("data").getString("address"));*/
                    System.out.println("" + jsonobject.getJSONObject("data").getString("numero"));
                    System.out.println("" + jsonobject.getJSONObject("data").getString("nombre_completo"));
                    System.out.println("" + jsonobject.getJSONObject("data").getString("nombres"));
                    System.out.println("" + jsonobject.getJSONObject("data").getString("apellido_paterno"));
                    System.out.println("" + jsonobject.getJSONObject("data").getString("apellido_materno"));
                    //System.out.println("" + jsonobject.getJSONObject("data").getString("fecha_nacimiento"));
//                    System.out.println("" + jsonobject.getJSONObject("data").getString("sexo"));
                } else {
                    System.out.println("" + jsonobject.get("success"));
                }
            }
            // System.out.println(respuesta);
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }

    }

    public static String peticionHttpGet(String urlParaVisitar) {
        // Esto es lo que vamos a devolver
        try {
            StringBuilder resultado = new StringBuilder();
            // Crear un objeto de tipo URL
            URL url = new URL(urlParaVisitar);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestProperty("Content-Type",
                    "application/json");
            conexion.setRequestProperty("Authorization",
                    
                    "Bearer 6b26f937b7afd0836dd49439ae6a8776c9e827f71f597bab3ad881f169a66892");
            conexion.setRequestMethod("GET");
            // Búferes para leer
            BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }
            // Cerrar el BufferedReader
            rd.close();
            // Regresar resultado, pero como cadena, no como StringBuilder
            return resultado.toString();
        } catch (Exception e) {
            return "{\n"
                    + "\"success\": \"ERROR NO ENCONTRO EL SERVIDOR\",\n"
                    + "\"message\": \"Ruc debe tener 11 dígitos\"\n"
                    + "}";
        }
    }

}
