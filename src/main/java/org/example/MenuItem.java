package org.example;

import org.example.Entity.Item;
import org.example.Entity.Stock;
import org.example.Impl.ItemServiceImpl;
import org.example.Impl.StockServiceImpl;
import org.example.service.ItemService;
import org.example.service.StockService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuItem {
    static Scanner scanner = new Scanner(System.in);
    static ItemService itemService = new ItemServiceImpl();
    static StockService stockService = new StockServiceImpl();

    public static void printMenu() throws SQLException {
        System.out.println("=====Item Menu====");
        CommonMenu.printCommonMenu();
        System.out.println("6. Find By Name");
        System.out.println("7. Find Most Item in Stock");
        System.out.println("8. Back");
        System.out.print("Choose : ");
        int choose=0;
        choose = scanner.nextInt();
        scanner.nextLine();
        if (choose==1){
            System.out.print("Input Name = ");
            String name = scanner.nextLine();
            System.out.print("Input Price = ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input Unit Id = ");
            int unitId = scanner.nextInt();
            scanner.nextLine();
            Item item = new Item(name,price,unitId);
            Item itemCompare = itemService.findByName(name);
            boolean isExist = name.equals(itemCompare.getName());
            if (!(item.getName().isEmpty() && item.getPrice()==0 && item.getUnitId()==0)){
                if (!isExist){
                    System.out.println(itemService.add(item));
                }else{
                    System.out.println("Duplicate Data");
                }
            }else{
                System.out.println("Failed to add");
            }
        }else if (choose==2){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Item item = itemService.findById(id);
            if (item!=null){
                System.out.print("Input new price = ");
                int price = scanner.nextInt();
                scanner.nextLine();
                item.setPrice(price);
                Stock stock = stockService.findStockByItemId(id);
                if (itemService.edit(item)!=null){
                    System.out.println(itemService.edit(item));
                    stockService.edit(stock);
                }else{
                    System.out.println("Failed to update!");
                }
            }
        }else if (choose==3){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Item item = itemService.findById(id);
            if (item!=null){
                System.out.println("======================================");
                commonPrintItem(item);
            }else{
                System.out.println("Item not found!");
            }

        }else if (choose==4){
            List<Item> items=itemService.findAll();
            if (items.isEmpty()){
                System.out.println("Items is empty!");
            }else {
                System.out.println("======================================");
                for (Item item: items) {
                    commonPrintItem(item);
                    System.out.println("======================================");
                }
            }
        }else if(choose==5){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            if (itemService.removeById(id)!=null){
                System.out.println(itemService.removeById(id));
            }else{
                System.out.println("Delete Failed!");
            }
        }else if (choose==6){
            System.out.print("Input item name = ");
            String name = scanner.nextLine();
            Item item = itemService.findByName(name);
            if (item!=null){
                System.out.println("======================================");
                commonPrintItem(item);
            }else{
                System.out.println("Item not found!");
            }
        }else if(choose==7){
            List<Item> items= itemService.findMostItemInStock();
            if (items.isEmpty()){
                System.out.println("Items is empty!");
            }else {
                System.out.println("======================================");
                for (Item item: items) {
                    commonPrintItem(item);
                    System.out.println("======================================");
                }
            }
        }else if(choose==8){
            System.out.println("You are back to main menu");
        }else{
            System.out.println("Menu tidak tersedia!");
        }
    }

    public static void commonPrintItem(Item item){
        System.out.println("ID\t\t\t: " + item.getId());
        System.out.println("Name\t\t: " + item.getName());
        System.out.println("Price\t\t: " + item.getPrice());
        System.out.println("Unit ID\t\t: " + item.getUnitId());

    }
}
