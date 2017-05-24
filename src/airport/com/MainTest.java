package airport.com;

import airport.com.version1.AirportFrameBlockingQueue;
import airport.com.version1.AvionBlockingQueue;
import airport.com.version2.AirportFrameCircularBuffer;
import airport.com.version2.AvionCircularBuffer;
import airport.com.version2.CircularBuffer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainTest {
    //Tableau des codes d'avions
    static String[] codePlane = {"3B147", "B3291", "6B239", "B1086", "780B4", "32A64", "17A69", "2A431", "647B8", "349A8", "536B8", "9103A", "9B210", "139A4", "96B01", "207B9", "830B6", "8435A", "7301B", "1076B", "5281B", "8A521", "3B806", "B6842", "B6238", "7B816", "A9437", "849A3", "60B18",
        "094B6", "4709B", "36A84", "085A3", "0718B", "80B21", "0A369", "5290A", "370B4", "021A3", "84A02", "052A6", "B6350", "630B5", "8B903", "1398B", "2693A", "902A6", "51A20", "971A5", "A7891"};
    //Boolean permettant de savoir si on lance l'appli en mode graphique ou en mode test
    static boolean afficherFrame;

    public static void main(String[] args) {
        //On initialise notre fenêtre des settings
        JFrameSettings jFrameSettings = new JFrameSettings();
        
        //Événement lorsque qu'on confirme la saisie des settings
        jFrameSettings.getOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //On récupère correctement les valeurs des spinners et on les associes aux variables statiques situées dans la classe Tools
                Tools.nbAvion = (int) jFrameSettings.getInputNbrAvion().getValue();
                Tools.nbPisteArr = (int) jFrameSettings.getInputNbrPisteArr().getValue();
                Tools.nbPisteDep = (int) jFrameSettings.getInputNbrPisteDep().getValue();
                Tools.nbPlace = (int) jFrameSettings.getInputNbrPlace().getValue();
                //On vérifie si l'application est lancée en mode test
                if (jFrameSettings.getCheckBoxModeTest().isSelected()) {
                    //Si oui, on n'affiche aucune autre interface
                    afficherFrame = false;
                    //Et on lance la procédure de test
                    testTempsExecution();
                } else {
                    //Sinon, on affiche l(es)'interface(s) sélectionnée(s) 
                    afficherFrame = true;
                    if (jFrameSettings.getCheckBoxBlockingQueue().isSelected()) {
                        testBlockingQueue();
                    }
                    if (jFrameSettings.getCheckBoxCircularTable().isSelected()) {
                        testCirularBuffer();
                    }
                }
            }
        });
    }
    
    /*------------------------------------------------------------------*\
    |*			Methodes Private				*|
    \*------------------------------------------------------------------*/
    
    //Fonction de procédure de test, les textes dans les inputs sont assez explicites sur ce qu'il se passe comme tests.
    private static void testTempsExecution() {
        long time;
        long result;

        int nbPistesMax = 20;
        int nbPlaceMax = 20;

        System.out.println("******************************Début de la séquence de test de performances******************************");

        System.out.println("Démarrage du test de la Blocking Queue");
        time = testBlockingQueue();
        System.out.println("Temps d'exécution : " + time + " ms");

        System.out.println();

        System.out.println("Démarrage du test du Circular Buffer");
        time = testCirularBuffer();
        System.out.println("Temps d'exécution : " + time + " ms");

        System.out.println("*******************************Fin de la séquence de test de performances*******************************");

        System.out.println();

        System.out.println("********************Début de la séquence des tests d'influence sur la Blocking Queue********************");

        System.out.println("Démarrage du test de la variation du nombre de pistes d'arrivée");
        for (int i = 1; i <= nbPistesMax; i++) {
            Tools.nbPisteArr = i;
            result = testBlockingQueue();
            System.out.println("Nombre de piste(s) : " + i + " -- Temps : " + result + " ms");
        }
        System.out.println("Fin du test de la variation du nombre de pistes d'arrivée !");

        System.out.println();

        System.out.println("Démarrage du test de la variation du nombre de pistes de départs");
        for (int i = 1; i <= nbPistesMax; i++) {
            Tools.nbPisteDep = i;
            result = testBlockingQueue();
            System.out.println("Nombre de piste(s) : " + i + " -- Temps : " + result + " ms");
        }
        System.out.println("Fin du test de la variation du nombre de pistes de départs !");

        System.out.println();

        System.out.println("Démarrage du test de la variation du nombre de place dans le parking");
        for (int i = 1; i <= nbPlaceMax; i++) {
            Tools.nbPlace = i;
            result = testBlockingQueue();
            System.out.println("Nombre de place(s) : " + i + " -- Temps : " + result + " ms");
        }
        System.out.println("Fin du test de la variation du nombre de place dans le parking !");

        System.out.println();

        System.out.println("*********************Fin de la séquence des tests d'influence sur la Blocking Queue*********************");
    }
    
    //Permet de tester/d'afficher graphiquement l'application avec la blocking queue
    private static long testBlockingQueue() {
        //Initialisation de la frame de la blocking queue
        AirportFrameBlockingQueue airportFrame = new AirportFrameBlockingQueue(Tools.nbPisteArr, Tools.nbPisteDep, Tools.nbPlace, Tools.nbAvion);

        BlockingQueue<AvionBlockingQueue> airArr = new ArrayBlockingQueue<AvionBlockingQueue>(Tools.nbAvion);
        BlockingQueue<AvionBlockingQueue> tarmacLand = new ArrayBlockingQueue<AvionBlockingQueue>(Tools.nbPisteArr);
        BlockingQueue<AvionBlockingQueue> tarmacTakeOff = new ArrayBlockingQueue<AvionBlockingQueue>(Tools.nbPisteDep);
        BlockingQueue<AvionBlockingQueue> terminal = new ArrayBlockingQueue<AvionBlockingQueue>(Tools.nbPlace);
        BlockingQueue<AvionBlockingQueue> airDep = new ArrayBlockingQueue<AvionBlockingQueue>(Tools.nbAvion);

        Thread[] tabThread = new Thread[Tools.nbAvion];
        //Initialise le nombre de threads correspondant au nombre d'avions.
        for (int i = 0; i < Tools.nbAvion; i++) {
            AvionBlockingQueue avion = new AvionBlockingQueue(airportFrame, codePlane[i], airArr, tarmacLand, tarmacTakeOff, terminal, airDep);
            tabThread[i] = new Thread(avion);
            tabThread[i].start();
        }
        //Rend visible ou non la frame (selon l'envie de l'utilisateur)
        airportFrame.setVisible(afficherFrame);
        airportFrame.setSize(1000, 800);
        //Si en mode test, alors on lance l'application toute seule et on calcul le temps écoulé entre le premier et le dernier thread.
        if (!afficherFrame) {
            // Start timer
            long start = System.currentTimeMillis();
            airportFrame.getStart().doClick();

            for (int i = 0; i < Tools.nbAvion; i++) {
                try {
                    tabThread[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Stop timer
            long result = System.currentTimeMillis() - start;
            return result;
        }

        return 0;
    }
    
    //Permet de tester/d'afficher graphiquement l'application avec le circular buffer
    private static long testCirularBuffer() {
        //Initialisation de la frame du circular buffer
        AirportFrameCircularBuffer airportFrame = new AirportFrameCircularBuffer(Tools.nbPisteArr, Tools.nbPisteDep, Tools.nbPlace, Tools.nbAvion);

        CircularBuffer<AvionCircularBuffer> airArr = new CircularBuffer<AvionCircularBuffer>(Tools.nbAvion);
        CircularBuffer<AvionCircularBuffer> tarmacLand = new CircularBuffer<AvionCircularBuffer>(Tools.nbPisteArr);
        CircularBuffer<AvionCircularBuffer> tarmacTakeOff = new CircularBuffer<AvionCircularBuffer>(Tools.nbPisteDep);
        CircularBuffer<AvionCircularBuffer> terminal = new CircularBuffer<AvionCircularBuffer>(Tools.nbPlace);
        CircularBuffer<AvionCircularBuffer> airDep = new CircularBuffer<AvionCircularBuffer>(Tools.nbAvion);
        
        Thread[] tabThread = new Thread[Tools.nbAvion];
        //Initialise le nombre de threads correspondant au nombre d'avions.
        for (int i = 0; i < Tools.nbAvion; i++) {
            AvionCircularBuffer avion = new AvionCircularBuffer(airportFrame, codePlane[i], airArr, tarmacLand, tarmacTakeOff, terminal, airDep);
            tabThread[i] = new Thread(avion);
            tabThread[i].start();
        }
        //Rend visible ou non la frame (selon l'envie de l'utilisateur)
        airportFrame.setVisible(afficherFrame);
        airportFrame.setSize(1000, 800);
        //Si en mode test, alors on lance l'application toute seule et on calcul le temps écoulé entre le premier et le dernier thread.
        if (!afficherFrame) {
            // Start timer
            long start = System.currentTimeMillis();
            airportFrame.getStart().doClick();

            for (int i = 0; i < Tools.nbAvion; i++) {
                try {
                    tabThread[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Stop timer
            long result = System.currentTimeMillis() - start;
            return result;
        }
        return 0;
    }
}
