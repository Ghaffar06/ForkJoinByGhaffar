import Resource.Student;
import Service.StudentService;
import junit.framework.TestCase;

public class ThreadTest extends TestCase {

    public void testStudent() {
        long start = System.currentTimeMillis();

        StudentService studentService = new StudentService();

        for (int i = 1; i <= 10; ++i) {
            Student std = new Student("student " + i);
            studentService.saveStudent(std);
        }
        long execTime = System.currentTimeMillis() - start;
        System.out.println("Execution Time: " + execTime + " ms");

    }
}
