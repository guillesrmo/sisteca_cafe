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
public class NewClass {
    public static void main(String[] args) {

    //String nullType = null;
    String integerType = "123434";
    String characterType = "1.5222DSDS";

  //  System.out.println(isInteger(nullType));
    System.out.println(isInteger(integerType));
    System.out.println(isInteger(characterType));

}

public static boolean isInteger(String source) {

    try {
                Double.parseDouble(source);
                return true;
        } catch (NumberFormatException nfe){
                return false;
        }
}
}
