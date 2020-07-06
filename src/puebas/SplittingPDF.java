/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;

/**
 *
 * @author User
 *
 */
import Comprobantes_E_SUNAT.CE_Guia1;
import Conexion.Conexion;
import java.io.BufferedWriter;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.Iterator;
import org.apache.pdfbox.text.PDFTextStripper;

public class SplittingPDF {

    /*public static void main(String[] args) throws IOException {
        File file = new File("C:\\Intel\\inamabara.pdf");
        PDDocument doc = PDDocument.load(file);
        Splitter splitter = new Splitter();
        List<PDDocument> Pages = splitter.split(doc);
        Iterator<PDDocument> iterator = Pages.listIterator();
        int i = 1;
        for (int j = 0; j < Pages.size(); j++) {
            PDDocument pd1 = (PDDocument) Pages.get(j);
            System.out.println("puebas.SplittingPDF.main()" + pd1);
            pd1.save("C:\\Intel\\splitOP" + i++ + ".pdf");

        }
        //  System.out.println("PDF splitted");     
    }*/

    public  void separado_pdf(String codigo) {
        PDDocument pd;
        BufferedWriter wr;
        try {
            File input = new File("C:\\Intel\\tambopata3.pdf");  // The PDF file from where you would like to extract
            pd = PDDocument.load(input);

            System.out.println(pd.getNumberOfPages());

            //System.out.println(pd.getDocumentInformation());
            pd.save("CopyOfBill.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1); //Start extracting from page 3
            stripper.setEndPage(pd.getNumberOfPages()); //Extract till page 5
            //System.out.print(stripper.getText(pd));
            List<String> text = new ArrayList<String>();
            String[] parts = stripper.getText(pd).split("CÓDIGO MODULAR");
            String[] arrSplit = stripper.getText(pd).split("\n");
            for (String a : arrSplit) {
                text.add(a);
                //System.out.println("a"+a);   
            }
            int contador = 1;
            int contador1 = 1;
            for (int i = 0; i < text.size(); i++) {

                boolean intIndex1 = text.get(i).contains("CÓDIGO MODULAR");
                if (intIndex1) {
                    System.out.println("palabra encontrada" + contador1);
                    contador1++;
                    boolean intIndex = text.get(i).contains(codigo);
                    if (intIndex) {
                        System.out.println("palabra encontrada codigo" + contador);
                        

                        File file = new File("C:\\Intel\\tambopata3.pdf");
                        int as=(contador1*2)-4;
                        PDDocument doc = PDDocument.load(file);
                        Splitter splitter = new Splitter();
                        List<PDDocument> Pages = splitter.split(doc);
                       // for (int j = 0; j < Pages.size(); j++) {
                        try {
                       // System.out.println("puebas.SplittingPDF.main()"+as);
                            PDDocument pd1 = (PDDocument) Pages.get(as);
                            pd1.save("C:\\Users\\User\\Desktop\\pdf\\8-" +codigo+"-"+((contador1*2-2)) + "acta.pdf");
                            PDDocument pd2 = (PDDocument) Pages.get(as+1);
                            pd2.save("C:\\Users\\User\\Desktop\\pdf\\8-" + codigo+"-"+((contador1*2-2+1)) + "acta.pdf");
                            
                             
                            pd1.save("C:\\Users\\User\\Desktop\\pdf\\9-" +codigo+"-"+((contador1*2-2)) + "1acta.pdf");
                           
                            pd2.save("C:\\Users\\User\\Desktop\\pdf\\9-" + codigo+"-"+((contador1*2-2+1)) + "1acta.pdf");
                            
                            PDDocument pd3 = (PDDocument) Pages.get(as+2);
                            pd3.save("C:\\Users\\User\\Desktop\\pdf\\6-" +codigo+"-"+((contador1*2)) + "acta.pdf");
                            PDDocument pd4 = (PDDocument) Pages.get(as+3);
                            pd4.save("C:\\Users\\User\\Desktop\\pdf\\6-" + codigo+"-"+((contador1*2+1)) + "acta.pdf");
                            
                             
                            pd3.save("C:\\Users\\User\\Desktop\\pdf\\7-" +codigo+"-"+((contador1*2)) + "1acta.pdf");
                           
                            pd4.save("C:\\Users\\User\\Desktop\\pdf\\7-" + codigo+"-"+((contador1*2+1)) + "1acta.pdf");
                        
                       // }
                       } catch (Exception e) {
                        }
                        contador++;
                        break;
                    } else {

                    }
                } else {

                }

            }

            text.add(stripper.getText(pd));

        } catch (Exception e) {
        //    e.printStackTrace();
        }
    }
    
     public static void main(String[] args) {
        Conexion cxn=new Conexion();
        try {
            String sql="SELECT [institucion_codigo_modular]\n" +
"  FROM [sistema_cafe].[dbo].[institucion] where cast(institucion_iten as varchar(max))='tambopata3'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sql);

            
            while (rs.next()) {
                if (rs.getString(1).length() >5) {
                    System.out.println(rs.getString(1));
                    SplittingPDF cp = new SplittingPDF();
                    cp.separado_pdf(rs.getString(1));
                }
                    
               
            }
        } catch (Exception e) {
        }
}
     
}