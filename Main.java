import java.util.Random;
public class Main {
    public static void main(String[] args) {
        int seed = 5;
        Random r = new Random(seed);

        int[] size = {10000, 50000, 100000, 250000, 500000};
        int[] elementsNum = {20000, 100000, 500000, 1000000, 2500000};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                long begin = System.nanoTime();
                // insert(size[i], elementsNum[j], r); // Tempo de inserção e número de colisões
                long end = System.nanoTime();

                //System.out.println(insert(size[i], elementsNum[j], r)); // Mostrar o número de colisões

                // System.out.println(end - begin); // Mostrar o tempo de inserção

                //System.out.println(search(size[i], elementsNum[j], r)); // Mostrar o nºde comparações na busca
            }
        }
    }


    public static int generateRandom(Random random) {
        int min = 100000000;
        int max = 999999999;
        return random.nextInt((max - min) + 1) + min;
    }

    public static int insert(int size, int elementsNum, Random r){
        HashTable T = new HashTable(size);
        for (int i = 0; i < elementsNum; i++) {
            T.insert(generateRandom(r), 1);
        }
        return T.getCollision();
    }

    public static int search(int size, int elementsNum, Random r){
        HashTable T = new HashTable(size);
        int[] numToSearch = new int[elementsNum];
        int comparisons;

        for (int i = 0; i < elementsNum; i++) {
            int n = generateRandom(r);
            T.insert(n, 3);
            numToSearch[i] = n;
        }

        long begin = System.nanoTime();
        comparisons = T.search(3, numToSearch[r.nextInt(elementsNum)]);
        long end = System.nanoTime();

        //System.out.println(end - begin); Mostrar o tempo de busca.
        return comparisons;
    }
}
