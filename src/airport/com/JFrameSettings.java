/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.com;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author pedro.costa
 */
public class JFrameSettings extends JFrame {

    //Panel

    JPanel panelSettings;
    //Labels de la frame settings
    JLabel nombreAvion;
    JLabel nombrePisteArr;
    JLabel nombrePisteDep;
    JLabel nomrePlace;
    JLabel blockingQueue;
    JLabel circularlTable;
    JLabel modeTest;
    //Spinner permettant la saisie des valeurs correspondantes
    JSpinner inputNbrAvion;
    JSpinner inputNbrPisteArr;
    JSpinner inputNbrPisteDep;
    JSpinner inputNbrPlace;
    //Bouton de confirmation des settings
    JButton ok;
    //CheckBox qui permettront de choisir le mode dans lequel on veut lancer l'application.
    JCheckBox checkBoxBlockingQueue;
    JCheckBox checkBoxCircularTable;
    JCheckBox checkBoxModeTest;

    public JFrameSettings() {
        //Constructeur de tous les attributs.
        panelSettings = new JPanel();
        //Labels permettant la compréhension de l'interface
        nombreAvion = new JLabel("Entrez le nombre d'avions");
        nombrePisteArr = new JLabel("Entrez le nombre de piste d'arrivées");
        nombrePisteDep = new JLabel("Entrez le nombre de piste de départs");
        nomrePlace = new JLabel("Entrez le nombre de place dans le parking");
        blockingQueue = new JLabel("Blocking Queue");
        circularlTable = new JLabel("Circular Buffer");
        modeTest = new JLabel("Mode Test");
        //Spinner initié de manière à ne pas entrer des valeurs absurdes
        inputNbrAvion = new JSpinner(new SpinnerNumberModel(Tools.nbAvion, 1, 100, 1));
        inputNbrPisteArr = new JSpinner(new SpinnerNumberModel(Tools.nbPisteArr, 1, 100, 1));
        inputNbrPisteDep = new JSpinner(new SpinnerNumberModel(Tools.nbPisteDep, 1, 100, 1));
        inputNbrPlace = new JSpinner(new SpinnerNumberModel(Tools.nbPlace, 1, 100, 1));
        //Fonction qui permettra de checker la saisie correcte (que des nombres) des valeurs des spinners
        checkFormatSpinner();
        //Bouton de confirmation
        ok = new JButton("OK");
        //Initiation des checkboxes
        checkBoxBlockingQueue = new JCheckBox();
        checkBoxCircularTable = new JCheckBox();
        checkBoxModeTest = new JCheckBox();
        
        //Événements des check boxes permettant de ne pas lancer le mode test si on veut lancer l'application en mode graphique
        event();
        
        //Layout permettant d'alligner un minimum nos champs
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        panelSettings.setLayout(layout);
        
        //Ajout de nos composants au panel
        panelSettings.add(nombreAvion);
        panelSettings.add(inputNbrAvion);
        panelSettings.add(nombrePisteArr);
        panelSettings.add(inputNbrPisteArr);
        panelSettings.add(nombrePisteDep);
        panelSettings.add(inputNbrPisteDep);
        panelSettings.add(nomrePlace);
        panelSettings.add(inputNbrPlace);
        panelSettings.add(blockingQueue);
        panelSettings.add(checkBoxBlockingQueue);
        panelSettings.add(circularlTable);
        panelSettings.add(checkBoxCircularTable);
        panelSettings.add(modeTest);
        panelSettings.add(checkBoxModeTest);
        panelSettings.add(ok);
        
        //Ajout du panel à la frame
        add(panelSettings);
        //Configs de la frame
        setVisible(true);
        setSize(350, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Configurations de l'aéroport");
    }
    
    /*------------------------------------------------------------------*\
    |*			Methodes Private				*|
    \*------------------------------------------------------------------*/
    
    private void event(){
        checkBoxBlockingQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxBlockingQueue.isSelected()) {
                    checkBoxModeTest.setEnabled(false);
                }
                if (!checkBoxCircularTable.isSelected() && !checkBoxBlockingQueue.isSelected()) {
                    checkBoxModeTest.setEnabled(true);
                }
            }
        });

        checkBoxCircularTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxCircularTable.isSelected()) {
                    checkBoxModeTest.setEnabled(false);
                }
                if (!checkBoxCircularTable.isSelected() && !checkBoxBlockingQueue.isSelected()) {
                    checkBoxModeTest.setEnabled(true);
                }
            }
        });

        checkBoxModeTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxModeTest.isSelected()) {
                    checkBoxBlockingQueue.setEnabled(false);
                    checkBoxCircularTable.setEnabled(false);
                } else {
                    checkBoxBlockingQueue.setEnabled(true);
                    checkBoxCircularTable.setEnabled(true);
                }
            }
        });
    }
    
    private void checkFormatSpinner() {
        //Façon trouvée sur internet pour empêcher de saisir autre chose que des nombres dans nos spinners
        JFormattedTextField avionFormat = ((JSpinner.NumberEditor) inputNbrAvion.getEditor()).getTextField();
        ((NumberFormatter) avionFormat.getFormatter()).setAllowsInvalid(false);

        JFormattedTextField pisteArrFormat = ((JSpinner.NumberEditor) inputNbrPisteArr.getEditor()).getTextField();
        ((NumberFormatter) pisteArrFormat.getFormatter()).setAllowsInvalid(false);

        JFormattedTextField pisteDepFormat = ((JSpinner.NumberEditor) inputNbrPisteDep.getEditor()).getTextField();
        ((NumberFormatter) pisteDepFormat.getFormatter()).setAllowsInvalid(false);

        JFormattedTextField placeFormat = ((JSpinner.NumberEditor) inputNbrPlace.getEditor()).getTextField();
        ((NumberFormatter) placeFormat.getFormatter()).setAllowsInvalid(false);
    }
    
    /*------------------------------------------------------------------*\
    |*                              Get                                 *|
    \*------------------------------------------------------------------*/
    
    public JCheckBox getCheckBoxModeTest() {
        return checkBoxModeTest;
    }

    public JCheckBox getCheckBoxBlockingQueue() {
        return checkBoxBlockingQueue;
    }

    public JCheckBox getCheckBoxCircularTable() {
        return checkBoxCircularTable;
    }

    public JButton getOk() {
        return ok;
    }

    public JSpinner getInputNbrAvion() {
        return inputNbrAvion;
    }

    public JSpinner getInputNbrPisteArr() {
        return inputNbrPisteArr;
    }

    public JSpinner getInputNbrPisteDep() {
        return inputNbrPisteDep;
    }

    public JSpinner getInputNbrPlace() {
        return inputNbrPlace;
    }
}
