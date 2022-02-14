package com.amirkenesbay.parser.test;

import com.amirkenesbay.parser.model.Address;
import com.amirkenesbay.parser.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EmployeeGsonExample {
    public static void main(String[] args) throws IOException {
        Employee emp = createEmployee();

        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // read JSON file data as String
        String fileData = new String(Files.readAllBytes(Paths
                .get("test3.json")));

        // parse json string to object
        Employee emp1 = gson.fromJson(fileData, Employee.class);

        // print object data
        System.out.println("\n\nEmployee Object\n\n" + emp1);

        // create JSON String from Object
        String jsonEmp = gson.toJson(emp);
        System.out.print(jsonEmp);
    }

    private static Employee createEmployee() {
        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[]{123456, 987654});
        emp.setRole("Manager");

        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);

        List cities = new ArrayList();
        cities.add("Los Angeles");
        cities.add("New York");
        emp.setCities(cities);

        Map props = new HashMap();
        props.put("salary", "1000 Rs");
        props.put("age", "28 years");
        emp.setProperties(props);

        return emp;
    }
}
