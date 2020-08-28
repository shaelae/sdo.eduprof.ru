import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class proxyCheckerN2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу в обычном формате (C:\\folder\\filename.txt)");
        String path = scanner.nextLine();

        FileReader reader = new FileReader(path);
        int c;
        String iplist = "";

        while((c=reader.read())!=-1){
            iplist += (char) c;
        }

        String[] ipListArray = iplist.split("\r");

        for (int i=0;i< ipListArray.length;i++) {
            String[] ipListArrayElement = ipListArray[i].split(":");
            String ip = ipListArrayElement[0].trim();
            int port = Integer.parseInt(ipListArrayElement[1]);
            Thread t1 = new Thread(new myRunnableClass(ip,port));
            t1.start();
        }
    }
}

class myRunnableClass implements Runnable {

    String ip = "";
    int port;

    public myRunnableClass(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        try {

            URLConnection connection = new URL("https://vozhzhaev.ru/test.php").openConnection(proxy);

            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            char[] buffer = new char[256];
            int rc;

            StringBuilder sb = new StringBuilder();

            while ((rc = reader.read(buffer)) != -1)
                sb.append(buffer, 0, rc);

            reader.close();
            System.out.println(Thread.currentThread().getName()+" == "+ip+":"+port+" доступен");
        } catch (Exception e) { System.out.println(Thread.currentThread().getName()+" == "+ip+":"+port+" недоступен"); }
        }
    }
