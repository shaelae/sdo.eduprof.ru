public class Main {
    public static void main(String[] args) {
        String[] words = {"Мама", "Мыла", "Раму"};
        int n = words.length;
        int combs = n*(n-1);
        String[] phrase = new String[combs];
        for (int i = 0; i < combs; i++) {
            if (i%2>0) {
                // Можно было написать вывод прямо здесь (и в else),
                // но решил писать во второй массив. Зато потом оттуда
                // вне цикла можно готовые слова забрать.
                phrase[i] = words[(Math.abs(i%n-n)-1)]+words[(Math.abs((i+1)%n-n)-1)]+words[(Math.abs((i+2)%n-n)-1)];
            }  else {
                phrase[i] = words[(Math.abs(i%n-n)-1)]+words[(Math.abs((i+2)%n-n)-1)]+words[(Math.abs((i+1)%n-n)-1)];
            }
            System.out.println(phrase[i]);
        }
    }
}
