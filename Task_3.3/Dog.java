public class Dog {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Dog bobik = new Dog();
        bobik.setName("Бобик");
        bobik.setAge(7);
        System.out.println(bobik.getName()+", "+bobik.getAge());

    }
}
