package testdb;

import entities.GroupName;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
* create entity "group"
* 
*/
public class App {
    private List<Student> students;
    private List<GroupName> groupName;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestDBPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public App() {
        try {
            students = (List<Student>)(Student)em.createQuery("SELECT s FROM Student s")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("There is no such record in db");
            students = new ArrayList<>();
            groupName = new ArrayList<>();
            
        }
    }
    
    public void run() {
        tx.begin();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
        
            if (student.getId() == null) {
                student.setName("Ivan");
                student.setLastname("Ivanov");
                student.setDay(19);
                student.setMonth(1);
                student.setYear(1982);
                student.setGroupName(new GroupName());
                student.getGroupName().setGname("JKTV21");
                student.getGroupName().setYear(2021);
                em.persist(student);

                GroupName groupName = student.getGroupName();
                student = new Student();
                student.setName("Peter");
                student.setLastname("Petrov");
                student.setDay(19);
                student.setMonth(1);
                student.setYear(1982);
                student.setGroupName(groupName);
//                student.getGroupName().setGname("JKTV22");
//                student.getGroupName().setYear(2022);
                em.persist(student);

            }else{
                student.setName("Zahhar");
                student.getGroupName().setGname("JKTV22");
                student.getGroupName().setYear(2022);
                em.merge(student);
            }
        }
        tx.commit();
        
    }
    // test data
}
