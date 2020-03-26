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

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import model.Filosofo;
import javafx.fxml.FXML;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.concurrent.Semaphore;
import javafx.scene.control.Spinner;

public class ControladorTela implements Initializable{
  @FXML public ImageView filosofo0, filosofo1, filosofo2, filosofo3, filosofo4, garfo0, garfo1, garfo2, garfo3, garfo4;  //todos os ImageView que estao na tela fxml
  Filosofo f0, f1, f2, f3, f4;  //todos os filosofos
  @FXML Spinner<Integer> p0, p1, p2, p3, p4, c0, c1, c2, c3, c4;  //todos os Spinner de controle de tempo de comer e de pensar
  public int n = 5;  //numero de filosofos
  public Semaphore mutex;  //variavel do Semaforo
  public Semaphore[] arraysemaforo;  //array de Semaforos
  public int[] estado;  //array de inteiros para os estados de cada filosofos
  public ImageView[] garfos = new ImageView[n];  //array de ImageView para os garfos

  public ControladorTela(){
    //instanciando abaixo todas as ImageView da tela
    filosofo0 = new ImageView();
    filosofo1 = new ImageView();
    filosofo2 = new ImageView();
    filosofo3 = new ImageView();
    filosofo4 = new ImageView();
    garfo0 = new ImageView();
    garfo1 = new ImageView();
    garfo2 = new ImageView();
    garfo3 = new ImageView();
    garfo4 = new ImageView();
    //instanciando abaixo todos os Spinner da tela
    p0 = new Spinner<>();
    p1 = new Spinner<>();
    p2 = new Spinner<>();
    p3 = new Spinner<>();
    p4 = new Spinner<>();
    c0 = new Spinner<>();
    c1 = new Spinner<>();
    c2 = new Spinner<>();
    c3 = new Spinner<>();
    c4 = new Spinner<>();
    //colocando as ImageView dos garfos no array de ImageView
    garfos[0] = garfo0;
    garfos[1] = garfo1;
    garfos[2] = garfo2;
    garfos[3] = garfo3;
    garfos[4] = garfo4;
    //instanciando abaixo todos os filosofos
    f0 = new Filosofo();
    f1 = new Filosofo();
    f2 = new Filosofo();
    f3 = new Filosofo();
    f4 = new Filosofo();
    //instanciando o mutex - Semaforo
    mutex = new Semaphore(1);
    //instanciando abaixo os arrays de estado e semaforo
    arraysemaforo = new Semaphore[n];
    estado = new int[n];
    for(int i = 0; i < n; i++){
      arraysemaforo[i] = new Semaphore(0);
      estado[i] = 0;
    }
  }  //fim do metodo contrutor da classe

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: metodo principal da classe Initializable
  * Parametros: URL, ResourceBundle
  * Retorno: *sem retorno*
  *************************************************************** */
  @Override
  public void initialize(URL url, ResourceBundle rb){
    //mudando os valores dos atributos do filosofo0 e iniciando a Thread   
    f0.setControlador(this);
    f0.setGarfos(garfo4, garfo0);
    f0.setDaemon(true);
    f0.setId(0);
    f0.start();
    //mudando os valores dos atributos do filosofo1 e iniciando a Thread 
    f1.setControlador(this);
    f1.setGarfos(garfo0, garfo1);
    f1.setDaemon(true);
    f1.setId(1);
    f1.start();
    //mudando os valores dos atributos do filosofo2 e iniciando a Thread 
    f2.setControlador(this);
    f2.setGarfos(garfo1, garfo2);
    f2.setDaemon(true);
    f2.setId(2);
    f2.start();
    //mudando os valores dos atributos do filosofo3 e iniciando a Thread 
    f3.setControlador(this);
    f3.setGarfos(garfo2, garfo3);
    f3.setDaemon(true);
    f3.setId(3);
    f3.start();
    //mudando os valores dos atributos do filosofo4 e iniciando a Thread 
    f4.setControlador(this);
    f4.setGarfos(garfo3, garfo4);
    f4.setDaemon(true);
    f4.setId(4);
    f4.start();
    //chama o metodo para atualizar os valores de tempo de comer e pensar
    atualizarValores();
  }  //fim do metodo initialize

  /* ***************************************************************
  * Metodo: atualizarValores
  * Funcao: atualiza os valores do tempo de comer e pensar dos
  *   filosofos a partir do valor de cada Spinner
  * Parametros: *sem paramentros*
  * Retorno: *sem retorno*
  *************************************************************** */
  @FXML
  public void atualizarValores(){
    //muda o tempo de comer e pensar de cada filosofo de acordo com o valor seu Spinner
    f0.setTempoComendo((int) c0.getValue());
    f0.setTempoPensando((int) p0.getValue());
    f1.setTempoComendo((int) c1.getValue());
    f1.setTempoPensando((int) p1.getValue());
    f2.setTempoComendo((int) c2.getValue());
    f2.setTempoPensando((int) p2.getValue());
    f3.setTempoComendo((int) c3.getValue());
    f3.setTempoPensando((int) p3.getValue());
    f4.setTempoComendo((int) c4.getValue());
    f4.setTempoPensando((int) p4.getValue());
  }  //fim do metodo atualizarValores

