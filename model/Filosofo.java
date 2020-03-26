/* ***************************************************************
* Autor: Ricardo Rodrigues Neto
* Matricula: 201710560
* Inicio: 28/08/2018
* Ultima 06/09/2018
* Nome: Problema do Jantar dos filosofos
* Funcao: Simular uma concorrencia entre os filosofos por garfos
*   que estao dispostos na mesa. Sendo que cada filosofo precisa
*   de dois garfos para comer o macarrao do prato. 
*************************************************************** */

package model;

import controller.ControladorTela;
import java.lang.Thread;
import javafx.scene.image.ImageView;

public class Filosofo extends Thread{
  public int n = 5;
  private ControladorTela c;
  public ImageView garfoEsquerda, garfoDireita;
  private int pensando = 0, fome = 1, comendo = 2;
  private int id;
  private int tempoComendo = 5000, tempoPensando = 5000;

  public void run(){
    while(1 > 0){
      try{
        c.pensar(this);
        pegaGarfos();
        c.come(this);
        devolveGarfos();
      }catch(InterruptedException e){}
    }
  }

  public void pegaGarfos() throws InterruptedException{    
    c.mutex.acquire();
    c.estado[id] = fome;
    c.fome(id);
    testa(id);
    c.mutex.release();
    c.arraysemaforo[id].acquire();
  }

  public void testa(int i) throws InterruptedException{
    if(c.estado[i] == fome && c.estado[c.esq(i)] != comendo && c.estado[c.dir(i)] != comendo){
      c.estado[i] = comendo;
      c.arraysemaforo[i].release();
    }
  }

  public void devolveGarfos() throws InterruptedException{
    c.mutex.acquire();
    c.estado[id] = pensando;
    c.colocaGarfos(this);
    testa(c.esq(id));
    testa(c.dir(id));
    c.mutex.release();
  }

  public int getid(){
    return id;
  }

  public int getTempoComendo(){
    return tempoComendo;
  }

  public int getTempoPensando(){
    return tempoPensando;
  }

  public void setGarfos(ImageView garfoEsquerda, ImageView garfoDireita){
    this.garfoDireita = garfoDireita;
    this.garfoEsquerda = garfoEsquerda;
  }

  public void setId(int id){
    this.id = id;
  }

  public void setControlador(ControladorTela c){
    this.c = c;
  }

  public void setTempoComendo(int tempoComendo){
    this.tempoComendo = tempoComendo * 1000;
  }

  public void setTempoPensando(int tempoPensando){
    this.tempoPensando = tempoPensando * 1000;
  }
}
