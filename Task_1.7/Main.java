import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        if (a ==0 || b == 0  || c == 0)
        {
            System.out.println("Плоский бассейн? Отличный выбор.\nХватит "+a*b*c*1000+" литров воды.");
        }
        else {
            System.out.println("Объем бассейна (м³): "+a*b*c+"\nДля заполнения нужно "+a*b*c*1000+" литров воды.");
        }
    }
}
