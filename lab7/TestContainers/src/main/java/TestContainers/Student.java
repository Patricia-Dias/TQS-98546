package TestContainers;

import java.sql.Date;

public class Student {
    private String name;
    private Date birthDate;
    private int nMec;

    public Student(String name, Date birthDate, int nMec){
        this.name= name;
        this.birthDate = birthDate;
        this.nMec = nMec;
    }
}
