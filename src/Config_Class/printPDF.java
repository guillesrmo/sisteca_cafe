/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config_Class;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;


/**
 *
 * @author pro
 */
public class printPDF {
      public static void main(String args[]) throws Exception {

           PDDocument document = PDDocument.load(new File("C:\\Users\\pro\\Downloads\\sss.pdf"));

           PrintService myPrintService = findPrintService("EPSON L220 Series");

           PrinterJob job = PrinterJob.getPrinterJob();
           job.setPageable(new PDFPageable(document));
           job.setPrintService(myPrintService);
           job.print();

       }       

       private static PrintService findPrintService(String printerName) {
           PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
           for (PrintService printService : printServices) {
               if (printService.getName().trim().equals(printerName)) {
                   return printService;
               }
           }
           return null;
       }
    
}
