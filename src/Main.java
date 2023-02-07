
import parser.PurchaseReader;
import services.ProductService;
import services.PurchaseService;
import services.UserService;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException {



        Scanner sc = new Scanner(System.in);
        System.out.println("<< Store Management System >>");
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println("For USERS, enter 'a';");
        System.out.println("For PRODUCTS, enter 'b';");
        System.out.println("For PURCHASES, enter 'c';");

        System.out.println("Enter your choice: ");
        String answer = sc.nextLine();

        switch (answer) {
            case "a" -> userActions();
            case "b" -> productsAction();
            case "c" -> purchasesAction();

        }

        System.out.println(">>> Terminal closed! <<<");

    }

    public static void userActions() throws IOException {
        UserService us = new UserService();
        Scanner sc = new Scanner(System.in);
        System.out.println(">>> What do you want to do? <<<");
        System.out.println("""
                a. Create users?        Press 'a'.\s
                b. Delete users?        Press 'b'.\s
                c. Read all users?      Press 'c'.\s
                d. Read an user by id?  Press 'd'.""");

        String answer = sc.nextLine();
        switch (answer) {
            case "a" -> {us.createUser();}
            case "b" -> {
                System.out.print("Please, insert the user id to delete: ");
                Integer idToDelete = sc.nextInt();
                sc.nextLine();
                us.dataDeleter(idToDelete);
            }
            case "c" -> {us.readAll();}
            case "d" -> {us.readById();}
        }

    }

    public static void productsAction() throws IOException {
        ProductService prs = new ProductService();
        Scanner sc = new Scanner(System.in);
        System.out.println(">>> What do you want to do? <<<");
        System.out.println("""
                a. Add products?            Press 'a'.\s
                b. Delete Products?         Press 'b'.\s
                c. Update Products?         Press 'c'.\s
                d. Read all saved products? Press 'd'.\s
                e. Read a product by id?    Press 'e'.""");
        String answer = sc.nextLine();
        switch (answer){
            case "a" -> {prs.productInsert();}
            case "b" -> {
                System.out.print("Please, insert the product id to delete: ");
                Integer idToDelete = sc.nextInt();
                sc.nextLine();
                prs.dataDeleter(idToDelete);
            }
            case "c" -> {prs.productUpdate();}
            case "d" -> {prs.readAll();}
            case "e" -> {prs.readById();}
        }



    }

    public static void purchasesAction() throws IOException {
        PurchaseService purs = new PurchaseService();
        Scanner sc = new Scanner(System.in);
        System.out.println(">>> What do you want to do? <<<");
        System.out.println("""
                a. Create purchases?         Press 'a'.\s
                b. Delete purchases?         Press 'b'.\s
                c. Update purchases?         Press 'c'.\s
                d. Read all saved products?  Press 'd'.\s
                e. Read a product by id?     Press 'e'.""");
        String answer = sc.nextLine();
        switch (answer) {
            case "a" -> {
                purs.purchaseCreate();
            }
            case "b" -> {
                System.out.print("Please, insert the purchase id to delete: ");
                Integer idToDelete = sc.nextInt();
                sc.nextLine();
                purs.dataDeleter(idToDelete);
            }
            case "c" -> {purs.purchaseUpdate();}
            case "d" -> {purs.readAll();}
            case "e" -> {purs.readById();}
        }
    }

}