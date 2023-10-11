package view.dialogs.newNode;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import observer.ErrorFactory;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import view.utilities.FileExplorer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



public class AddNewPresentationDialog extends JDialog {

    private JLabel lblIme;
    private JLabel lblAutor;
    private JLabel lblSlika;
    private JTextField textField;
    private JButton btnAdd;
    private JButton btnClose;
    private JTextField textImg;
    private FileExplorer fileExplorer;
    private JTextField autor;
    private RuPresentation presentation;

    public AddNewPresentationDialog() {




       // this.presentation = presentation;
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
       // MyTreeNode selektovani =(MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
       // RuNode selectedNode =  selektovani.getMyNode();
        this.setTitle("Add new presentation");

        //Podesavanje GridBag-a
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.insets = new Insets(3, 3, 3, 3);

        gb.gridx = 0;
        gb.gridy = 0;
        gb.gridwidth = 1;
        lblIme = new JLabel("Ime");
        this.add(lblIme, gb);

        textField = new JTextField();
        textField.setSize(120, 10);
        gb.gridx = 1;
        this.add(textField, gb);

        lblAutor = new JLabel("Autor");
        gb.gridx = 0;
        gb.gridy = 1;
        this.add(lblAutor, gb);

        autor = new JTextField();
        autor.setSize(120, 10);
        gb.gridx = 1;
        this.add(autor, gb);

        lblSlika = new JLabel("Slika");
        gb.gridx = 0;
        gb.gridy = 2;
        this.add(lblSlika, gb);

        textImg = new JTextField();
        textImg.setSize(120, 10);
        gb.gridx = 1;
        this.add(textImg, gb);

        fileExplorer = new FileExplorer(textImg);
        gb.gridx = 2;
        this.add(fileExplorer, gb);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add");
        btnClose = new JButton("Cancel");
        btnPanel.add(btnAdd);
        btnPanel.add(btnClose);
        gb.gridy = 3;
        gb.gridx = 1;
        this.add(btnPanel, gb);


        //Dodavanje akcija za JButton-e
        btnAdd.addActionListener(e -> {
            RuNode newNode;
            if (textField.getText().isEmpty()){
                ErrorFactory.getInstance().generateError("GRESKA PRI IMENOVANJU", "Niste uneli naziv prezentacije.", "Unesite naziv prezentacije.", 1);
                return;
            }
            if (autor.getText().isEmpty()){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU AUTORA", "Niste dodali autora prezentacije.", "Dodajte autora.", 1);
                return;
            }
            if (textImg.getText().isEmpty()){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU POZADINE", "Niste uneli pozadinu za prezentaciju", "Izaberite pozadinu.", 1);
                return;
            }
            String ext = getFileExtension(new File(textImg.getText()));
           // System.out.println(ext);
            if (!((ext.equals(".png") ^ (ext.equals(".jpg"))))){
                ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU POZADINE", "Odabrali ste "+ext+" fajl.", "Izaberite .jpg ili .png fajl za pozadinu.", 0);
                return;
            }
            dispose();
        });

        //Klikom na ENTER se dodaje novi cvor jer je podeseno da je defaultButton btnAdd.
        this.getRootPane().setDefaultButton(btnAdd);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClose.setEnabled(false);
                dispose();
            }
        });
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

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JTextField getTextImg() {
        return textImg;
    }

    public void setTextImg(JTextField textImg) {
        this.textImg = textImg;
    }

    public JTextField getAutor() {
        return autor;
    }

    public void setAutor(JTextField autor) {
        this.autor = autor;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }
}
