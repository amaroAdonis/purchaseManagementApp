package parser;

import model.Purchase;
import model.User;
import services.UserService;

import java.io.*;
import java.util.*;

public class UserReader implements Reader<User> {

    private final String path = "D:/adonis/estudosJavaDev/Purchaseapp2023/csv/user.csv";
    Scanner sc = new Scanner(System.in);


    @Override
    public void dataWriter(List<User> userList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (var user1 : userList) {
                csvUser(bw, user1);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public String userInLine(User user) {
        return user.getId() + ","
                + user.getFirstName().toUpperCase() + ","
                + user.getLastName().toUpperCase() + ","
                + user.getCreatedOn();
    }


    public void csvUser(BufferedWriter bw, User user) throws IOException {
        var csvInLine = userInLine(user);
        bw.write(csvInLine);
        bw.newLine();
    }

    @Override
    public void dataReader() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList(); //LER DOCUMENTAÇÃO DIREITO
            for (var line : lines) {
                var user = stringToUser(line);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(users);
    }


    public static User stringToUser(String user) {
        String splitter = ",";
        String[] userString = user.split(splitter);
        Integer id = Integer.valueOf(userString[0]);
        User user1 = new User();
        user1.setId(id);
        user1.setFirstName(userString[1]);
        user1.setLastName(userString[2]);
        user1.setCreatedOn(userString[3]);
        return user1;
    }




}



