package testdb;

import entities.GroupName;
import entities.GroupStudents;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {
    private List<Student> students;
    private List<GroupName> groupNames;
    private List<GroupStudents> listGroupStudents;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestDBPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public App() {
        try {
            students = em.createQuery("SELECT s FROM Student s").getResultList();
            groupNames = em.createQuery("SELECT gn FROM GroupName gn").getResultList();
            listGroupStudents = em.createQuery("SELECT gs FROM GroupStudents gs").getResultList();
        } catch (Exception e) {
            System.out.println("There is no such record in db");
            students = new ArrayList<>();
            groupNames = new ArrayList<>();
            listGroupStudents = new ArrayList<>();

            
        }
    }
    
    public void run() {
        tx.begin();
        
        if (students.isEmpty()) {
            GroupName groupName = new GroupName();
            groupName.setGname("JKTV21");
            groupName.setYear(2021);
            em.persist(groupName);
            
            Student student = new Student();
            student.setName("Tolik");
            student.setLastname("Pruzhinkin");
            student.setDay(19);
            student.setMonth(1);
            student.setYear(1982);
            em.persist(student);
            
            GroupStudents groupStudents = new GroupStudents();
            groupStudents.setStudent(student);
            groupStudents.setGroup(groupName);
            em.persist(groupStudents);
                        //------------------------------------------------
            
            student = new Student();
            student.setName("Ivan");
            student.setLastname("Ivanov");
            student.setDay(19);
            student.setMonth(1);
            student.setYear(1982);
            em.persist(student);
            
            groupName = new GroupName();
            groupName.setGname("JKTV22");
            groupName.setYear(2021);
            em.persist(groupName);
            
            groupStudents = new GroupStudents();
            groupStudents.setStudent(student);
            groupStudents.setGroup(groupName);
            em.persist(groupStudents);
        }
        
        tx.commit();
        List<GroupStudents> groupStudents = em.createQuery(
                "SELECT gs FROM GroupStudents gs"
                ).getResultList();
        for (int i = 0; i < groupStudents.size(); i++) {
            GroupStudents gs = groupStudents.get(i);
            System.out.printf("%d. %s %s, %s%n", 
                    i+1,
                    gs.getStudent().getName(),
                    gs.getStudent().getLastname(),
                    gs.getGroup().getGname()
                    );
        }
    }
    
    
    
    
}   // ends public class App
