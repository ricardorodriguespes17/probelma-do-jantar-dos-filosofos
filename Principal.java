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

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import controller.ControladorTela;

public class Principal extends Application{
 
  /* ***************************************************************
  * Metodo: start
  * Funcao: metodo principal da classe Application que inicia o
  *   palco principal
  * Parametros: palco principal da aplicacao
  * Retorno: *sem retorno*
  *************************************************************** */
  @Override
  public void start(Stage palco) throws Exception{
    Parent r = FXMLLoader.load(getClass().getResource("/view/Tela.fxml"));  //carrega o caminho da tela fxml para o Parent
    palco.setScene(new Scene(r)); //muda a cena do palco pela nova cena que contem o caminho da tela fxml
    palco.show();  //mostra o palco na tela
  }
  
  /* ***************************************************************
  * Metodo: main
  * Funcao: metodo principal do projeto
  * Parametros: String[] args
  * Retorno: *sem retorno*
  *************************************************************** */
	public static void main(String[] args){
		launch(args);  //iniciando aplicação
	}  //fim do metodo main
}  //fim da classe Principal
