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
import javax.swing.text.NumberFormatter;

/**
 *
 * @author pedro.costa
 */
public class JFrameSettings extends JFrame {

    JPanel getSettings;

    JLabel nombreAvion;
    JLabel nombrePisteArr;
    JLabel nombrePisteDep;
    JLabel nomrePlace;
    JLabel blockingQueue;
    JLabel circularlTable;
    JLabel modeTest;

    JSpinner inputNbrAvion;
    JSpinner inputNbrPisteArr;
    JSpinner inputNbrPisteDep;
    JSpinner inputNbrPlace;

    JButton ok;

    JCheckBox checkBoxBlockingQueue;
    JCheckBox checkBoxCircularTable;
    JCheckBox checkBoxModeTest;

    public JFrameSettings() {
        getSettings = new JPanel();

        nombreAvion = new JLabel("Entrez le nombre d'avions");
        nombrePisteArr = new JLabel("Entrez le nombre de piste d'arrivées");
        nombrePisteDep = new JLabel("Entrez le nombre de piste de départs");
        nomrePlace = new JLabel("Entrez le nombre de place dans le parking");
        blockingQueue = new JLabel("Blocking Queue");
        circularlTable = new JLabel("Circular Buffer");
        modeTest = new JLabel("Mode Test");

        inputNbrAvion = new JSpinner();
        inputNbrAvion.setValue(Tools.nbAvion);
        inputNbrPisteArr = new JSpinner();
        inputNbrPisteArr.setValue(Tools.nbPisteArr);
        inputNbrPisteArr.setSize(100, 25);
        inputNbrPisteDep = new JSpinner();
        inputNbrPisteDep.setValue(Tools.nbPisteDep);
        inputNbrPisteDep.setSize(100, 25);
        inputNbrPlace = new JSpinner();
        inputNbrPlace.setValue(Tools.nbPlace);
        inputNbrPlace.setSize(100, 25);

        checkFormatSpinner();

        ok = new JButton("OK");

        checkBoxBlockingQueue = new JCheckBox();
        checkBoxCircularTable = new JCheckBox();
        checkBoxModeTest = new JCheckBox();

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

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);

        getSettings.setLayout(layout);

        getSettings.add(nombreAvion);
        getSettings.add(inputNbrAvion);
        getSettings.add(nombrePisteArr);
        getSettings.add(inputNbrPisteArr);
        getSettings.add(nombrePisteDep);
        getSettings.add(inputNbrPisteDep);
        getSettings.add(nomrePlace);
        getSettings.add(inputNbrPlace);
        getSettings.add(blockingQueue);
        getSettings.add(checkBoxBlockingQueue);
        getSettings.add(circularlTable);
        getSettings.add(checkBoxCircularTable);
        getSettings.add(modeTest);
        getSettings.add(checkBoxModeTest);

        getSettings.add(ok);

        add(getSettings);

        setVisible(true);
        setSize(300, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Configurations de l'aéroport");
    }

    private void checkFormatSpinner() {
        JFormattedTextField avionFormat = ((JSpinner.NumberEditor) inputNbrAvion.getEditor()).getTextField();
        ((NumberFormatter) avionFormat.getFormatter()).setAllowsInvalid(false);

        JFormattedTextField pisteArrFormat = ((JSpinner.NumberEditor) inputNbrPisteArr.getEditor()).getTextField();
        ((NumberFormatter) pisteArrFormat.getFormatter()).setAllowsInvalid(false);

        JFormattedTextField pisteDepFormat = ((JSpinner.NumberEditor) inputNbrPisteDep.getEditor()).getTextField();
        ((NumberFormatter) pisteDepFormat.getFormatter()).setAllowsInvalid(false);

        JFormattedTextField placeFormat = ((JSpinner.NumberEditor) inputNbrPlace.getEditor()).getTextField();
        ((NumberFormatter) placeFormat.getFormatter()).setAllowsInvalid(false);
    }

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
