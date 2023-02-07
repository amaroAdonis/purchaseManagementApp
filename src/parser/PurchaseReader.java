package parser;

import model.Product;
import model.Purchase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseReader implements Reader{
    public String path = ".csv/purchase.csv\"";
    Scanner sc = new Scanner(System.in);

    @Override
    public void dataReader() throws IOException {
            List<Purchase> purchases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var purchase = stringToPurchase(line);
                purchases.add(purchase);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(purchases);
    }

    @Override
    public void dataWriter(List purchaseList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (Object purchase1 : purchaseList) {
                purchaseCSV(bw, (Purchase) purchase1);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
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


}