  /* ***************************************************************
  * Metodo: colocaGarfos
  * Funcao: aparece as imagens dos garfos na mesa
  * Parametros: *sem paramentro*
  * Retorno: *sem retorno*
  *************************************************************** */
  public void colocaGarfos(Filosofo f){
    //faz aparecer as imagens dos garfos do lado desse filosofo
    f.garfoDireita.setVisible(true);
    f.garfoEsquerda.setVisible(true);
  }  //fim do metodo colocaGarfos

  /* ***************************************************************
  * Metodo: pensar
  * Funcao: muda a imagem do filosofo para 'pensando' e hiberna a
  *   Thread de acordo com tempoPensando desse filosofo
  * Parametros: o filosofo que chamou o metodo
  * Retorno: *sem retorno*
  *************************************************************** */
  public void pensar(Filosofo f){
    //muda a imagem do filosofo de acordo com seu id
    switch(f.getid()){
      case 0:
        filosofo0.setImage(new Image("imagens/sim_pensando.png"));
        break;
      case 1:
        filosofo1.setImage(new Image("imagens/sim_pensando.png"));
        break;
      case 2:
        filosofo2.setImage(new Image("imagens/sim_pensando.png"));
        break;
      case 3:
        filosofo3.setImage(new Image("imagens/sim_pensando.png"));
        break;
      case 4:
        filosofo4.setImage(new Image("imagens/sim_pensando.png"));
        break;
      default:
        break;
    }
    //hiberna a Thread de acordo com o tempo de pensar desse filosofo
    try{
      f.sleep(f.getTempoPensando());
    }catch(InterruptedException e){

    }
  }  //fim do metodo pensar

  /* ***************************************************************
  * Metodo: fome
  * Funcao: muda a imagem do filosofo para 'fome'
  * Parametros: inteiro identificador do filosofo
  * Retorno: *sem retorno*
  *************************************************************** */
  public void fome(int i){
    //muda a imagem do filosofo de acordo com seu id
    switch(i){
      case 0:
        filosofo0.setImage(new Image("imagens/sim_fome.png")); 
        break;
      case 1:
        filosofo1.setImage(new Image("imagens/sim_fome.png")); 
        break;
      case 2:
        filosofo2.setImage(new Image("imagens/sim_fome.png")); 
        break;
      case 3:
        filosofo3.setImage(new Image("imagens/sim_fome.png")); 
        break;
      case 4:
        filosofo4.setImage(new Image("imagens/sim_fome.png")); 
        break;
      default:
        break;
    }
  }  //fim do metodo fome

  /* ***************************************************************
  * Metodo: come
  * Funcao: muda a imagem do filosofo para 'comendo', desaparece
  *   as imagens dos garfos na mesa e hiberna a Thread de acordo o
  *   tempoComendo desse filosofo
  * Parametros: filosofo que esta chamando o metodo
  * Retorno: *sem retorno*
  *************************************************************** */
  public void come(Filosofo f){
    //muda a imagem do filosofo de acordo com seu id
    switch(f.getid()){
      case 0:
        filosofo0.setImage(new Image("imagens/sim_comendo.png"));
        break;
      case 1:
        filosofo1.setImage(new Image("imagens/sim_comendo.png"));
        break;
      case 2:
        filosofo2.setImage(new Image("imagens/sim_comendo.png"));
        break;
      case 3:
        filosofo3.setImage(new Image("imagens/sim_comendo.png"));
        break;
      case 4:
        filosofo4.setImage(new Image("imagens/sim_comendo.png"));
        break;
      default:
        break;
    }
    //faz sumir as imagens dos garfos do lado desse filosofo
    f.garfoDireita.setVisible(false);
    f.garfoEsquerda.setVisible(false);
    //hiberna a Thread de acordo com o tempo de comer desse filosofo
    try{
      f.sleep(f.getTempoComendo());
    }catch(InterruptedException e){}
  }  //fim do metodo come

  /* ***************************************************************
  * Metodo: esq
  * Funcao: verifica qual o indice da esquerda do indice passado
  * Parametros: inteiro do indice ao qual quer o indice da esquerda
  * Retorno: indice da esquerda do inteiro passado
  *************************************************************** */
  public int esq(int i){
    if(i + 1 < n){  //verifica se o valor a esquerda esta fora do array
      return i + 1;
    }
    return 0;
  }  //fim do metodo esq

  /* ***************************************************************
  * Metodo: dir
  * Funcao: verifica qual o indice da direita do indice passado
  * Parametros: inteiro do indice ao qual quer o indice da direita
  * Retorno: indice da direita do inteiro passado
  *************************************************************** */
  public int dir(int i){
    if(i - 1 >= 0){  //verifica se o valor a direita esta fora do array
      return i - 1;
    }
    return n - 1;
  }  //fim do metodo dir

}  //fim da classe ControladorTela
