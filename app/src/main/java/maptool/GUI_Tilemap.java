package maptool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI_Tilemap extends JPanel{
    JLabel[] labels = new JLabel[64];
    JCheckBox[] checkBoxs = new JCheckBox[64];
    public JPanel createTileDisplay() {
        JPanel p = new JPanel(new GridLayout(8,8));

        for(int i = 0; i < 64; i++) {
            JPanel square = new JPanel();
            square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            square.setPreferredSize(new Dimension(65,65));


            //square.addMouseListener(new MouseAdapter() {
            //    public void mouseClicked(MouseEvent arg0) {
            //        boolean isSelected = false;
            //        if(isSelected == false) {
            //            square.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            //            isSelected = true;
            //        } else if(isSelected == true) {
            //            square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //            isSelected = false;
            //        }
            //    }
            //});

            JLabel label = new JLabel();
            labels[i] = label;
            label.setText("NONE");
            square.add(label);

            JCheckBox checkBox = new JCheckBox();
            checkBoxs[i] = checkBox;
            square.add(checkBox);
            p.add(square);
        }

        return p;
    }
}
