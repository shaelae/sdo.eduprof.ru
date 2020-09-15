package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Server {

    static ArrayList<Socket> clients = new ArrayList<>();
    static ArrayList<String> userNames = new ArrayList<>(); //коллекция для имен пользователей


    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен на порту " + serverSocket.getLocalPort());
            while (true) {
                Socket socket = serverSocket.accept();
                clients.add(socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Подключен клиент");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String userName = null;
                        try {
                            out.writeUTF("[Сообщение сервера] Пожалуйста, введите ваше имя и нажмите «Отправить».");
                            userName = in.readUTF();
                            userNames.add(userName);
                            System.out.println("Подключен клиент " + userName);
                            out.writeUTF(userNames+"‡"+"[Сообщение сервера] Очень приятно, "+userName+". Теперь вы можете отправлять сообщения.");
                            while (true) {
                                String str = in.readUTF();
                                BroadcastMsg(str, userName);
                                System.out.println("Клиент " + userName + " прислал сообщение: " + str);
                            }
                        } catch (IOException e) {
                            System.out.println("Клиент отключился");

                        } finally {
                            try {
                                clients.remove(socket);
                                userNames.remove(userName);
                                socket.close();

                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void BroadcastMsg(String str, String userName) throws IOException {
        DataOutputStream out;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        for (Socket socket : clients) {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(userNames+"‡"+"["+dtf.format(now)+"] "+userName + ": " + str);
        }
    }}
