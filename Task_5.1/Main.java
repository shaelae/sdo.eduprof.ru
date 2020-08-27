import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> list = new ArrayList<>();
        while (true) {
            String family = reader.readLine();
            if (family.isEmpty()) {
                break;
            }

            list.add(family);
        }

        String city = reader.readLine();

        for (int i=0;i<list.size();i++) {
            if (list.get(i).equals(city)) {
                String familyName = list.get(i+1);
                System.out.println(familyName);
                break;
            } else if (i==list.size()-1) System.out.println("В этом городе никто не живет.");
        }
    }
}
