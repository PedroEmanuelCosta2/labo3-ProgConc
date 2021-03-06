package airport.com.version1;

import airport.com.Tools;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AirportFrameBlockingQueue extends JFrame {

    private static final long serialVersionUID = 1L;

    // liste d'avion à chaque endroits
    private List<AvionBlockingQueue> avionOnAirArray;
    private List<AvionBlockingQueue> avionLandingArray;
    private List<AvionBlockingQueue> avionTermArray;
    private List<AvionBlockingQueue> avionTakeOffArray;
    private List<AvionBlockingQueue> avionOnAirLeaveArray;

    // images d'avion
    private ArrayList<JLabel> listTerm;
    private ArrayList<JLabel> listArr;
    private ArrayList<JLabel> listDep;

    private JLabel nbOnAirLabel;
    private JLabel nbLandingLabel;
    private JLabel nbTermLabel;
    private JLabel nbTakeOffLabel;
    private JLabel nbOnAirLeaveLabel;
    
    //Attributs boutons
    private JButton start;
    private JButton stop;
    
    //Attribut très important qui permet de définir si l'aéroport est ouvert ou non. 
    //Ce qui signifie si on peut accéder à ses ressources ou non.
    private boolean isOpen = false;

    private int nbPisteArr;
    private int nbPisteDep;
    private int nbPlace;
    
    /*------------------------------------------------------------------*\
    |*                      Constructeur        			*|
    \*------------------------------------------------------------------*/

    public AirportFrameBlockingQueue(int _nbPisteArr, int _nbPisteDep, int _nbPlace, int _nbAvion) {
        //Code original donné par la professeur
        nbPisteArr = _nbPisteArr;
        nbPisteDep = _nbPisteDep;
        nbPlace = _nbPlace;

        avionOnAirArray = new ArrayList<AvionBlockingQueue>();
        avionLandingArray = new ArrayList<AvionBlockingQueue>();
        avionTermArray = new ArrayList<AvionBlockingQueue>();
        avionTakeOffArray = new ArrayList<AvionBlockingQueue>();
        avionOnAirLeaveArray = new ArrayList<AvionBlockingQueue>();

        listArr = new ArrayList<JLabel>();
        listTerm = new ArrayList<JLabel>();
        listDep = new ArrayList<JLabel>();

        JPanel panel = new JPanel(new BorderLayout());

        JPanel airportPanel = new JPanel();
        airportPanel.setLayout(new GridLayout(1, 3));

        ImageIcon imgRoad = new ImageIcon("img/piste.png");

        JPanel landPanel = new JPanel();
        landPanel.setLayout(new GridLayout(2 + (nbPisteArr - 1), 1));
        ImageIcon imgLand = new ImageIcon("img/landing.png");
        nbLandingLabel = new JLabel("nb avion en approche :", SwingConstants.CENTER);

        for (int i = 1; i <= _nbPisteArr; i++) {
            JLabel imgLandingLabel = new JLabel("", Tools.scaleImage(imgLand, 50, 50), SwingConstants.CENTER);
            imgLandingLabel.setVisible(false);
            listArr.add(imgLandingLabel);
            landPanel.add(imgLandingLabel);
            landPanel.add(new JLabel("", Tools.scaleImage(imgRoad, 50, 50), SwingConstants.CENTER));
        }

        landPanel.add(new JLabel());
        landPanel.add(nbLandingLabel);
        airportPanel.add(landPanel);

        JPanel airportImgPanel = new JPanel();
        airportImgPanel.setLayout(new GridLayout(3, 1));
        ImageIcon imgAir = new ImageIcon("img/airport.png");
        airportImgPanel.add(new JLabel("", Tools.scaleImage(imgAir, 150, 150), SwingConstants.CENTER));
        nbTermLabel = new JLabel("nb avion au terminal :", SwingConstants.CENTER);
        airportImgPanel.add(nbTermLabel);
        airportPanel.add(airportImgPanel);

        JPanel takeOffPanel = new JPanel();
        takeOffPanel.setLayout(new GridLayout(2 + (nbPisteDep - 1), 1));
        ImageIcon imgTakeOff = new ImageIcon("img/takeoff.png");
        nbTakeOffLabel = new JLabel("nb avion au départ :", SwingConstants.CENTER);

        for (int i = 1; i <= _nbPisteDep; i++) {
            JLabel imgTakeOffLabel = new JLabel("", Tools.scaleImage(imgTakeOff, 50, 50), SwingConstants.CENTER);
            imgTakeOffLabel.setVisible(false);
            listDep.add(imgTakeOffLabel);
            takeOffPanel.add(new JLabel("", Tools.scaleImage(imgRoad, 50, 50), SwingConstants.CENTER));
            takeOffPanel.add(imgTakeOffLabel);
        }

        takeOffPanel.add(nbTakeOffLabel);
        airportPanel.add(takeOffPanel);

        panel.add(airportPanel, BorderLayout.CENTER);

        JPanel parkPanel = new JPanel();

        for (int i = 1; i <= _nbPlace; i++) {
            ImageIcon imgPark = new ImageIcon("img/waiting.png");
            JLabel imgParkLabel = new JLabel("", Tools.scaleImage(imgPark, 50, 50), SwingConstants.CENTER);
            imgParkLabel.setVisible(false);
            listTerm.add(imgParkLabel);
            imgParkLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            parkPanel.add(imgParkLabel);
        }

        panel.add(parkPanel, BorderLayout.SOUTH);

        JPanel onAirPanel = new JPanel();
        onAirPanel.setLayout(new GridLayout(2, 2));
        ImageIcon imgOnAir = new ImageIcon("img/onair.png");
        nbOnAirLabel = new JLabel("nb avion en air (arrive) :", SwingConstants.CENTER);
        onAirPanel.add(new JLabel("", Tools.scaleImage(imgOnAir, 50, 50), SwingConstants.CENTER));
        onAirPanel.add(new JLabel("", Tools.scaleImage(imgOnAir, 50, 50), SwingConstants.CENTER));
        onAirPanel.add(nbOnAirLabel);
        nbOnAirLeaveLabel = new JLabel("nb avion en air (depart) :", SwingConstants.CENTER);
        onAirPanel.add(nbOnAirLeaveLabel);
        panel.add(onAirPanel, BorderLayout.NORTH);

        JPanel bouton = new JPanel();
        bouton.setLayout(new GridLayout(1, 2));
        JPanel start = new JPanel();
        JPanel stop = new JPanel();
        //Modification ici du code en utilisant des boutons comme attributs initialisés plus haut.
        this.start = new JButton("Start");
        this.stop = new JButton("Stop");
        this.stop.setEnabled(false);
        start.add(this.start);
        stop.add(this.stop);
        
        //L'utilisation de ces attributs permet de gérer ainsi leurs événements
        this.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ici on synchronise la frame
                synchronized(AirportFrameBlockingQueue.this){
                    AirportFrameBlockingQueue.this.isOpen = true; //Pour en suite mettre l'attribut isOpen à true et
                    AirportFrameBlockingQueue.this.notifyAll(); //indique à tous les threads de se réveiller.
                    
                    AirportFrameBlockingQueue.this.start.setEnabled(false);
                    AirportFrameBlockingQueue.this.stop.setEnabled(true);
                }
            }
        });
        
        this.stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized(AirportFrameBlockingQueue.this){
                    AirportFrameBlockingQueue.this.isOpen = false; //Mets isOpen à false, on ne fait pas de wait ici
                    //ça met le cheni sinon. On prendra en compte les wait plus bas.
                    AirportFrameBlockingQueue.this.start.setEnabled(true);
                    AirportFrameBlockingQueue.this.stop.setEnabled(false);
                }
            }
        });

        bouton.add(start);
        bouton.add(stop);
        panel.add(bouton, BorderLayout.EAST);

        this.getContentPane().add(panel);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Labo 3 - Gestion d'un aéroport - BlockingQueue");
    }
    
    /*------------------------------------------------------------------*\
    |*                      Methodes Publique     			*|
    \*------------------------------------------------------------------*/
    //Permet de récupérer le bouton ok depuis maintest
    public JButton getStart() {
        return start;
    }        
    //Gère lorsque l'avion arrive
    public synchronized void updateArrive(AvionBlockingQueue avion) throws InterruptedException {
        while(!isOpen){ //C'est ici qu'on prend en compte les wait. Tant que l'aéroport n'est pas ouvert,                        
            wait(); //on ne passe pas à la suite.
        }
        avionOnAirArray.add(avion); //Ajoute l'avion à la liste des avions en l'air.
        nbOnAirLabel.setText("nb avion en air (arrive) : " + avionOnAirArray.size()); //Update le texte en fonciton de la taille de cette dernière.
    }
    //Toutes les fonctions ci-dessous font basiquement la même chose que celle en dessus. 
    //On change de liste les avions au fur et à mesure, ce qui permet de les faire changer d'étapes.
    //On fait bien attention de mettre à jour les textes correspondants.
    public synchronized void updateAtterrit(AvionBlockingQueue avion) throws InterruptedException {
        while(!isOpen){
            wait();
        }
        avionOnAirArray.remove(avion);
        avionLandingArray.add(avion);
        nbLandingLabel.setText("nb avion en approche : " + avionLandingArray.size());
        nbOnAirLabel.setText("nb avion en air (arrive) : " + avionOnAirArray.size());

        updateLandingLabel();
    }

    public synchronized void updatePark(AvionBlockingQueue avion) throws InterruptedException {
        while(!isOpen){
            wait();
        }
        avionLandingArray.remove(avion);
        avionTermArray.add(avion);
        nbLandingLabel.setText("nb avion en approche : " + avionLandingArray.size());
        nbTermLabel.setText("nb avion au terminal : " + avionTermArray.size());

        updateLandingLabel();
        updateTerminalLabel();
    }

    public synchronized void updateDecolle(AvionBlockingQueue avion) throws InterruptedException {
        while(!isOpen){
            wait();
        }
        avionTermArray.remove(avion);
        avionTakeOffArray.add(avion);
        nbTermLabel.setText("nb avion au terminal : " + avionTermArray.size());
        nbTakeOffLabel.setText("nb avion au départ : " + avionTakeOffArray.size());

        updateTerminalLabel();
        updateTakeOfLabel();
    }

    public synchronized void updatePart(AvionBlockingQueue avion) throws InterruptedException {
        while(!isOpen){
            wait();
        }
        avionTakeOffArray.remove(avion);
        avionOnAirLeaveArray.add(avion);
        nbTakeOffLabel.setText("nb avion au départ : " + avionTakeOffArray.size());
        nbOnAirLeaveLabel.setText("nb avion en air (depart) : " + avionOnAirLeaveArray.size());
        updateTakeOfLabel();
    }    

    /*------------------------------------------------------------------*\
    |*                      Methodes Private     			*|
    \*------------------------------------------------------------------*/
    //Ici ces trois fonctions permettent de gérer les images des avions avec 
    //les codes correspondants. Ces dernières feront donc défiler les images des
    //avions en fonction de leur changement de place dans les listes correspondantes.
    
    //Ici lorsque l'avion décolle de la piste de départ.
    private void updateTakeOfLabel() {
        for (int i = 0; i < nbPisteDep; i++) {
            if (i < avionTakeOffArray.size()) {
                listDep.get(i).setVisible(true);
                listDep.get(i).setText(avionTakeOffArray.get(i).getCode());
            } else {
                listDep.get(i).setVisible(false);
            }
        }
    }
    //Ici lorque l'avion est parqué.
    private void updateTerminalLabel() {
        for (int i = 0; i < nbPlace; i++) {
            if (i < avionTermArray.size()) {
                listTerm.get(i).setVisible(true);
                listTerm.get(i).setText(avionTermArray.get(i).getCode());
            } else {
                listTerm.get(i).setVisible(false);
            }
        }
    }
    //Ici lorsque l'avion attérit.
    private void updateLandingLabel() {
        for (int i = 0; i < nbPisteArr; i++) {
            if (i < avionLandingArray.size()) {
                listArr.get(i).setVisible(true);
                listArr.get(i).setText(avionLandingArray.get(i).getCode());
            } else {
                listArr.get(i).setVisible(false);
            }
        }
    }        
}
