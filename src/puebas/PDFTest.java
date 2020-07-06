/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;

/**
 *
 * @author User
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.*;

public class PDFTest {

    public static void main(String[] args) {
        PDDocument pd;
        BufferedWriter wr;
        try {
            File input = new File("C:\\Users\\User\\Downloads\\Informe_Control_047201530081.pdf");  // The PDF file from where you would like to extract
            File output = new File("C:\\Users\\User\\Downloads\\txt.txt"); // The text file where you are going to store the extracted data
            pd = PDDocument.load(input);
            /*  PDDocument document = PDDocument.load(new File("C:\\Users\\User\\Downloads\\Actas de Entrega Productos Emitir_INAMBARI (2).pdf"));
if (!document.isEncrypted()) {
    PDFTextStripper stripper = new PDFTextStripper();
    String text = stripper.getText(document);
    System.out.println("Text:" + text);
}
document.close();*/
            System.out.println(pd.getNumberOfPages());

            //System.out.println(pd.getDocumentInformation());
            pd.save("CopyOfBill.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1); //Start extracting from page 3
            stripper.setEndPage(180); //Extract till page 5
            System.out.print(stripper.getText(pd));
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
               /* boolean resultado = text.get(i).contains("[COPIA CAE]");

                if (resultado) {
                    System.out.println("cae");
                } else {
                    // System.out.println("palabra no encontrada");
                }*/
                /*boolean intIndex11 = text.get(i).contains("COPIA CAE");
                if (intIndex11) {*/
                boolean intIndex1 = text.get(i).contains("CÓDIGO MODULAR");
                if (intIndex1) {
                    System.out.println("palabra encontrada" + contador1);
                    contador1++;
                    boolean intIndex = text.get(i).contains("1128644");
                    if (intIndex) {
                        System.out.println("palabra encontrada codigo" + contador);
                        contador++;
                    } else {
                        //System.out.println("palabra no encontrada"
                        // + intIndex);
                    }
                } else {
                    //System.out.println("palabra no encontrada"
                    // + intIndex);
                }
                /*  } else {
                    //System.out.println("palabra no encontrada"
                           // + intIndex);
                }*/

            }
            // System.out.println("puebas.PDFTest.main()"+part1);
            //System.out.println("puebas.PDFTest.main()"+part2);
            text.add(stripper.getText(pd));
            // System.out.println("puebas.PDFTest.main()" + text.get(0));
            wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
            stripper.writeText(pd, wr);

            if (pd != null) {
                System.out.println(wr);
                pd.close();
            }
            // I use close() to flush the stream.
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
