
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private int day;
    private int month;
    private int year;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private GroupName groupName;

    
    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName GroupName) {
        this.groupName = GroupName;
    }

    @Override
    public String toString() {
        return "Student{" 
                + "id=" + id 
                + ", name=" + name 
                + ", lastname=" + lastname 
                + ", day=" + day + ", month=" + month 
                + ", year=" + year 
                + ", groupName=" + groupName.getGname() 
                + '}';
    }
    

   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}   // ends public class Student
