/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airport.com.version2;

/*******************************************************************************
 * Classe créée permettant d'avoir un buffer circulaire.
 * Nous nous sommes inspirés d'internet pour la création de cette dernière.
 * (https://stackoverflow.com/questions/590069/how-would-you-code-an-efficient-circular-buffer-in-java-or-c-sharp)
 ******************************************************************************/
public class CircularBuffer<T>{
    private final int bufferLength; //Taille de notre buffer
    private final T[] data; //Tableau générique contenant nos données
    private int in = 0; //Pointeur des entrées
    private int out = 0; //Pointeur des sorties
    private int validSpace = 0; //Espace valide de notre buffer
    //Constructeur du buffer
    public CircularBuffer(int bufferLength){
        this.bufferLength = bufferLength;
        this.data = (T[]) new Object[this.bufferLength];//Tableau générique car nous ne savons pas ce qu'il contiendra
    }
    //Fonction ajoutant un élément au pointeur d'entrée de notre buffer
    public synchronized void push(T element){
        //Boucle très importante, elle permet d'attendre si il n'y a plus de place dans le buffer
        while (this.validSpace == this.bufferLength) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function putElement");
		e.printStackTrace();
	    }
	}
        this.data[this.in] = element;  //insère l'élément
	this.in = (this.in+1)%this.bufferLength; //procède à la bonne rotation de notre index
	this.validSpace++; //incrémente l'espace valide
	notifyAll(); //indique à tous les threads de se réveiller.
    }
    //Retourne l'élément au pointeur de sortie de notre buffer
    public synchronized T pull(){
        //Boucle également importante permettant d'attendre lorsque le buffer est vide
        while (this.validSpace == 0) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function takeElement");
		e.printStackTrace();
	    }
	}
	T element = this.data[this.out]; //récupère l'élément à l'indexe de sortie 
	this.out = (this.out+1)%this.bufferLength; //procède à la bonne rotation de l'index de sortie
	this.validSpace--; //décrémente l'espace valide
	notifyAll(); //indique à tous les threads de se réveiller.
	return element; //retourne l'élément
    } 
}
