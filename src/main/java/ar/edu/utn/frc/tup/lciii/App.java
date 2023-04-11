package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.model.Bill;
import ar.edu.utn.frc.tup.lciii.model.Customer;
import ar.edu.utn.frc.tup.lciii.model.Item;
import ar.edu.utn.frc.tup.lciii.services.BillService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Hello Stack!
 *
 */
public class App 
{

    /**
     * This is the main program
     * 
     */
    public static void main( String[] args ) {

        BillService billService = new BillService();

        Scanner scanner = new Scanner(System.in);
        String option = "";
        List<Item> itemList = new ArrayList<>();
        Bill bill = new Bill();
        System.out.println("Bienvenido al facturador!");
        do {
            System.out.println("Elija una opción:");
            System.out.println("\tA - Ingresar un item");
            System.out.println("\tB - Facturar ítems");
            option = scanner.nextLine();
            if(option.equals("A")) {
                Item item = new Item();
                System.out.println("Ingrese el nombre de un item");
                item.setName(scanner.nextLine());
                System.out.println("Ingrese la cantidad");
                item.setQuantity(scanner.nextInt());
                System.out.println("Ingrese el precio del item");
                item.setUnitPrice(scanner.nextBigDecimal());
                itemList.add(item);
                scanner.nextLine();
            }
            if(option.equals("B")) {
                bill = billService.billing(itemList);
            }
        } while (option.equals("A"));
        System.out.println("Gracias por usar el facturador!");
        System.out.println("Aquí los datos de su factura");
        System.out.println(bill.toString());
    }
}
