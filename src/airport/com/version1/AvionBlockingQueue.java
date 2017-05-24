package airport.com.version1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
//represente l'avion

public class AvionBlockingQueue implements Runnable {
    //Frame sur laquelle les avions seront affichés.
    private AirportFrameBlockingQueue airportFrame;
    //Code de l'avion
    private String codePlane;
    //Objet random
    private static Random random = new Random();
    //Les blocking queues permettant le bon déroulement des étapes de l'avion.
    private BlockingQueue<AvionBlockingQueue> airArr;
    private BlockingQueue<AvionBlockingQueue> tarmacLand;
    private BlockingQueue<AvionBlockingQueue> tarmacTakeOff;
    private BlockingQueue<AvionBlockingQueue> terminal;
    private BlockingQueue<AvionBlockingQueue> airDep;
    //Constructeur de l'avion
    public AvionBlockingQueue(AirportFrameBlockingQueue _airportFrame, String _codePlane, BlockingQueue<AvionBlockingQueue> _airArr, BlockingQueue<AvionBlockingQueue> _tarmacLand, BlockingQueue<AvionBlockingQueue> _tarmacTakeOff, BlockingQueue<AvionBlockingQueue> _terminal, BlockingQueue<AvionBlockingQueue> _airDep) {
        airportFrame = _airportFrame;
        codePlane = _codePlane;

        airArr = _airArr;
        tarmacLand = _tarmacLand;
        tarmacTakeOff = _tarmacTakeOff;
        terminal = _terminal;
        airDep = _airDep;
    }
    //Vu que notre avion implémente Runnable, on doit implémenter la fonction run
    @Override
    public void run() {
        try {
            //Ici on appelle toutes les fonctions qui procéderont aux étapes 
            //nécessaires pour notre avion.
            //Remplacer les nextRandomTime par 1000 (ms) pour avoir des temps fixes.
            arrive();
            Thread.sleep(nextRandomTime());
            atterit();
            Thread.sleep(nextRandomTime());
            parque();
            Thread.sleep(nextRandomTime());
            decolle();
            Thread.sleep(nextRandomTime());
            part();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------------------------------------*\
    |*			Methodes Private				*|
    \*------------------------------------------------------------------*/
    //Ici on déclare les fonctions qui permettent de faire bouger notre avion
    //entre chaque étape demandée. On ajoute l'avion aux blocking queue correspondantes
    //et on met à jour l'affichage se trouvant sur la frame.
    
    //Ici on ajoute l'avion à la blocking queue des arrivées.
    private synchronized void arrive() throws InterruptedException {
        airArr.put(this);
        airportFrame.updateArrive(this);
    }

    //Ici on ajoute à celle des attérissage et on l'enlève de celle d'arrivée.
    private synchronized void atterit() throws InterruptedException {
        tarmacLand.put(this);
        airArr.remove(this);
        airportFrame.updateAtterrit(this);
    }
    //Pareil pour les parking
    private synchronized void parque() throws InterruptedException {
        terminal.put(this);
        tarmacLand.remove(this);
        airportFrame.updatePark(this);
    }
    //Pour celle des décollages
    private synchronized void decolle() throws InterruptedException {
        tarmacTakeOff.put(this);
        terminal.remove(this);
        airportFrame.updateDecolle(this);
    }
    //et des départs
    private synchronized void part() throws InterruptedException {
        airDep.put(this);
        tarmacTakeOff.remove(this);
        airportFrame.updatePart(this);
    }
    
    //Getter sur le code
    public String getCode() {
        return codePlane;
    }
    
    //Renvoi un temps aléatoire pour donner quelque chose de "réalistique"
    private long nextRandomTime() {
        long ret = (long) random.nextInt() % 2000;
        if (ret < 0) {
            return ret * (-1) + 1000;
        }
        return ret + 1000;
    }
}
