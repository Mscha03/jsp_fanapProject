package org.example.demo4.DataBase;

import org.example.demo4.Person.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PersonController {
    private static DataBaseConnector dataBaseConnector;
    private static final List<String> columns = Arrays.asList("USERNAME", "PASSWORD", "PERSON_NAME");

    public static void createPersonInDB(Person person){
       dataBaseConnector = new DataBaseConnector();
        List<String> sql = new ArrayList<>();
        sql.add("'" + person.getUserName() + "'");
        sql.add("'" + person.getPassword() + "'");
        sql.add("'" + person.getPersonName() + "'");

        dataBaseConnector.insert("PERSON", columns, sql,false);
    }

    public static Person getPersonDB(String username, String password) throws SQLException {
        String DBUsername = null, DBPassword = null, DBPersonName = null;
        dataBaseConnector = new DataBaseConnector();
        ResultSet resultSet =
                dataBaseConnector.find("PERSON", "USERNAME", username );
        if (resultSet.next()){
            DBUsername = resultSet.getString("USERNAME");
            DBPassword = resultSet.getString("PASSWORD");
            DBPersonName = resultSet.getString("PERSON_NAME");
        }

        if (Objects.equals(username, DBUsername) && Objects.equals(password, DBPassword)){
            return new Person(DBPersonName, DBUsername, DBPassword);
        }

        return null;
    }
}
