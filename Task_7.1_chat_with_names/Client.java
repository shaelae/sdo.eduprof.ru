import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8189);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пожалуйста, представьтесь, чтобы начать общение:");
            String name = scanner.nextLine();
            out.writeUTF(name);
            System.out.println("Спасибо, теперь вы можете отправлять сообщения!");
            Thread responseThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String response = null;
                        try {
                            response = in.readUTF();
                            System.out.println(response);
                        } catch (IOException exception) {exception.printStackTrace();};
                    }
                }
            });
            responseThread.start();
            while (true) {
                String str = scanner.nextLine();
                out.writeUTF(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
