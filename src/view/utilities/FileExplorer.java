package view.utilities;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FileExplorer extends JButton {

    public FileExplorer(JTextField textField) {

        this.setText("Browse");
        this.setPreferredSize(new Dimension(10, 10));
        this.setMaximumSize(new Dimension(10,10));
        this.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            int returnValue = jFileChooser.showOpenDialog(jFileChooser);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                textField.setText(jFileChooser.getSelectedFile().getAbsolutePath());
                textField.setEditable(false);
            }
        });

    }

    public Icon loadIcon(String fileName) {

        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        } else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }
}
