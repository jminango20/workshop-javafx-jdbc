package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller)->{
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
		
	}

	@FXML
	public void onMenuItemDepartmentAction() { //passo uma funcao para inicializar o controllador: controller
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller)->{
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAbout() { //paso acao de inicializacao
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	
	//abrir outra tela | loadView eh uma funcao Generica do tipo T
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction ) { //recebe o nome da View | uso interfaz funcional
		try {
			
			//Carga a Janela
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene(); //eh static
			VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent(); //pega o primeiro elemento da minha view
			
			//Salvar uma referencia para o Menu
			Node mainMenu = mainVBox.getChildren().get(0); //Primerio filho do mainVBox
			mainVBox.getChildren().clear(); //limpo todos os childrens
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			//Ativar a funcao que passei no Consumer
			T controller = loader.getController(); //retorno o controllado do tipo que eu chamo
			initializingAction.accept(controller); //executa a funcao ver acima na chamada ao metodo
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Erro loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
		
	
}
