package sample.models;

import sample.exceptions.IncorrectAge;
import sample.exceptions.IncorrectName;
import sample.exceptions.IncorrectSalary;
import sample.exceptions.IncorrectSurname;

/**
 * TheSusanin
 * 3/16/2021
 */
public class Employee {
    private String surname;
    private String name;
    private String middlename;
    private int age;
    private double salary;
    private String position;

    public Employee(String surname, String name, String middlename, int age, double salary, String position) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    public Employee(String surname, String name, int age, double salary) { //}  throws IncorrectSurname, IncorrectName, IncorrectAge, IncorrectSalary {
        setSurname(surname);
        setName(name);
        setAge(age);
        setSalary(salary);

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) { //}  throws IncorrectSurname {
        if (surname.isEmpty())
            throw new IncorrectSurname("Неверное значение для поле фамилия!");
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { //}  throws IncorrectName {
        if (name.isEmpty())
            throw new IncorrectName("Неверное значение для поле имя!");
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) { //}  throws IncorrectAge {
        if (age < 18 )
            throw new IncorrectAge("Возраст должен быть не менее 18!");
        else if (age > 65)
            throw new IncorrectAge("Возраст должен быть не больше 65!");
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) { // throws IncorrectSalary {
        if (salary < 0)
            throw new IncorrectSalary("Зарплата сотрудника не должен быть отрицательным числом");
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
