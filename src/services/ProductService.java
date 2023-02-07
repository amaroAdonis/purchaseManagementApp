package services;

import model.Product;
import model.User;
import parser.ProductReader;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ProductService {

    public List<Product> products = new ArrayList<>();
    public Product product = new Product();
    public String path = "D:/adonis/estudosJavaDev/Purchaseapp2023/csv/product.csv";


    public void productInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>>> Adding Products!");
        System.out.print("How many products will be added? ");
        int answer = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= answer; i++) {

            this.product = new Product();
            System.out.print("Please, insert the product id: ");
            product.setId(sc.nextInt());
            sc.nextLine();
            System.out.print("Now, insert the product name: ");
            product.setName(sc.nextLine());
            System.out.print("Now, describe the product: ");
            product.setDescription(sc.nextLine());
            System.out.print("Now, insert the product price: ");
            product.setPrice(sc.nextDouble());
            System.out.print("How many products will be inserted? ");
            product.setQuantity(sc.nextInt());
            sc.nextLine();
            System.out.print("Created on: ");
            product.setCreatedOn(sc.nextLine());
            product.setUpdatedOn(product.getCreatedOn());
            products.add(product);

            System.out.println("----------------------------\n");

        }
        ProductReader pr = new ProductReader();
        pr.dataWriter(products);
    }

    public static Product stringToProduct(String product) {
        String splitter = ",";
        String[] productString = product.split(splitter);
        Integer id = Integer.valueOf(productString[0]);
        Integer quantity = Integer.valueOf(productString[4]);
        Double price = Double.valueOf(productString[3]);
        Product product1 = new Product();
        product1.setId(id);
        product1.setName(productString[1]);
        product1.setDescription(productString[2]);
        product1.setPrice(price);
        product1.setQuantity(quantity);
        product1.setCreatedOn(productString[5]);
        product1.setUpdatedOn(productString[6]);

        return product1;
    }

    public String productLine(Product product) {
        return product.getId() + ","
                + product.getName().toUpperCase() + ","
                + product.getDescription().toUpperCase() + ","
                + product.getPrice() + ","
                + product.getQuantity() + ","
                + product.getCreatedOn() + ","
                + product.getUpdatedOn();
    }

    public void productCSV(BufferedWriter bw, Product product) throws IOException {
        var csvInLine = productLine(product);
        bw.write(csvInLine);
        bw.newLine();
    }

    public void dataDeleter(Integer idToDelete) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var product = stringToProduct(line);
                if (!Objects.equals(product.getId(), idToDelete)) {
                    products.add(product);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (var product : products) {
                productCSV(bw, product);
            }
        }
    }

    public void productUpdate() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the product ID that will be updated: ");
        int idUpdate = sc.nextInt();
        sc.nextLine();
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var product = stringToProduct(line);
                if (product.getId() == idUpdate) {
                    System.out.println("Insert the new date of update: ");
                    product.setUpdatedOn(sc.nextLine());
                    products.add(product);
                }
                else {
                    products.add(product);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (var product : products) {
                productCSV(bw, product);
            }
        }
        System.out.println("The product ID " + idUpdate + ", was updated!");
    }



    public void readAll() throws IOException {
        ProductReader pr = new ProductReader();
        pr.dataReader();
    }

    public void readById () throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert the product ID that will be printed: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var product = stringToProduct(line);
                if (Objects.equals(product.getId(), id)) {
                    System.out.println("Product id number " + product.getId() +", correspond to: " + line.toString());

                }
            }
        }
    }


}
