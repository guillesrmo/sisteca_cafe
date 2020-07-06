/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;

/**
 *
 * @author pro
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Test {

        public static void main( String[] args ) {

            String path = "C:\\Users\\pro\\Desktop\\Nueva carpeta\\sqlsrv_unofficial_3.0.2.2/";


            String[] files = getFiles( path );

            if ( files != null ) {

                int size = files.length;

                for ( int i = 0; i < size; i ++ ) {

                    System.out.println( files[ i ] );
                }
            }
        }


        public static String[] getFiles( String dir_path ) {

            String[] arr_res = null;

            File f = new File( dir_path );

            if ( f.isDirectory( )) {

                List<String> res   = new ArrayList<>();
                File[] arr_content = f.listFiles();

                int size = arr_content.length;

                for ( int i = 0; i < size; i ++ ) {

                    if ( arr_content[ i ].isFile( ))
                    res.add( arr_content[ i ].toString( ));
                }


                arr_res = res.toArray( new String[ 0 ] );

            } else
                System.err.println( "¡ Path NO válido !" );


            return arr_res;
        }

} //class