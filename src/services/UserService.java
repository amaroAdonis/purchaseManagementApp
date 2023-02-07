package services;

import model.User;
import parser.UserReader;

import java.io.*;
import java.util.*;

public class UserService {
    private final List<User> userList = new ArrayList<>();
    private User user = new User();
    private final String path = ".";

    public UserService() {
    }


    public void createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>>> Creating new users!");
        System.out.print("How many users will be created? ");
        int answer = sc.nextInt();
        sc.nextLine();
        for (int i=1; i<=answer; i++){
            this.user = new User();
            System.out.print("Insert the user Id: ");
            user.setId(sc.nextInt());
            sc.nextLine();
            System.out.print("Now, insert the user first name: ");
            user.setFirstName(sc.nextLine());
            System.out.print("Now, the user last name: ");
            user.setLastName(sc.nextLine());
            System.out.print("Created on: ");
            user.setCreatedOn(sc.nextLine());
            userList.add(user);


            System.out.println("----------------------------\n");
        }
        UserReader ur = new UserReader();
        ur.dataWriter(userList);
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

    public void dataDeleter(Integer idToDelete) throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList(); //LER DOCUMENTAÇÃO DIREITO
            for (var line : lines) {
                var user = stringToUser(line);
                if (!Objects.equals(user.getId(), idToDelete)) {
                    users.add(user);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (var user : users) {
                csvUser(bw, user);
            }
        }
    }


    public List<User> getUserList() {
        return userList;
    }


}

