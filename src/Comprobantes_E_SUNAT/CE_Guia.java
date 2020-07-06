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

public class CE_Guia {


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
   // private String RUTA = "https://www.pse.pe/api/v1/535c025ba60e49498765a79ecad3fb56bcbd578b80484a07857db20ba20feacd";
    private String RUTA = "https://www.pse.pe/api/v1/576036088bc04375a924acc3e41760abfd1686843c8841718df8a062ffc7481f";
//  TOKEN para enviar documentos    
    // String TOKEN = "eyJhbGciOiJIUzI1NiJ9.IjEyODAyMWIyZjhlNTQxMWE5OWI3MzJkMGExNzA4ZmU2YzNmYzg5MDYwZGI0NGViOWE2ZTIxODE0Y2ZmNmZhOGMi.jtMWs6RWz7w-B2gVzvJ8E6w5iWdExTHWdUXxmQowj3M";
    private String TOKEN = "eyJhbGciOiJIUzI1NiJ9.ImY4ZmYwMWU3MjkyMzQ2Y2Q4N2VkYTQ2ZTk2ZDZjMjRjNzRmYmEzNDU2NDk1NDk3MDhmMmFjZmUwN2ZmOGU3NGEi.5pe0glXxI4stL3Xll5b_75IWSvw_LuZh74c2sICkjj0";

