package gui.utils;



import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {
		return (Stage)((Node) event.getSource()).getScene().getWindow();
	}
	
	public static Integer tryParseToInt(String str) {//ler um inteiro de uma caixa de texto
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return null;
		}
	}

}
