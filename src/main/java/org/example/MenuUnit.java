package org.example;

import org.example.Entity.Unit;
import org.example.Impl.UnitServiceImpl;
import org.example.service.UnitService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuUnit {
    static Scanner scanner = new Scanner(System.in);
    static UnitService unitService = new UnitServiceImpl();

    public static void printMenu() throws SQLException {
        System.out.println("=====Unit Menu====");
        CommonMenu.printCommonMenu();
        System.out.println("6. Find By Code");
        System.out.print("Choose : ");
        int choose = scanner.nextInt();
        scanner.nextLine();
        if (choose==1){
            System.out.print("Input Description = ");
            String desc = scanner.nextLine();
            System.out.print("Input Code = ");
            String code = scanner.nextLine();
            Unit unit = new Unit(code,desc);
            Unit compareUnit= unitService.findByCode(code);
            if (!(code.equals(compareUnit.getCode()) || desc.equals(compareUnit.getDescription()))){
                System.out.println(unitService.add(unit));
            }else {
                System.out.println("Duplicate Data!");
            }
        }else if (choose==2){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Unit unit = unitService.findById(id);
            if (unit!=null){
                System.out.print("Input Description = ");
                String desc = scanner.nextLine();
                System.out.print("Input Code = ");
                String code = scanner.nextLine();
                unit.setDescription(desc);
                unit.setCode(code);
                if (unitService.edit(unit)!=null){
                    System.out.println( unitService.edit(unit));
                }else{
                    System.out.println("Failed to update!");
                }
            }
        }else if (choose==3){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Unit unit = unitService.findById(id);
            if (unit!=null){
                System.out.println("======================================");
                commonPrintUnit(unit);
            }else{
                System.out.println("Unit not found!");
            }

        }else if (choose==4){
            List<Unit> units=unitService.findAll();
            if (units.isEmpty()){
                System.out.println("Units is empty!");
            }else {
                System.out.println("======================================");
                for (Unit unit: units) {
                    commonPrintUnit(unit);
                    System.out.println("======================================");
                }
            }
        }else if(choose==5){
            System.out.print("Input ID = ");
            int id = scanner.nextInt();
            scanner.nextLine();
            if (unitService.removeById(id)!=null){
                System.out.println(unitService.removeById(id));
            }else{
                System.out.println("Delete Failed!");
            }
        }else if (choose==6){
            System.out.print("Input Code = ");
            String code = scanner.nextLine();
            Unit unit = unitService.findByCode(code);
            if (unit!=null){
                System.out.println("======================================");
                commonPrintUnit(unit);
            }else{
                System.out.println("Unit not found!");
            }
        }else{
            System.out.println("Menu tidak tersedia!");
        }
    }

    public static void commonPrintUnit(Unit unit){
        System.out.println("ID\t\t\t: " + unit.getId());
        System.out.println("Code\t\t: " + unit.getCode());
        System.out.println("Description\t: " + unit.getDescription());
    }
}
