import junit.framework.TestCase;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ArrayCounterTest extends TestCase {


    private int[] randomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size);
        }
        /* Check  random array
        Arrays.stream(arr).forEach(System.out::println);*/
        return arr;
    }

    private int[] intStream(int size) {
        IntStream intStream = IntStream.range(1, size);
        return intStream.toArray();
    }

    public void testArraySumSeq() {

        int size = 1000_000;
        int[] arr = intStream(size);

        ArrayCounter array = new ArrayCounter(arr, 0, arr.length - 1, 2);
        long start = System.currentTimeMillis();
        long sum = array.computeSeq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d sum is %d\n", size, endTimer, sum);
//        assertEquals(15,res);
    }

    public void testArraySumPP() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 1000_000;
        int[] arr = intStream(size);

        ArrayCounter array = new ArrayCounter(arr, 0, arr.length - 1, 2);
        long start = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(array);
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Time execution for Random Array of size %d is %d ms sum is %d\n", size, endTimer, array.cnt);
//        assertEquals(15,res);
    }

    public void testArraySumStreamSq() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 1000_000;
        int[] arr = intStream(size);

        ArrayCounter array = new ArrayCounter(arr, 0, arr.length - 1, 2);
        long start = System.currentTimeMillis();
        array.computeStreamSq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Stream Time execution for Random Array of size %d is %d ms sum is %d\n", size, endTimer, array.cnt);
//        assertEquals(15,res);
    }

    public void testArraySumStream() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 1000_000;
        int[] arr = intStream(size);

        ArrayCounter array = new ArrayCounter(arr, 0, arr.length - 1, 2);
        long start = System.currentTimeMillis();
        array.computeStream();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Stream Time execution for Random Array of size %d is %d ms sum is %d\n", size, endTimer, array.cnt);
//        assertEquals(15,res);
    }

    public void resource() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.commonPool().getParallelism());
    }

}
