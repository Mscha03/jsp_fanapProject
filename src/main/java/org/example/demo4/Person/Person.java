package org.example.demo4.Person;

public class Person {
    private String personName;
    private String userName;
    private String password;

    public Person(String personName, String userName, String password) {
        this.personName = personName;
        this.userName = userName;
        this.password = password;
    }

    public String getPersonName() {
        return personName;
    }


    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