    public List<String> apiConsume(String id_Venta) {
        List<String> respuesta = new ArrayList<String>();
        try {
            List<String> guia = new Controller_Nubefact().Guia_persona(id_Venta);
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
            post.addHeader("Authorization", "Token token=" + TOKEN); // Cabecera del token
            post.addHeader("Content-Type", "application/json"); // Cabecera del Content-Type

            JSONObject objetoCabecera = new JSONObject(); // Instancear el  segundario

            objetoCabecera.put("operacion", "generar_guia");
            objetoCabecera.put("tipo_de_comprobante", "7");
            objetoCabecera.put("serie", guia.get(1));
            objetoCabecera.put("numero", guia.get(2));
            objetoCabecera.put("cliente_tipo_de_documento", "6");
            objetoCabecera.put("cliente_numero_de_documento", "20563818728");
            objetoCabecera.put("cliente_denominacion", "COMITE DE COMPRA MADRE DE DIOS 1");
            objetoCabecera.put("cliente_direccion", "PJ. VICTOR RAUL H.DE LA TORRE LT. 14 MZ. 5P - MADRE DE DIOS TAMBOPATA TAMBOPATA");
            objetoCabecera.put("cliente_email", "");
            objetoCabecera.put("cliente_email_1", "");
            objetoCabecera.put("cliente_email_2", "");
            objetoCabecera.put("fecha_de_emision", guia.get(8));
            objetoCabecera.put("observaciones", "");
            objetoCabecera.put("motivo_de_traslado", guia.get(9));
            objetoCabecera.put("peso_bruto_total", guia.get(10));
            objetoCabecera.put("numero_de_bultos", "0");
            objetoCabecera.put("tipo_de_transporte", guia.get(11));
            objetoCabecera.put("fecha_de_inicio_de_traslado", guia.get(12));
            objetoCabecera.put("transportista_documento_tipo", guia.get(13));
            objetoCabecera.put("transportista_documento_numero", guia.get(14));
            objetoCabecera.put("transportista_denominacion", guia.get(15));
            objetoCabecera.put("transportista_placa_numero", guia.get(17) + " " + guia.get(18));
            objetoCabecera.put("conductor_documento_tipo", guia.get(19));
            objetoCabecera.put("conductor_documento_numero", guia.get(20));
            objetoCabecera.put("conductor_denominacion", guia.get(21) + "-" + guia.get(22));
            objetoCabecera.put("punto_de_partida_ubigeo", guia.get(23));
            objetoCabecera.put("punto_de_partida_direccion", guia.get(24));
            objetoCabecera.put("punto_de_llegada_ubigeo", guia.get(25));
            objetoCabecera.put("punto_de_llegada_direccion", "IIEE - " + guia.get(6) + " " + guia.get(26));
            objetoCabecera.put("enviar_automaticamente_a_la_sunat", "true");
            objetoCabecera.put("enviar_automaticamente_al_cliente", "true");
            objetoCabecera.put("codigo_unico", "");
            objetoCabecera.put("formato_de_pdf", "A4");

            JSONArray lista = new JSONArray();
            JSONObject detalle_linea_1 = new JSONObject();
            if (guia.size() > 28) {
                detalle_linea_1.put("unidad_de_medida", "NIU");
                detalle_linea_1.put("codigo", guia.get(27));
                detalle_linea_1.put("descripcion", guia.get(28));
                detalle_linea_1.put("cantidad", guia.get(29));
            }
            JSONObject detalle_linea_2 = new JSONObject();
            if (guia.size() > 31) {
                detalle_linea_2.put("unidad_de_medida", "NIU");
                detalle_linea_2.put("codigo", guia.get(30));
                detalle_linea_2.put("descripcion", guia.get(31));
                detalle_linea_2.put("cantidad", guia.get(32));
            }
            JSONObject detalle_linea_3 = new JSONObject();
            if (guia.size() > 34) {
                detalle_linea_3.put("unidad_de_medida", "NIU");
                detalle_linea_3.put("codigo", guia.get(33));
                detalle_linea_3.put("descripcion", guia.get(34));
                detalle_linea_3.put("cantidad", guia.get(35));
            }
            JSONObject detalle_linea_4 = new JSONObject();
            if (guia.size() > 37) {
                detalle_linea_4.put("unidad_de_medida", "NIU");
                detalle_linea_4.put("codigo", guia.get(36));
                detalle_linea_4.put("descripcion", guia.get(37));
                detalle_linea_4.put("cantidad", guia.get(38));
            }
            JSONObject detalle_linea_5 = new JSONObject();
            if (guia.size() > 40) {
                detalle_linea_5.put("unidad_de_medida", "NIU");
                detalle_linea_5.put("codigo", guia.get(39));
                detalle_linea_5.put("descripcion", guia.get(40));
                detalle_linea_5.put("cantidad", guia.get(41));
            }
            JSONObject detalle_linea_6 = new JSONObject();
            if (guia.size() > 43) {
                detalle_linea_6.put("unidad_de_medida", "NIU");
                detalle_linea_6.put("codigo", guia.get(42));
                detalle_linea_6.put("descripcion", guia.get(43));
                detalle_linea_6.put("cantidad", guia.get(44));
            }
            JSONObject detalle_linea_7 = new JSONObject();
            if (guia.size() > 46) {
                detalle_linea_7.put("unidad_de_medida", "NIU");
                detalle_linea_7.put("codigo", guia.get(45));
                detalle_linea_7.put("descripcion", guia.get(46));
                detalle_linea_7.put("cantidad", guia.get(47));
            }
            JSONObject detalle_linea_8 = new JSONObject();
            if (guia.size() > 49) {
                detalle_linea_8.put("unidad_de_medida", "NIU");
                detalle_linea_8.put("codigo", guia.get(48));
                detalle_linea_8.put("descripcion", guia.get(49));
                detalle_linea_8.put("cantidad", guia.get(50));
            }
            JSONObject detalle_linea_9 = new JSONObject();
            if (guia.size() > 52) {
                detalle_linea_9.put("unidad_de_medida", "NIU");
                detalle_linea_9.put("codigo", guia.get(51));
                detalle_linea_9.put("descripcion", guia.get(52));
                detalle_linea_9.put("cantidad", guia.get(53));
            }
            JSONObject detalle_linea_10 = new JSONObject();
            if (guia.size() > 55) {
                detalle_linea_10.put("unidad_de_medida", "NIU");
                detalle_linea_10.put("codigo", guia.get(54));
                detalle_linea_10.put("descripcion", guia.get(55));
                detalle_linea_10.put("cantidad", guia.get(56));
            }
            JSONObject detalle_linea_11 = new JSONObject();
            if (guia.size() > 58) {
                detalle_linea_11.put("unidad_de_medida", "NIU");
                detalle_linea_11.put("codigo", guia.get(57));
                detalle_linea_11.put("descripcion", guia.get(58));
                detalle_linea_11.put("cantidad", guia.get(59));
            }
            JSONObject detalle_linea_12 = new JSONObject();
            if (guia.size() > 61) {
                detalle_linea_12.put("unidad_de_medida", "NIU");
                detalle_linea_12.put("codigo", guia.get(60));
                detalle_linea_12.put("descripcion", guia.get(61));
                detalle_linea_12.put("cantidad", guia.get(62));
            }
            JSONObject detalle_linea_13 = new JSONObject();
            if (guia.size() > 64) {
                detalle_linea_13.put("unidad_de_medida", "NIU");
                detalle_linea_13.put("codigo", guia.get(63));
                detalle_linea_13.put("descripcion", guia.get(64));
                detalle_linea_13.put("cantidad", guia.get(65));
            }
            JSONObject detalle_linea_14 = new JSONObject();
            if (guia.size() > 67) {
                detalle_linea_14.put("unidad_de_medida", "NIU");
                detalle_linea_14.put("codigo", guia.get(66));
                detalle_linea_14.put("descripcion", guia.get(67));
                detalle_linea_14.put("cantidad", guia.get(68));
            }
            JSONObject detalle_linea_15 = new JSONObject();
            if (guia.size() > 70) {
                detalle_linea_15.put("unidad_de_medida", "NIU");
                detalle_linea_15.put("codigo", guia.get(69));
                detalle_linea_15.put("descripcion", guia.get(70));
                detalle_linea_15.put("cantidad", guia.get(71));
            }
            JSONObject detalle_linea_16 = new JSONObject();
            if (guia.size() > 73) {
                detalle_linea_16.put("unidad_de_medida", "NIU");
                detalle_linea_16.put("codigo", guia.get(72));
                detalle_linea_16.put("descripcion", guia.get(73));
                detalle_linea_16.put("cantidad", guia.get(74));
            }
            JSONObject detalle_linea_17 = new JSONObject();
            if (guia.size() > 76) {
                detalle_linea_17.put("unidad_de_medida", "NIU");
                detalle_linea_17.put("codigo", guia.get(75));
                detalle_linea_17.put("descripcion", guia.get(76));
                detalle_linea_17.put("cantidad", guia.get(77));
            }
            JSONObject detalle_linea_18 = new JSONObject();
            if (guia.size() > 79) {
                detalle_linea_18.put("unidad_de_medida", "NIU");
                detalle_linea_18.put("codigo", guia.get(78));
                detalle_linea_18.put("descripcion", guia.get(79));
                detalle_linea_18.put("cantidad", guia.get(80));
            }
            JSONObject detalle_linea_19 = new JSONObject();
            if (guia.size() > 82) {
                detalle_linea_19.put("unidad_de_medida", "NIU");
                detalle_linea_19.put("codigo", guia.get(81));
                detalle_linea_19.put("descripcion", guia.get(82));
                detalle_linea_19.put("cantidad", guia.get(83));
            }
            JSONObject detalle_linea_20 = new JSONObject();
            if (guia.size() > 85) {
                detalle_linea_20.put("unidad_de_medida", "NIU");
                detalle_linea_20.put("codigo", guia.get(84));
                detalle_linea_20.put("descripcion", guia.get(85));
                detalle_linea_20.put("cantidad", guia.get(86));
            }
            JSONObject detalle_linea_21 = new JSONObject();
            if (guia.size() > 88) {
                detalle_linea_21.put("unidad_de_medida", "NIU");
                detalle_linea_21.put("codigo", guia.get(87));
                detalle_linea_21.put("descripcion", guia.get(88));
                detalle_linea_21.put("cantidad", guia.get(89));
            }
            JSONObject detalle_linea_22 = new JSONObject();
            if (guia.size() > 91) {
                detalle_linea_22.put("unidad_de_medida", "NIU");
                detalle_linea_22.put("codigo", guia.get(90));
                detalle_linea_22.put("descripcion", guia.get(91));
                detalle_linea_22.put("cantidad", guia.get(92));
            }
            JSONObject detalle_linea_23 = new JSONObject();
            if (guia.size() > 94) {
                detalle_linea_23.put("unidad_de_medida", "NIU");
                detalle_linea_23.put("codigo", guia.get(93));
                detalle_linea_23.put("descripcion", guia.get(94));
                detalle_linea_23.put("cantidad", guia.get(95));
            }
            JSONObject detalle_linea_24 = new JSONObject();
            if (guia.size() > 97) {
                detalle_linea_24.put("unidad_de_medida", "NIU");
                detalle_linea_24.put("codigo", guia.get(96));
                detalle_linea_24.put("descripcion", guia.get(97));
                detalle_linea_24.put("cantidad", guia.get(98));
            }
            JSONObject detalle_linea_25 = new JSONObject();
            if (guia.size() > 100) {
                detalle_linea_25.put("unidad_de_medida", "NIU");
                detalle_linea_25.put("codigo", guia.get(99));
                detalle_linea_25.put("descripcion", guia.get(100));
                detalle_linea_25.put("cantidad", guia.get(101));
            }
            JSONObject detalle_linea_26 = new JSONObject();
            if (guia.size() > 103) {
                detalle_linea_26.put("unidad_de_medida", "NIU");
                detalle_linea_26.put("codigo", guia.get(102));
                detalle_linea_26.put("descripcion", guia.get(103));
                detalle_linea_26.put("cantidad", guia.get(104));
            }
            JSONObject detalle_linea_27 = new JSONObject();
            if (guia.size() > 106) {
                detalle_linea_27.put("unidad_de_medida", "NIU");
                detalle_linea_27.put("codigo", guia.get(105));
                detalle_linea_27.put("descripcion", guia.get(106));
                detalle_linea_27.put("cantidad", guia.get(107));
            }
            JSONObject detalle_linea_28 = new JSONObject();
            if (guia.size() > 109) {
                detalle_linea_28.put("unidad_de_medida", "NIU");
                detalle_linea_28.put("codigo", guia.get(108));
                detalle_linea_28.put("descripcion", guia.get(109));
                detalle_linea_28.put("cantidad", guia.get(110));
            }
            JSONObject detalle_linea_29 = new JSONObject();
            if (guia.size() > 112) {
                detalle_linea_29.put("unidad_de_medida", "NIU");
                detalle_linea_29.put("codigo", guia.get(111));
                detalle_linea_29.put("descripcion", guia.get(112));
                detalle_linea_29.put("cantidad", guia.get(113));
            }
            JSONObject detalle_linea_30 = new JSONObject();
            if (guia.size() > 115) {
                detalle_linea_30.put("unidad_de_medida", "NIU");
                detalle_linea_30.put("codigo", guia.get(114));
                detalle_linea_30.put("descripcion", guia.get(115));
                detalle_linea_30.put("cantidad", guia.get(116));
            }
            JSONObject detalle_linea_31 = new JSONObject();
            if (guia.size() > 118) {
                detalle_linea_31.put("unidad_de_medida", "NIU");
                detalle_linea_31.put("codigo", guia.get(117));
                detalle_linea_31.put("descripcion", guia.get(118));
                detalle_linea_31.put("cantidad", guia.get(119));
            }
            JSONObject detalle_linea_32 = new JSONObject();
            if (guia.size() > 121) {
                detalle_linea_32.put("unidad_de_medida", "NIU");
                detalle_linea_32.put("codigo", guia.get(120));
                detalle_linea_32.put("descripcion", guia.get(121));
                detalle_linea_32.put("cantidad", guia.get(122));
            }
            JSONObject detalle_linea_33 = new JSONObject();
            if (guia.size() > 124) {
                detalle_linea_33.put("unidad_de_medida", "NIU");
                detalle_linea_33.put("codigo", guia.get(123));
                detalle_linea_33.put("descripcion", guia.get(124));
                detalle_linea_33.put("cantidad", guia.get(125));
            }
            JSONObject detalle_linea_34 = new JSONObject();
            if (guia.size() > 127) {
                detalle_linea_34.put("unidad_de_medida", "NIU");
                detalle_linea_34.put("codigo", guia.get(126));
                detalle_linea_34.put("descripcion", guia.get(127));
                detalle_linea_34.put("cantidad", guia.get(128));
            }
            JSONObject detalle_linea_35 = new JSONObject();
            if (guia.size() > 130) {
                detalle_linea_35.put("unidad_de_medida", "NIU");
                detalle_linea_35.put("codigo", guia.get(129));
                detalle_linea_35.put("descripcion", guia.get(130));
                detalle_linea_35.put("cantidad", guia.get(131));
            }
            JSONObject detalle_linea_36 = new JSONObject();
            if (guia.size() > 133) {
                detalle_linea_36.put("unidad_de_medida", "NIU");
                detalle_linea_36.put("codigo", guia.get(132));
                detalle_linea_36.put("descripcion", guia.get(133));
                detalle_linea_36.put("cantidad", guia.get(134));
            }
            JSONObject detalle_linea_37 = new JSONObject();
            if (guia.size() > 136) {
                detalle_linea_37.put("unidad_de_medida", "NIU");
                detalle_linea_37.put("codigo", guia.get(135));
                detalle_linea_37.put("descripcion", guia.get(136));
                detalle_linea_37.put("cantidad", guia.get(137));
            }
            JSONObject detalle_linea_38 = new JSONObject();
            if (guia.size() > 139) {
                detalle_linea_38.put("unidad_de_medida", "NIU");
                detalle_linea_38.put("codigo", guia.get(138));
                detalle_linea_38.put("descripcion", guia.get(139));
                detalle_linea_38.put("cantidad", guia.get(140));
            }
            JSONObject detalle_linea_39 = new JSONObject();
            if (guia.size() > 142) {
                detalle_linea_39.put("unidad_de_medida", "NIU");
                detalle_linea_39.put("codigo", guia.get(141));
                detalle_linea_39.put("descripcion", guia.get(142));
                detalle_linea_39.put("cantidad", guia.get(143));
            }
            JSONObject detalle_linea_40 = new JSONObject();
            if (guia.size() > 145) {
                detalle_linea_40.put("unidad_de_medida", "NIU");
                detalle_linea_40.put("codigo", guia.get(144));
                detalle_linea_40.put("descripcion", guia.get(145));
                detalle_linea_40.put("cantidad", guia.get(146));
            }
            JSONObject detalle_linea_41 = new JSONObject();
            if (guia.size() > 148) {
                detalle_linea_41.put("unidad_de_medida", "NIU");
                detalle_linea_41.put("codigo", guia.get(147));
                detalle_linea_41.put("descripcion", guia.get(148));
                detalle_linea_41.put("cantidad", guia.get(149));
            }
            JSONObject detalle_linea_42 = new JSONObject();
            if (guia.size() > 151) {
                detalle_linea_42.put("unidad_de_medida", "NIU");
                detalle_linea_42.put("codigo", guia.get(150));
                detalle_linea_42.put("descripcion", guia.get(151));
                detalle_linea_42.put("cantidad", guia.get(152));
            }
            JSONObject detalle_linea_43 = new JSONObject();
            if (guia.size() > 154) {
                detalle_linea_43.put("unidad_de_medida", "NIU");
                detalle_linea_43.put("codigo", guia.get(153));
                detalle_linea_43.put("descripcion", guia.get(154));
                detalle_linea_43.put("cantidad", guia.get(155));
            }
            JSONObject detalle_linea_44 = new JSONObject();
            if (guia.size() > 157) {
                detalle_linea_44.put("unidad_de_medida", "NIU");
                detalle_linea_44.put("codigo", guia.get(156));
                detalle_linea_44.put("descripcion", guia.get(157));
                detalle_linea_44.put("cantidad", guia.get(158));
            }
            JSONObject detalle_linea_45 = new JSONObject();
            if (guia.size() > 160) {
                detalle_linea_45.put("unidad_de_medida", "NIU");
                detalle_linea_45.put("codigo", guia.get(159));
                detalle_linea_45.put("descripcion", guia.get(160));
                detalle_linea_45.put("cantidad", guia.get(161));
            }
            JSONObject detalle_linea_46 = new JSONObject();
            if (guia.size() > 163) {
                detalle_linea_46.put("unidad_de_medida", "NIU");
                detalle_linea_46.put("codigo", guia.get(162));
                detalle_linea_46.put("descripcion", guia.get(163));
                detalle_linea_46.put("cantidad", guia.get(164));
            }
            JSONObject detalle_linea_47 = new JSONObject();
            if (guia.size() > 166) {
                detalle_linea_47.put("unidad_de_medida", "NIU");
                detalle_linea_47.put("codigo", guia.get(165));
                detalle_linea_47.put("descripcion", guia.get(166));
                detalle_linea_47.put("cantidad", guia.get(167));
            }
            JSONObject detalle_linea_48 = new JSONObject();
            if (guia.size() > 169) {
                detalle_linea_48.put("unidad_de_medida", "NIU");
                detalle_linea_48.put("codigo", guia.get(168));
                detalle_linea_48.put("descripcion", guia.get(169));
                detalle_linea_48.put("cantidad", guia.get(170));
            }
            JSONObject detalle_linea_49 = new JSONObject();
            if (guia.size() > 172) {
                detalle_linea_49.put("unidad_de_medida", "NIU");
                detalle_linea_49.put("codigo", guia.get(171));
                detalle_linea_49.put("descripcion", guia.get(172));
                detalle_linea_49.put("cantidad", guia.get(173));
            }
            JSONObject detalle_linea_50 = new JSONObject();
            if (guia.size() > 175) {
                detalle_linea_50.put("unidad_de_medida", "NIU");
                detalle_linea_50.put("codigo", guia.get(174));
                detalle_linea_50.put("descripcion", guia.get(175));
                detalle_linea_50.put("cantidad", guia.get(176));
            }
            run:
            if (guia.size() > 28) {
                lista.add(detalle_linea_1);
            }
            if (guia.size() > 31) {
                lista.add(detalle_linea_2);
            }
            if (guia.size() > 34) {
                lista.add(detalle_linea_3);
            }
            if (guia.size() > 37) {
                lista.add(detalle_linea_4);
            }
            if (guia.size() > 40) {
                lista.add(detalle_linea_5);
            }
            if (guia.size() > 43) {
                lista.add(detalle_linea_6);
            }
            if (guia.size() > 46) {
                lista.add(detalle_linea_7);
            }
            if (guia.size() > 49) {
                lista.add(detalle_linea_8);
            }
            if (guia.size() > 52) {
                lista.add(detalle_linea_9);
            }
            if (guia.size() > 55) {
                lista.add(detalle_linea_10);
            }
            if (guia.size() > 58) {
                lista.add(detalle_linea_11);
            }
            if (guia.size() > 61) {
                lista.add(detalle_linea_12);
            }
            if (guia.size() > 64) {
                lista.add(detalle_linea_13);
            }
            if (guia.size() > 67) {
                lista.add(detalle_linea_14);
            }
            if (guia.size() > 70) {
                lista.add(detalle_linea_15);
            }
            if (guia.size() > 73) {
                lista.add(detalle_linea_16);
            }
            if (guia.size() > 76) {
                lista.add(detalle_linea_17);
            }
            if (guia.size() > 79) {
                lista.add(detalle_linea_18);
            }
            if (guia.size() > 82) {
                lista.add(detalle_linea_19);
            }
            if (guia.size() > 85) {
                lista.add(detalle_linea_20);
            }
            if (guia.size() > 88) {
                lista.add(detalle_linea_21);
            }
            if (guia.size() > 91) {
                lista.add(detalle_linea_22);
            }
            if (guia.size() > 94) {
                lista.add(detalle_linea_23);
            }
            if (guia.size() > 97) {
                lista.add(detalle_linea_24);
            }
            if (guia.size() > 100) {
                lista.add(detalle_linea_25);
            }
            if (guia.size() > 103) {
                lista.add(detalle_linea_26);
            }
            if (guia.size() > 106) {
                lista.add(detalle_linea_27);
            }
            if (guia.size() > 109) {
                lista.add(detalle_linea_28);
            }
            if (guia.size() > 112) {
                lista.add(detalle_linea_29);
            }
            if (guia.size() > 115) {
                lista.add(detalle_linea_30);
            }
            if (guia.size() > 118) {
                lista.add(detalle_linea_31);
            }
            if (guia.size() > 121) {
                lista.add(detalle_linea_32);
            }
            if (guia.size() > 124) {
                lista.add(detalle_linea_33);
            }
            if (guia.size() > 127) {
                lista.add(detalle_linea_34);
            }
            if (guia.size() > 130) {
                lista.add(detalle_linea_35);
            }
            if (guia.size() > 133) {
                lista.add(detalle_linea_36);
            }
            if (guia.size() > 136) {
                lista.add(detalle_linea_37);
            }
            if (guia.size() > 139) {
                lista.add(detalle_linea_38);
            }
            if (guia.size() > 142) {
                lista.add(detalle_linea_39);
            }
            if (guia.size() > 145) {
                lista.add(detalle_linea_40);
            }
            if (guia.size() > 148) {
                lista.add(detalle_linea_41);
            }
            if (guia.size() > 151) {
                lista.add(detalle_linea_42);
            }
            if (guia.size() > 154) {
                lista.add(detalle_linea_43);
            }
            if (guia.size() > 157) {
                lista.add(detalle_linea_44);
            }
            if (guia.size() > 160) {
                lista.add(detalle_linea_45);
            }
            if (guia.size() > 163) {
                lista.add(detalle_linea_46);
            }
            if (guia.size() > 166) {
                lista.add(detalle_linea_47);
            }
            if (guia.size() > 169) {
                lista.add(detalle_linea_48);
            }
            if (guia.size() > 172) {
                lista.add(detalle_linea_49);
            }
            if (guia.size() > 175) {
                lista.add(detalle_linea_50);
            }

            objetoCabecera.put("items", lista);
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
                respuesta.add(json_rspta.get("tipo_de_comprobante").toString());
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

                }
            }
        } catch (UnsupportedEncodingException ex1) {
            System.err.println("Error UnsupportedEncodingException: " + ex1.getMessage());
        } catch (IOException ex2) {
            System.err.println("Error IOException: " + ex2.getMessage());
        } catch (Exception ex3) {
            System.err.println("Exepction: " + ex3.getMessage());
        }
        return respuesta;
    }
}
