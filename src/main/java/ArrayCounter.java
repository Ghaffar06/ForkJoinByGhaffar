import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ArrayCounter extends RecursiveAction {
    public long cnt;
    int[] arr;
    int lo;
    int hi;
    int key ;

    public ArrayCounter(int[] arr,
                        int lo,
                        int hi,
                        int key) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.key = key ;

    }

    public long computeSeq() {
//        if(lo == hi)
//            return arr[lo];
//        else {
//            int mid = (lo + hi) / 2;
//            ArraySum left = new ArraySum(arr, lo, mid);
//            ArraySum right = new ArraySum(arr, mid + 1, hi);
//
//            return left.computeSeq()+ right.computeSeq();
//        }
        for (int i = lo; i <= hi; ++i) {
            cnt += arr[i];
        }
        return cnt;
    }

    @Override
    protected void compute() {
        if (hi - lo > 1_000_000) {
            int mid = (lo + hi) / 2;
            ArrayCounter left = new ArrayCounter(arr, lo, mid, key);
            ArrayCounter right = new ArrayCounter(arr, mid + 1, hi, key);
            left.fork();
            right.compute();
            left.join();
            cnt = left.cnt + right.cnt;
        } else {
//            cnt =computeSeq();
            for (int i = lo; i <= hi; ++i) {
                cnt += (arr[i] == key?1:0);
            }
        }
    }

    public void computeStreamSq() {
        cnt = Arrays.stream(arr).asLongStream().filter(e -> e == key).count();

    }

    public void computeStream() {
        cnt = Arrays.stream(arr).asLongStream().parallel().filter(e -> e == key).count();
//        all the intermediate and final operation run in parallel
//         Introduce to Map Reduce pattern credit to java streams
    }
}
