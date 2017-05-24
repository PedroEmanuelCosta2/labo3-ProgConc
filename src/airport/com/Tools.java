package airport.com;

import java.awt.Image;
//gestion de ressources

import javax.swing.ImageIcon;

public class Tools {
    
    public static int nbAvion = 20; //nombre d'avion
    public static int nbPisteArr = 2; //pistes d'atterrisage
    public static int nbPisteDep = 2; //pistes de depart
    public static int nbPlace = 2; //parking

    public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();
        if (icon.getIconWidth() > w) {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }
        if (nh > h) {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }
        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }

}
