import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static ArrayList<Socket> clients = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();

    public static void main(String[] args) {

        Socket socket = null;

        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен на порту "+serverSocket.getLocalPort());
            while (true){
                socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                clients.add(socket);
                int a = clients.indexOf(socket);
                String name = in.readUTF();
                names.add(name);
                System.out.println("Подключен клиент "+name+". // "+socket);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                String str = in.readUTF();
                                BroadcastMsg(names.get(a)+": "+str);
                                System.out.println("Клиент "+names.get(a)+" прислал сообщение: "+str);
                                //out.writeUTF("Сообщение "+str+" от "+name+" принято.");
                            }
                        } catch (IOException e) { e.printStackTrace(); }
                    }
                });
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void BroadcastMsg(String str) throws IOException{
        DataOutputStream out;
        for (Socket socket : clients) {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(str);
        }
    }

}
