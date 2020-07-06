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
import java.awt.BorderLayout;   
import java.awt.Dimension;   
   
import javax.swing.JFrame;   
import javax.swing.JScrollPane;   
import javax.swing.JTable;   
import javax.swing.JViewport;   
import javax.swing.ListSelectionModel;   
import javax.swing.table.AbstractTableModel;   
import javax.swing.table.TableModel;   
   
public class FixedTable {   
  public static void main(String args[]) {   
   
    final Object rowData[][] = {   
        { "1", "one", "ichi", "un", "I", "\u4E00" },   
        { "2", "two", "ni", "deux", "II", "\u4E8C" },   
        { "3", "three", "san", "trois", "III", "\u4E09" },   
        { "4", "four", "shi", "quatre", "IV", "\u56DB" },   
        { "5", "five", "go", "cinq", "V", "\u4E94" },   
        { "6", "six", "roku", "treiza", "VI", "\u516D" },   
        { "7", "seven", "shichi", "sept", "VII", "\u4E03" },   
        { "8", "eight", "hachi", "huit", "VIII", "\u516B" },   
        { "9", "nine", "kyu", "neur", "IX", "\u4E5D" },   
        { "10", "ten", "ju", "dix", "X", "\u5341" } };   
   
    final String columnNames[] = { "#", "English", "Japanese", "French",   
        "Roman", "Kanji" };   
   
    TableModel fixedColumnModel = new AbstractTableModel() {   
      public int getColumnCount() {   
        return 2;   
      }   
   
      public String getColumnName(int column) {   
        return columnNames[column];   
      }   
   
      public int getRowCount() {   
        return rowData.length;   
      }   
   
      public Object getValueAt(int row, int column) {   
        return null;   
      }   
    };   
   
    TableModel mainModel = new AbstractTableModel() {   
      public int getColumnCount() {   
        return columnNames.length - 1;   
      }   
   
      public String getColumnName(int column) {   
        return columnNames[column + 1];   
      }   
   
      public int getRowCount() {   
        return rowData.length;   
      }   
   
      public Object getValueAt(int row, int column) {   
        return rowData[row][column + 1];   
      }   
    };   
   
    JTable fixedTable = new JTable(fixedColumnModel);   
    fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
    //    fixedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   
   
    JTable mainTable = new JTable(mainModel);   
    mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   
    //    mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   
   
    ListSelectionModel model = fixedTable.getSelectionModel();   
    mainTable.setSelectionModel(model);   
   
    JScrollPane scrollPane = new JScrollPane(mainTable);   
    Dimension fixedSize = fixedTable.getPreferredSize();   
    JViewport viewport = new JViewport();   
    viewport.setView(fixedTable);   
    viewport.setPreferredSize(fixedSize);   
    viewport.setMaximumSize(fixedSize);   
    scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedTable   
        .getTableHeader());   
    scrollPane.setRowHeaderView(viewport);   
   
    JFrame frame = new JFrame("Fixed Column Table");   
   
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);   
    frame.setSize(300, 150);   
    frame.setVisible(true);   
  }   
}   

