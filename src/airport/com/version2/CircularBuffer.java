/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.com.version2;

/**
 *
 * @author pedro.costa
 */
public class CircularBuffer<T>{
    private final int bufferLength; 
    private final T[] data; 
    private int in = 0; 
    private int out = 0; 
    private int validSpace = 0;
    
    public CircularBuffer(int bufferLength){
        this.bufferLength = bufferLength;
        this.data = (T[]) new Object[this.bufferLength];
    }
    
    public synchronized void push(T element){
        while (this.validSpace == this.bufferLength) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function putElement");
		e.printStackTrace();
	    }
	}
        this.data[this.in] = element; 
	this.in = (this.in+1)%this.bufferLength; 
	this.validSpace++;
	notifyAll(); 
    }
    
    public synchronized T pull(){
        while (this.validSpace == 0) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function takeElement");
		e.printStackTrace();
	    }
	}
	T element = this.data[this.out];
	this.out = (this.out+1)%this.bufferLength;
	this.validSpace--;
	notifyAll();
	return element;
    } 
}
