package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    TextArea messageWindow;

    @FXML
    TextField ololo;

    @FXML
    Button button;

    @FXML
    private void send(){
        messageWindow.setText("Hello world");
        String str = ololo.getText();
    }
}
