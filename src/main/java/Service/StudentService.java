package Service;

import Resource.Student;

import java.util.UUID;

public class StudentService {
    public String saveStudent(Student std){
        System.out.println("Saving Student: " + std.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return UUID.randomUUID().toString();
    }
}
