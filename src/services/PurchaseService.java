package services;

import model.Product;
import model.Purchase;
import parser.PurchaseReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PurchaseService {

    public List<Purchase> purchases = new ArrayList<>();
    public Purchase purchase = new Purchase();
    public String path = ".csv/purchase.csv";


    public void purchaseCreate() {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>>> Creating Purchases!");
        System.out.print("How many purchases will be created? ");
        int answer = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= answer; i++) {

            this.purchase = new Purchase();
            System.out.print("Please, insert the purchase id: ");
            purchase.setId(sc.nextInt());
            sc.nextLine();
            System.out.print("Now, insert the product id: ");
            purchase.setProductId(sc.nextInt());
            System.out.print("Now, insert the user id: ");
            purchase.setUserId(sc.nextInt());
            System.out.print("Now, insert the product quantity: ");
            purchase.setQuantity(sc.nextInt());
            System.out.print("Now, insert the total cost: ");
            purchase.setCost(sc.nextDouble());
            sc.nextLine();
            System.out.print("Created on: ");
            purchase.setCreatedOn(sc.nextLine());
            purchases.add(purchase);


            System.out.println("----------------------------\n");

        }
        PurchaseReader pr = new PurchaseReader();
        pr.dataWriter(purchases);
    }

    public static Purchase stringToPurchase(String purchase) {
        String splitter = ",";
        String[] purchaseString = purchase.split(splitter);
        Integer id = Integer.valueOf(purchaseString[0]);
        Integer productId = Integer.valueOf(purchaseString[1]);
        Integer userId = Integer.valueOf(purchaseString[2]);
        Integer quantity = Integer.valueOf(purchaseString[3]);
        Double cost = Double.valueOf(purchaseString[4]);
        Purchase purchase1 = new Purchase();
        purchase1.setId(id);
        purchase1.setProductId(productId);
        purchase1.setUserId(userId);
        purchase1.setQuantity(quantity);
        purchase1.setCost(cost);
        purchase1.setCreatedOn(purchaseString[5]);

        return purchase1;
    }

    public String purchaseLine(Purchase purchase) {
        return purchase.getId() + ","
                + purchase.getProductId() + ","
                + purchase.getUserId() + ","
                + purchase.getQuantity() + ","
                + purchase.getCost() + ","
                + purchase.getCreatedOn();
    }

    public void purchaseCSV(BufferedWriter bw, Purchase purchase) throws IOException {
        var csvInLine = purchaseLine(purchase);
        bw.write(csvInLine);
        bw.newLine();
    }

    public void dataDeleter(Integer idToDelete) throws IOException {
        List<Purchase> purchases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var purchase = stringToPurchase(line);
                if (!Objects.equals(purchase.getId(), idToDelete)) {
                    purchases.add(purchase);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (var purchase : purchases) {
                purchaseCSV(bw, purchase);
            }
        }
    }

    public void purchaseUpdate() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the purchase ID that will be updated: ");
        int idUpdate = sc.nextInt();
        sc.nextLine();
        List<Purchase> purchases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var purchase = stringToPurchase(line);
                if (purchase.getId() == idUpdate) {
                    System.out.println("Insert the new date of creation: ");
                    purchase.setCreatedOn(sc.nextLine());
                    purchases.add(purchase);
                }
                else {
                    purchases.add(purchase);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (var purchase : purchases) {
                purchaseCSV(bw, purchase);
            }
        }
        System.out.println("The purchase, ID " + idUpdate + ", was updated!");
    }



    public void readAll() throws IOException {
        PurchaseReader pr = new PurchaseReader();
        pr.dataReader();
    }

    public void readById () throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the purchase ID that will be printed: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var purchase = stringToPurchase(line);
                if (Objects.equals(purchase.getId(), id)) {
                    System.out.println("Purchase id number " + purchase.getId() +  ", correspond to: " + line.toString());

                }
            }
        }
    }

}
