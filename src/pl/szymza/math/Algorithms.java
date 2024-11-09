package pl.szymza.math;
import java.util.List;
import java.util.Collections;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public abstract class Algorithms {
    public static <T>
    void insertionSort(List<T> arr, BiPredicate<T, T> cmp) {
        for (int i=1; i < arr.size(); i++)
            for (int j=i; j-1 >= 0 && cmp.test(arr.get(j), arr.get(j-1)); j--)
                Collections.swap(arr, j, j-1);
    }
    public static int random(int min, int max) {
        var rand = new java.util.Random();
        return rand.nextInt(max-min + 1) + min;
    }

    public static boolean chance(double probability) {
        return random(0, 99) < 100*probability;
    }

    public static <T> T pickRandom(List<T> container) {
        return container.get(random(0, container.size()-1));
    }

    public static List<Integer> range(int lo, int hi) {
        return IntStream.range(lo, hi+1).boxed().toList();
    }
}
