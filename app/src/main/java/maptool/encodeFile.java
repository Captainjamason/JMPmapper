package maptool;

import javax.swing.JLabel;

public class encodeFile {
    void createDataFromArr(JLabel[] arr) {
        JLabel[][] d = new JLabel[8][8];

        int n = 0;
        for(int i = 0; i < 8; i++) {
            for(int y = 0; y < 8; y++) {
                d[i][y] = arr[n];
                n++;
            }
        }
        System.out.println(d[0][0]);
    }
}
