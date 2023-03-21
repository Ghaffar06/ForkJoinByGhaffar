import java.util.HashMap;
import java.util.concurrent.RecursiveTask;


/*
However, besides being a dumb way to compute Fibonacci functions (there is a simple fast linear algorithm that you'd use in practice),
 this is likely to perform poorly because the smallest subtasks are too small to be worthwhile splitting up.
 Instead, as is the case for nearly all fork/join applications,
 you'd pick some minimum granularity size (for example 10 here) for which you always sequentially solve rather than subdividing.
 */

public class Fibonacci extends RecursiveTask<Integer> {
    final int n;
    final boolean hash;
    static HashMap<Integer, Integer> Memo = new HashMap<Integer, Integer>();
    public Fibonacci(int n, boolean hash) { this.n = n; this.hash = hash; }

    public Integer compute() {
        if(hash) {
            if (Memo.get(n) != null)
                return Memo.get(n);
        }
        if (n > 20) {
            Fibonacci f1 = new Fibonacci(n - 1, hash);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2, hash);
            int n2 = f2.compute();
            int n1 = f1.join();
            Memo.put(n, n1 + n2);
            return n1 + n2;
        } else {
            return computeSeq();
        }


    }


    public Integer computeSeq() {
        if(Memo.get(n) != null && hash)
            return Memo.get(n) ;
        if (n <= 1)
        {
            int memo = n ;
            Memo.put(n , memo) ;
            return memo;
        }

        Fibonacci f1 = new Fibonacci(n - 1, hash);
        Fibonacci f2 = new Fibonacci(n - 2, hash);
        int n1 = f1.computeSeq();
        int n2 = f2.computeSeq();
        Memo.put(n, n1 + n2);
        return n1 + n2;

    }

}
