public class Main{
    public static void main(String[] args) {
        Horse droolie = new Horse("Слюнка", "лошадь", 80);
        droolie.run();
        Pegasus vasilualiy = new Pegasus("Васисуалий", "пегас", 70, true);
        vasilualiy.fly();
    }
}

abstract class Animal {
    protected String name;
    protected String breed;

    public Animal(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }
}

class Horse extends Animal {
    private int carryWeight;
    public Horse(String name, String breed, int carryWeight) {
        super (name, breed);
        this.carryWeight = carryWeight;
    }

    public void run(){ System.out.println("Игого, я поскакал(а)"); }
}

class Pegasus extends Horse {
    private boolean canFly;

    public Pegasus(String name, String breed, int carryWeight, boolean canFly) {
        super(name, breed, carryWeight);
        this.canFly = canFly;
    }

    void fly(){ System.out.println("Игого, я полетел(а)"); }
}
