package TestContainers.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "nmec")
    private int nmec;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "classletter")
    private char classLeter;

    public Student(String name, Date date, char classLeter){
        this.name = name;
        this.birthDate = date;
        this.classLeter=classLeter;
    }

    public Student(){}

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setBirthDate(Date birthdate){
        this.birthDate=birthdate;
    }

    public Date getBirthDate(){
        return this.birthDate;
    }

    public Integer getNMec(){
        return this.nmec;
    }

    public Character getClassLetter(){
        return this.classLeter;
    }

    @Override
    public String toString(){
        return "Student ("+nmec+","+name+","+birthDate+","+classLeter+")";
    }
}
