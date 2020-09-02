import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        int a = 0;
        int b = 0;

        Class<?> c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        List<Integer> cat1Stats = new ArrayList<>();
        List<Integer> cat2Stats = new ArrayList<>();


        for( Field field : fields ){
            try {
                cat1Stats.add((int) field.get(this));
                cat2Stats.add((int) field.get(anotherCat));
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }

        for (int i = 0; i<cat1Stats.size(); i++) {
            if (cat1Stats.get(i)>cat2Stats.get(i)) a++;
            else if (cat1Stats.get(i)<cat2Stats.get(i)) b++;
        }

        if (a == b)
            return this.hashCode()>anotherCat.hashCode();
        else return a>b;
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        cat1.age = 6;
        cat1.weight = 6;
        cat1.strength = 6;
        Cat cat2 = new Cat();
        cat2.age = 3;
        cat2.weight = 3;
        cat2.strength = 3;
        Cat cat3 = new Cat();
        cat3.age = 1;
        cat3.weight = 2;
        cat3.strength = 2;
        Cat cat4 = new Cat();
        cat4.age = 2;
        cat4.weight = 1;
        cat4.strength = 2;
        Cat cat5 = new Cat();
        cat5.age = 1;
        cat5.weight = 1;
        cat5.strength = 1;
        Cat cat6 = new Cat();
        cat6.age = 1;
        cat6.weight = 1;
        cat6.strength = 1;

        System.out.println("Кот1 vs Кот2: "+ cat1.fight(cat2)); // сильный vs слабый
        System.out.println("Кот2 vs Кот1: "+ cat2.fight(cat1)); // слабый vs сильный
        System.out.println("---");
        System.out.println("Кот3 vs Кот4: "+ cat3.fight(cat4)); // равносильные
        System.out.println("Кот4 vs Кот3: "+ cat4.fight(cat3)); // коты
        System.out.println("---");
        System.out.println("Кот5 vs Кот6: "+ cat5.fight(cat6)); // идентичные
        System.out.println("Кот6 vs Кот5: "+ cat6.fight(cat5)); // коты
    }
}
