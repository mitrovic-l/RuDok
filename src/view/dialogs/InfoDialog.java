package view.dialogs;

import view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InfoDialog extends JDialog {
    private MainFrame mf;

    public InfoDialog(MainFrame mf){

        this.mf = mf;
        this.setTitle("Info");
        JLabel lblIme = new JLabel("Ime: Luka");
        JLabel lblPrezime = new JLabel("Prezime: Mitrovic");
        JLabel lblIndeks = new JLabel("Indeks: RN61/2020");
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/controller/actions/icons/moja slika.jpg"));
        } catch (IOException e) {
            System.err.println("Greska pri ucitavanju slike...");
        }
        Image scaleImage = img.getScaledInstance(196, 296,Image.SCALE_DEFAULT);
        JPanel pomocni = new JPanel();
        JLabel lblSlika = new JLabel();
        lblSlika.setIcon(new ImageIcon(scaleImage));
        JPanel pNovi = new JPanel();
        pNovi.setLayout(new FlowLayout());
        pNovi.add(lblSlika);
        pNovi.add(pomocni);
        pomocni.setLayout(new BoxLayout(pomocni, BoxLayout.Y_AXIS));
        pomocni.add(lblIme);
        pomocni.add(lblPrezime);
        pomocni.add(lblIndeks);
        this.add(pNovi);
        this.setSize(500,300);
        this.setVisible(true);
        this.setModal(true);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(mf);
        this.setResizable(false);
        BufferedImage ic = null;
        try {
            ic = ImageIO.read(new File("src\\controller\\actions\\icons\\info.png"));
        } catch (IOException e) {
            System.err.println("Greska pri ucitavanju ikonice...");
        }
        this.setIconImage(ic);


    }
}
