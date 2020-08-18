//не сказано, какое число, поэтому допустим большое вещественное
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double bignum = scan.nextDouble();
        bignum = (bignum*115)/100;
        System.out.println(bignum);
    }
    //Серьезно?..
}
