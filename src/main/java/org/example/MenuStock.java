package org.example;

import org.example.Entity.Stock;
import org.example.Impl.StockServiceImpl;
import org.example.service.StockService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuStock {
    static Scanner scanner = new Scanner(System.in);
    static StockService stockService = new StockServiceImpl();

    public static void printMenu() throws SQLException {
        System.out.println("=====Stock Menu====");
        CommonMenu.printCommonMenu();
        System.out.println("6. Find greater than quantity");
        System.out.println("7. Find less than quantity");
        System.out.print("Choose : ");
        int choose = scanner.nextInt();
        scanner.nextLine();
        if (choose==1){
            System.out.print("Input Item ID = ");
            int itemId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input Quantity = ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Stock stock = new Stock(itemId,quantity);
            if (stockService.add(stock)!=null){
                System.out.println(stockService.add(stock));
            }else{
                System.out.println("failed to add!");
            }
        }else if (choose==2){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Stock stock = stockService.findById(id);
            if (stock!=null){
                System.out.print("Input new quantity = ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                stock.setQuantity(quantity);
                if (stockService.edit(stock)!=null){
                    System.out.println(stockService.edit(stock));
                }else{
                    System.out.println("Failed to update!");
                }
            }
        }else if (choose==3){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Stock stock = stockService.findById(id);
            if (stock!=null){
                System.out.println("======================================");
                commonPrintStock(stock);
            }else{
                System.out.println("Stock not found!");
            }

        }else if (choose==4){
            List<Stock> stocks =stockService.findAll();
            if (stocks.isEmpty()){
                System.out.println("Items is empty!");
            }else {
                System.out.println("======================================");
                for (Stock stock: stocks) {
                    commonPrintStock(stock);
                    System.out.println("======================================");
                }
            }
        }else if(choose==5){
            System.out.print("Input ID to remove= ");
            int id = scanner.nextInt();
            scanner.nextLine();
            if (stockService.removeById(id)!=null){
                System.out.println(stockService.removeById(id));
            }else{
                System.out.println("Delete Failed!");
            }
        }else if (choose==6){
            System.out.print("Input Number to compare= ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            List<Stock> stocks =stockService.findGreaterThanQuantity(quantity);
            if (stocks.isEmpty()){
                System.out.println("Items is empty!");
            }else {
                System.out.println("======================================");
                for (Stock stock: stocks) {
                    commonPrintStock(stock);
                    System.out.println("======================================");
                }
            }
        }else if(choose==7){
            System.out.print("Input Number to compare= ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            List<Stock> stocks =stockService.findLessThanQuantity(quantity);
            if (stocks.isEmpty()){
                System.out.println("Items is empty!");
            }else {
                System.out.println("======================================");
                for (Stock stock: stocks) {
                    commonPrintStock(stock);
                    System.out.println("======================================");
                }
            }
        }else{
            System.out.println("Menu tidak tersedia!");
        }
    }

    public static void commonPrintStock(Stock stock){
        System.out.println("ID\t\t\t: " + stock.getId());
        System.out.println("Item ID\t\t: " + stock.getItemId());
        System.out.println("Quantity\t: " + stock.getQuantity());
        System.out.println("Total Price\t: " + stock.getTotalPrice());

    }
}
