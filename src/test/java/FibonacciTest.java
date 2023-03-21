import junit.framework.TestCase;


public class FibonacciTest extends TestCase {

    int n=40;

    public void testFiboPP(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n, false);
        int res = fib.compute();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and parallel execution took %d ms\n",n,res,end);
//        Fibonacci parallel takes 5308 fon n = 40
    }

    public void testFiboPPHash(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n, true);
        long res = fib.compute();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and parallel execution with hash map took %d ms\n",n,res,end);
//        Fibonacci parallel takes 4 fon n = 40
    }

    public void testFiboSeq(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n, false);
        int res = fib.computeSeq();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and sequential execution took %d ms\n",n,res,end);
        //Fibonacci Sequential takes 4275 fon n = 40
    }

    public void testFiboSeqHash(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n, true);
        long res = fib.computeSeq();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and sequential Hash map execution took %d ms\n",n,res,end);
        //Fibonacci Sequential takes 1 fon n = 40
    }
}
