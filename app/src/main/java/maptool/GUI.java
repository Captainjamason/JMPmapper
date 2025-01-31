package maptool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class CusMenuBar extends JMenuBar {
    public JMenuBar createMenuBar(JLabel label) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fmenu = new JMenu("File");
        fmenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileDiag = new JFileChooser();
                int res = fileDiag.showOpenDialog(fmenu);
                if(res == JFileChooser.APPROVE_OPTION) {
                    File f = fileDiag.getSelectedFile();
                    System.out.println("File: "+f.getAbsolutePath());
                    label.setText(f.getAbsolutePath());
                }
            }
        });
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileDiag = new JFileChooser();
                int res = fileDiag.showSaveDialog(fmenu);
                if(res == JFileChooser.APPROVE_OPTION) {
                    File f = fileDiag.getSelectedFile();
                    System.out.println("File: "+f.getAbsolutePath());
                }
            }
        });
        JMenuItem newfile = new JMenuItem("New file");
        newfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileDiag = new JFileChooser();
                int res = fileDiag.showSaveDialog(fmenu);
                if(res == JFileChooser.APPROVE_OPTION) {
                    jmp d = JMPfiles.newFile(fileDiag.getSelectedFile());
                    System.out.println(d.encodeJmp(d.testData, "TEST"));
                }
            }
        });
        fmenu.add(newfile);
        fmenu.add(open);
        fmenu.add(save);

        JMenu oMenu = new JMenu("Options");
        oMenu.setMnemonic(KeyEvent.VK_O);
        JCheckBoxMenuItem compOneFile = new JCheckBoxMenuItem("Compile one file");
        oMenu.add(compOneFile);

        menuBar.add(fmenu);
        menuBar.add(oMenu);
        return menuBar;
    }
}

public class GUI {
    void createUI() {
        //  Create frame.
        JFrame frame = new JFrame("JMP Mapper");
        JPanel contentFrame = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 3;
        c.gridwidth = 5;

        //  Spawn our tilemap.
        GUI_Tilemap gui_tile = new GUI_Tilemap();
        JPanel td = gui_tile.createTileDisplay();
        td.setBounds(new Rectangle(0, 0, 200, 200));
        c.gridx = 0;
        c.weightx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 5, 0, 0);
        contentFrame.add(td, c);

        // Loaded file label && Opened Master file label.
        JLabel loadedFileLabel = new JLabel("No file loaded!");
        c.gridx = 5;
        c.anchor = c.NORTH;
        c.gridy = 0;
        c.weightx = 0.3;
        c.insets = new Insets(2, 0, 0, 0);
        contentFrame.add(loadedFileLabel, c);

        JLabel openedMasterFile = new JLabel("Opened Master File: None");
        c.gridy = 1;
        c.gridx = 5;
        c.insets = new Insets(355, 0, 0, 0);
        contentFrame.add(openedMasterFile, c);

        // Create our table of data, in a scrollist.
        JTable typeList = new GUI_Datalist().createDatalist();
        JScrollPane sp = new JScrollPane(typeList);
        sp.setPreferredSize(new Dimension(400, 270));
        sp.setBackground(Color.LIGHT_GRAY);
        c.gridy = 1;
        c.gridx = 5;
        c.insets = new Insets(30, 0, 0, 0);
        c.weighty = 0;
        contentFrame.add(sp, c);

        // Generate all our buttons; New Key, Delete Key, Save Master, Open Master, Update Tiles.
        JButton newCellButton = new JButton("New Key");
        c.gridy = 2;
        c.gridx = 5;
        c.insets = new Insets(305, 120, 0, 0);
        contentFrame.add(newCellButton, c);

        JButton delCellButton = new JButton("Delete Key");
        c.gridy = 2;
        c.gridx = 5;
        c.insets = new Insets(305, 305, 0, 0);
        contentFrame.add(delCellButton, c);

        JButton saveMasterButton = new JButton("Save Master File");
        c.gridy = 2;
        c.gridx = 5;
        c.insets = new Insets(380, 0, 0, 150);
        contentFrame.add(saveMasterButton, c);

        JButton openMasterButton = new JButton("Open Master File");
        c.gridy = 2;
        c.gridx = 5;
        c.insets = new Insets(380, 150, 0, 0);
        contentFrame.add(openMasterButton, c);

        JButton updateTilesButton = new JButton("Update tiles to selection");
        updateTilesButton.setPreferredSize(new Dimension(400, 80));
        updateTilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encodeFile n = new encodeFile();
                n.createDataFromArr(gui_tile.labels);
            }
        });
        c.gridy = 2;
        c.gridx = 5;
        c.insets = new Insets(440, 0, 0, 0);
        contentFrame.add(updateTilesButton, c);

        // Tie it all together, add some final window/frame parameters.
        frame.add(contentFrame);
        frame.setJMenuBar(new CusMenuBar().createMenuBar(loadedFileLabel));
        //frame.setMinimumSize(new Dimension(1000, 600));
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}
