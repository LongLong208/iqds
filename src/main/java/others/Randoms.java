package others;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randoms {
    public static int[] getRandoms(int max) {
        Random rd = new Random();
        int[] randoms = new int[max];
        List<Integer> lst = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            lst.add(i);
        }
        int index = 0;
        for (int i = 0; i < max; i++) {
            index = rd.nextInt(max - i);
            randoms[i] = lst.get(index);
            lst.remove(index);
        }
        return randoms;
    }

    public static <T> List<T> randoms(List<T> list) {
        List<T> listRes = new ArrayList<>();
        int[] rds = getRandoms(list.size());
        for (int rd : rds) {
            listRes.add(list.get(rd));
        }
        return listRes;
    }

}
