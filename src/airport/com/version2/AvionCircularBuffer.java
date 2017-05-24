/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.com.version2;

import java.util.Random;

/**
 *
 * @author pedro.costa
 */
public class AvionCircularBuffer implements Runnable{

    AirportFrameCircularBuffer airportFrame;
    String codePlane;
    CircularBuffer<AvionCircularBuffer> airArr;
    CircularBuffer<AvionCircularBuffer> tarmacLand;
    CircularBuffer<AvionCircularBuffer> tarmacTakeOff;
    CircularBuffer<AvionCircularBuffer> terminal;
    CircularBuffer<AvionCircularBuffer> airDep;
    int nbAvion;
    int nbPisteArr;
    int nbPisteDep;
    int nbPlace;

    int position;

    public AvionCircularBuffer(AirportFrameCircularBuffer _airportFrame, String _codePlane, CircularBuffer<AvionCircularBuffer> _airArr, CircularBuffer<AvionCircularBuffer> _tarmacLand, CircularBuffer<AvionCircularBuffer> _tarmacTakeOff, CircularBuffer<AvionCircularBuffer> _terminal, CircularBuffer<AvionCircularBuffer> _airDep, int _nbAvion, int _nbPisteArr, int _nbPisteDep, int _nbPlace) {
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
            Thread.sleep(1000);
            atterit();
            Thread.sleep(1000);
            parque();
            Thread.sleep(1000);
            decolle();
            Thread.sleep(1000);
            part();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------------------------------------*\
     |*			Methodes Private				*|
     \*------------------------------------------------------------------*/
    private synchronized void arrive() throws InterruptedException {
        airArr.push(this);
        airportFrame.updateArrive(this);
    }

    private synchronized void atterit() throws InterruptedException {
        tarmacLand.push(this);
        airArr.pull();
        airportFrame.updateAtterrit(this);
    }

    private synchronized void parque() throws InterruptedException {
        terminal.push(this);
        tarmacLand.pull();
        airportFrame.updatePark(this);
    }

    private synchronized void decolle() throws InterruptedException {
        tarmacTakeOff.push(this);
        terminal.pull();
        airportFrame.updateDecolle(this);
    }

    private synchronized void part() throws InterruptedException {
        airDep.push(this);
        tarmacTakeOff.pull();
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
