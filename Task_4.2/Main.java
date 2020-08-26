public class Main {
    public static void main(String[] args) {
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);
        Mouse musclesMouse = new Mouse("Cousin Muscles", 14, 3);
        Mouse pecosMouse = new Mouse("Uncle Pecos", 12, 6);
        Mouse nibblesMouse = new Mouse("Nibbles", 6, 4);
        Cat tomCat = new Cat("Tom", "grey", 'm');
        Cat butchCat = new Cat("Butch", "black", 'm');
        Cat toddlesCat = new Cat("Toddles Galore", "white", 'f');
        Dog spikeDog = new Dog("Spike",true,true);
        Dog tykeDog = new Dog("Tyke",false,false);
    }
}
class Mouse {
    String name;
    int height;
    int tail;

    public Mouse(String name, int height, int tail) {
        this.name = name;
        this.height = height;
        this.tail = tail;
    }
}

class Cat {
    String name;
    String furColor;
    char gender;

    public Cat(String name, String furColor, char gender) {
        this.name = name;
        this.furColor = furColor;
        this.gender = gender;
    }
}

class Dog {
    String name;
    boolean isBuff;
    boolean isDumb;

    public Dog(String name, boolean isBuff, boolean isDumb) {
        this.name = name;
        this.isBuff = isBuff;
        this.isDumb = isDumb;
    }
}
