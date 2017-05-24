/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.com.version2;

import java.util.Random;

/*******************************************************************************
 * Le comportement de cette classe est exactement le même que la classe
 * AvionBlockingQueue, seulement nous utilison des CircularBuffer qui a été 
 * créé par nos soins. (voir classe CircularBuffer)
 * Pour avoir plus de détails sur le comportement de cette classe veuillez vous
 * référez aux commentaires de la classe AvionBlockingQueue. 
 ******************************************************************************/
public class AvionCircularBuffer implements Runnable {
    //Frame sur laquelle les avions seront affichés.
    private AirportFrameCircularBuffer airportFrame;
    //Code de l'avion
    private String codePlane;
    //Objet random
    private static Random random = new Random();
    //Les circulars buffers permettant le bon déroulement des étapes de l'avion.
    CircularBuffer<AvionCircularBuffer> airArr;
    CircularBuffer<AvionCircularBuffer> tarmacLand;
    CircularBuffer<AvionCircularBuffer> tarmacTakeOff;
    CircularBuffer<AvionCircularBuffer> terminal;
    CircularBuffer<AvionCircularBuffer> airDep;
    //Constructeur de l'avion
    public AvionCircularBuffer(AirportFrameCircularBuffer _airportFrame, String _codePlane, CircularBuffer<AvionCircularBuffer> _airArr, CircularBuffer<AvionCircularBuffer> _tarmacLand, CircularBuffer<AvionCircularBuffer> _tarmacTakeOff, CircularBuffer<AvionCircularBuffer> _terminal, CircularBuffer<AvionCircularBuffer> _airDep) {
        airportFrame = _airportFrame;
        codePlane = _codePlane;

        airArr = _airArr;
        tarmacLand = _tarmacLand;
        tarmacTakeOff = _tarmacTakeOff;
        terminal = _terminal;
        airDep = _airDep;
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
}
