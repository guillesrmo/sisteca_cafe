/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;


import com.itextpdf.text.pdf.PdfDocument;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author User
 */
public class NewClass4 {
    public static void main(String[] args) throws IOException {
         //Loading an existing document 
      File file = new File("C:\\Intel\\inamabara.pdf"); 
      PDDocument doc = PDDocument.load(file); 
       
      //Listing the number of existing pages       
      System.out.print(doc.getNumberOfPages()); 
       int a=0;
      for(int i = 0; i<doc.getNumberOfPages(); i++){
         
         //removing the pages 
         doc.removePage(i); 
         a=a+2;
      } 
      System.out.println("page removed");       
      
      //Saving the document 
      doc.save("C:\\Intel\\RemovePages_OP.pdf");     
      
      //Closing the document  
      doc.close(); 
   
    }
}
