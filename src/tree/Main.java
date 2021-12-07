package tree;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 2000000;
        Random random = new Random();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            data.add(random.nextInt(Integer.MAX_VALUE));
        }
        long startTime = System.nanoTime();
        RBTree<Integer, String> rbt = new RBTree<>();
        for (Integer x : data) {
            rbt.put(x, null);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println(time);
    }

}
