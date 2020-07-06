/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;

import static Controller.Controller_Distribucion.doMerge;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class removeBlankPdfPages {
    
    public  void pdf(String codigo) throws IOException, DocumentException {
        List<InputStream> list = new ArrayList<InputStream>();
        String ruta = "C:\\Users\\User\\Desktop\\pdf";
        String ext = "pdf";
        Path dir = Paths.get(ruta);
        if (Files.exists(dir, LinkOption.NOFOLLOW_LINKS) && Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, String.format("*.%s", ext))) {
                ArrayList<Path> files = new ArrayList<>();
                stream.forEach(files::add);
                //utilize un natural order primero para ordenar por nombre
                files.sort(Comparator.naturalOrder());
                //utilize nuestro ordernado por Numero.
                String pdf = "";
                files.sort(Comparator.comparing(file -> getnumeric(file), Comparator.nullsLast(Comparator.naturalOrder())));

                // files.forEach(  file -> pdf=(file.getFileName().toString()));
                for (int i = 0; i < files.size(); i++) {
                   
                    boolean existe = files.get(i).toString().contains(codigo);
		if (existe) {
                     System.out.println(files.get(i).toString());
                     list.add(new FileInputStream(new File(files.get(i).toString())));
		//	System.out.println("El elemento SÍ existe en la lista");
		} else {
			//System.out.println("El elemento no existe");
		}
                    
                }

            }
        }
        
        
            OutputStream out = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\actas\\"+codigo+".pdf"));
            doMerge(list, out);

        

    }
     private static Integer getnumeric(Path file) {
        try {
            return Integer.parseInt(file.getFileName().toString().substring(0, file.getFileName().toString().indexOf('.')));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
