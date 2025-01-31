package maptool;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class GUI_Datalist {
    public JTable createDatalist() {
        String[][] data = {
            {"", "", ""}
        };
        String[] columnNames = {"ID", "Type", "Name"};

        JTable table = new JTable(data, columnNames);
        return table;
    } 
}
