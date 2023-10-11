package view.dialogs;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import observer.ErrorFactory;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import view.utilities.FileExplorer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EditDialog extends JDialog {

    private JLabel lblIme;
    private JLabel lblAutor;
    private JLabel lblSlika;
    private JTextField textField;
    private JButton btnAdd;
    private JButton btnClose;
    private JTextField textImg;
    private FileExplorer fileExplorer;
    private JTextField autor;

    public EditDialog() {
        //Defaultna podesavanja dialoga
        this.setSize(new Dimension(400, 150));
        this.setModal(true);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setResizable(false);

        //Dodavanje komponenti
        GridBagConstraints gb = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        //Uzimanje trenutnog cvora na koji dodajemo novi
        MyTreeNode selektovani = (MyTreeNode)MainFrame.getInstance().getTree().getLastSelectedPathComponent();
        RuNode selectedNode = selektovani.getMyNode();
        this.setTitle("Promena autora i slike za prezentaciju");

        //Podesavanje GridBag-a
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.insets = new Insets(3, 3, 3, 3);

        gb.gridx = 0;
        gb.gridy = 0;
        gb.gridwidth = 1;
        lblIme = new JLabel("Ime");
        this.add(lblIme, gb);

        textField = new JTextField();
        textField.setText(selectedNode.getName());
        textField.setEditable(false);
        textField.setSize(120, 10);
        gb.gridx = 1;
        this.add(textField, gb);

        lblAutor = new JLabel("Autor");
        gb.gridx = 0;
        gb.gridy = 1;
        this.add(lblAutor, gb);

        autor = new JTextField();
        autor.setSize(120, 10);
        autor.setText(((RuPresentation) selectedNode).getAutor());
        gb.gridx = 1;
        this.add(autor, gb);

        lblSlika = new JLabel("Slika");
        gb.gridx = 0;
        gb.gridy = 2;
        this.add(lblSlika, gb);

        textImg = new JTextField();
        textImg.setSize(120, 10);
        textImg.setText(((RuPresentation)selectedNode).getFilePath());
        gb.gridx = 1;
        this.add(textImg, gb);

        fileExplorer = new FileExplorer(textImg);
        gb.gridx = 2;
        this.add(fileExplorer, gb);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Change");
        btnClose = new JButton("Cancel");
        btnPanel.add(btnAdd);
        btnPanel.add(btnClose);
        gb.gridy = 3;
        gb.gridx = 1;
        this.add(btnPanel, gb);


        //Dodavanje akcija za JButton-e
        btnAdd.addActionListener(e -> {
            if (autor.getText().isEmpty()){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU AUTORA", "Niste dodali autora prezentacije.", "Dodajte autora.", 1);
                return;
            }
            if (textImg.getText().isEmpty()){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU POZADINE", "Niste uneli pozadinu za prezentaciju", "Izaberite pozadinu.", 1);
                return;
            }
            String ext = getFileExtension(new File(textImg.getText()));
            System.out.println(ext);
            if (!((ext.equals(".png") ^ (ext.equals(".jpg"))))){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU POZADINE", "Odabrali ste "+ext+" fajl.", "Izaberite .jpg ili .png fajl za pozadinu.", 0);
                return;
            }

            if (!(new File(textImg.getText())).exists()){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU POZADINE", "Odabrani fajl ne postoji.", "Izaberite postojeci fajl.", 1);
                return;
            }
            ((RuPresentation)selectedNode).setAutor(autor.getText());
            ((RuPresentation)selectedNode).setFilePath(textImg.getText());
            MainFrame.getInstance().reloadTree();
            dispose();
        });

        this.getRootPane().setDefaultButton(btnAdd);

        btnClose.addActionListener(e -> dispose());
        this.setVisible(true);
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}
