package airport.com.version1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
//represente l'avion

public class AvionBlockingQueue implements Runnable {

    AirportFrameBlockingQueue airportFrame;
    String codePlane;
    BlockingQueue<AvionBlockingQueue> airArr;
    BlockingQueue<AvionBlockingQueue> tarmacLand;
    BlockingQueue<AvionBlockingQueue> tarmacTakeOff;
    BlockingQueue<AvionBlockingQueue> terminal;
    BlockingQueue<AvionBlockingQueue> airDep;
    int nbAvion;
    int nbPisteArr;
    int nbPisteDep;
    int nbPlace;

    int position;

    public AvionBlockingQueue(AirportFrameBlockingQueue _airportFrame, String _codePlane, BlockingQueue<AvionBlockingQueue> _airArr, BlockingQueue<AvionBlockingQueue> _tarmacLand, BlockingQueue<AvionBlockingQueue> _tarmacTakeOff, BlockingQueue<AvionBlockingQueue> _terminal, BlockingQueue<AvionBlockingQueue> _airDep, int _nbAvion, int _nbPisteArr, int _nbPisteDep, int _nbPlace) {
        airportFrame = _airportFrame;
        codePlane = _codePlane;

        airArr = _airArr;
        tarmacLand = _tarmacLand;
        tarmacTakeOff = _tarmacTakeOff;
        terminal = _terminal;
        airDep = _airDep;

        nbAvion = _nbAvion;
        nbPisteArr = _nbPisteArr;
        nbPisteDep = _nbPisteDep;
        nbPlace = _nbPlace;
    }

    @Override
    public void run() {
        try {
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
    private synchronized void arrive() throws InterruptedException {
        airArr.put(this);
        airportFrame.updateArrive(this);
    }

    private synchronized void atterit() throws InterruptedException {
        tarmacLand.put(this);
        airArr.remove(this);
        airportFrame.updateAtterrit(this);
    }

    private synchronized void parque() throws InterruptedException {
        terminal.put(this);
        tarmacLand.remove(this);
        airportFrame.updatePark(this);
    }

    private synchronized void decolle() throws InterruptedException {
        tarmacTakeOff.put(this);
        terminal.remove(this);
        airportFrame.updateDecolle(this);
    }

    private synchronized void part() throws InterruptedException {
        airDep.put(this);
        tarmacTakeOff.remove(this);
        airportFrame.updatePart(this);
    }

    public String getCode() {
        return codePlane;
    }

    private long nextRandomTime() {
        long ret = (long) random.nextInt() % 2000;
        if (ret < 0) {
            return ret * (-1) + 1000;
        }
        return ret + 1000;
    }

    /*------------------------------------------------------------------*\
     |*                     Attributs Private				*|
     \*------------------------------------------------------------------*/
    private static Random random = new Random();

}
