package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {
    public Socket socket;
    public DataInputStream in;
    public DataOutputStream out;

    @FXML
    TextArea messageWindow;

    @FXML
    TextArea userList;

    @FXML
    TextField textField;

    @FXML
    Button button;

    @FXML
    Button connectButton;

    int buttonSwitch = 0;

    @FXML
    private void send(){

        String str = textField.getText();

        try {
            out.writeUTF(str);
            buttonSwitch++;
            textField.clear();
            textField.requestFocus();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (buttonSwitch==1) {
            connectButton.setText(str);
            connectButton.setDisable(true);
            textField.setPromptText("Введите сообщение...");
            connectButton.setStyle("-fx-background-color:rgba(255,255,255,1)");
            connectButton.setStyle("-fx-font-weight:bold");
        }

    }


    public void startClient() {
        try {
            socket = new Socket("localhost",8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            textField.setPromptText("Введите ваше имя...");
            String response = in.readUTF();
            messageWindow.appendText(response+"\n");
            Thread responseThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String response = null;
                        try {
                            response = in.readUTF();
                            String[] responseArray = response.split("]‡");
                            String userNamesString = (responseArray[0].toString().replaceAll(", ","\n")).replaceAll("^\\[","");
                            userList.setText(userNamesString);
                            messageWindow.appendText(responseArray[1]+"\n");
                        } catch (IOException exception) {exception.printStackTrace();};
                    }
                }
            });
            responseThread.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}