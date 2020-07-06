/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config_Class;

/**
 *
 * @author memo
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

import com.gnostice.pdfone.PdfDocument;
import java.awt.print.PrinterException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Read_PDF_From_URL {
 private final static Logger LOGGER = Logger.getLogger("mx.hash.impresionpdf.Impresor");
 //public void imprimir() throws PrinterException, IOException {
  public static void  descargar_pdf(String url,String directorio) throws IOException {
      
    URL url1 =new URL(url);

    byte[] ba1 = new byte[1024];
    int baLength;
    FileOutputStream fos1 = new FileOutputStream(directorio);

    try {
      // Contacting the URL
      //System.out.print("Connecting to " + url1.toString() + " ... ");
      URLConnection urlConn = url1.openConnection();

      // Checking whether the URL contains a PDF
      if (!urlConn.getContentType().equalsIgnoreCase("application/pdf")) {
          JOptionPane.showMessageDialog(null, "ERROR DE DESCARGA");
      } else {
        try {

          // Read the PDF from the URL and save to a local file
          InputStream is1 = url1.openStream();
          while ((baLength = is1.read(ba1)) != -1) {
              fos1.write(ba1, 0, baLength);
          }
          fos1.flush();
          fos1.close();
          is1.close();

          // Load the PDF document and display its page count
         // System.out.print("DONE.\nProcessing the PDF ... ");
          PdfDocument doc = new PdfDocument();
          try {
              
              
            File file = new File(directorio) ;
            doc.load(file);
            //System.out.println("DONE.\nNumber of pages in the PDF is " +
                             //  doc.getPageCount());
            doc.close();
            
            

   // public static void main(String[] args) {
      
    //}

            
          } catch (Exception e) {
            System.out.println("FAILED.\n[" + e.getMessage() + "]");
          }

        } catch (ConnectException ce) {
          System.out.println("FAILED.\n[" + ce.getMessage() + "]\n");
        }
      }

    } catch (NullPointerException npe) {
      System.out.println("FAILED.\n[" + npe.getMessage() + "]\n");
    }
  }
    
}