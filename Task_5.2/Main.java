import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу в обычном формате (C:\\folder\\filename.txt)");
        String path = scanner.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        System.out.println(parseLines(reader));

    }

    public static Object parseLines(BufferedReader reader) throws IOException {
        ArrayList<Integer> nums = new ArrayList<>();
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (Integer.parseInt(line)%2==0) nums.add(Integer.parseInt(line));
        }
        Collections.sort(nums);
        for (int i=0;i< nums.size();i++) {
            content.append(nums.get(i));
            content.append(System.lineSeparator());
        }
        return content.toString();
    }
}
