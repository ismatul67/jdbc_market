package org.example;

import org.example.Entity.Unit;
import org.example.Impl.ItemServiceImpl;
import org.example.Impl.StockServiceImpl;
import org.example.Impl.UnitServiceImpl;
import org.example.service.ItemService;
import org.example.service.StockService;
import org.example.service.UnitService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App
{
    static Scanner scanner = new Scanner(System.in);
    static UnitService unitService = new UnitServiceImpl();
    static ItemService itemService = new ItemServiceImpl();
    static StockService stockService = new StockServiceImpl();

    public static void main( String[] args ) {
        int choose=0;
        System.out.println("========Welcome To Waikiki Shop======");
        while (choose<4){
            menu();
            try {
                choose= scanner.nextInt();
                scanner.nextLine();

                if (choose==1){
                    MenuUnit.printMenu();
                }else if (choose==2){
                    MenuItem.printMenu();
                }else if (choose==3){
                    MenuStock.printMenu();
                }else if (choose==4){
                    System.out.println("You are exit!");
                }else{
                    System.out.println("Menu didn't exist!");
                }
            }catch (InputMismatchException e){
                System.out.println("Number only!");
            }catch (Exception e){

            }


        }

    }
    public static void menu(){
        System.out.println("======================================");
        System.out.println("1. See unit category menu");
        System.out.println("2. See product item menu");
        System.out.println("3. see stock item menu");
        System.out.println("4. Exit");
        System.out.print("choose: ");
    }

}
