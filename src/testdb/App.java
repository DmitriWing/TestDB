package testdb;

import entities.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class App {
    private Student student;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestDBPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public App() {
        try {
            student = (Student)em.createQuery("SELECT s FROM Student s")
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("There is no such record in db");
            student = new Student();
        }
    }
    
    public void run() {
        tx.begin();
        if (student.getId() == null) {
            student.setName("Ivan");
            student.setLastname("Ivanov");
            student.setDay(19);
            student.setMonth(1);
            student.setYear(1982);
            em.persist(student);
        }else{
            student.setName("Zahhar");
            em.merge(student);
        }
        tx.commit();
        
    }
    // test data
}
