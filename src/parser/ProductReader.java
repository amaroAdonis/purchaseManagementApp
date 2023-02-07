package parser;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductReader implements Reader{
    public String path = ".csv/product.csv";
    Scanner sc = new Scanner(System.in);


    @Override
    public void dataReader() throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<String> lines = br.lines().toList();
            for (var line : lines) {
                var product = stringToProduct(line);
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(products);
    }



    @Override
    public void dataWriter(List productList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (Object product1 : productList) {
                productCSV(bw, (Product) product1);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
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


}
