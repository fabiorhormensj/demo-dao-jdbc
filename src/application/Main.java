package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Find Sellers by ID ===");
        System.out.print("Enter the ID of the Seller:");
        Integer id = sc.nextInt();

        Seller seller = sellerDao.findById(id);

        System.out.println(seller);
        /*

        System.out.println("\n=== TEST 2: Find Sellers by Department ID ===");
        System.out.print("Enter the ID of the Department: ");
        Department department = new Department(sc.nextInt(), null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller s : list) {
            System.out.println(s);
        }

        System.out.println("\n=== TEST 3: Find All Sellers ===");
        List<Seller> sellers = sellerDao.findAll();
        sc.next();

        for (Seller s : sellers) {
            System.out.println(s);
        }


        System.out.println("\n=== TEST 4: Insert Seller ===");
        Seller seller1 = new Seller(null, "Fábio", "fabiorhormensj@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(seller1);
        System.out.println("Inserted Seller! Id: " + seller1.getId());



        System.out.println("\n=== TEST 5: Update Seller name by ID ===");
        System.out.print("Enter the ID of the Seller: ");
        id = sc.nextInt();
        System.out.print("\nEnter the new name of the Seller: ");
        String name = sc.next();
        seller =  sellerDao.findById(id);

        seller.setName(name);
        sellerDao.update(seller);

         */
        System.out.println("\n=== TEST 6: Delete Seller by ID ===");
        System.out.print("Enter the ID of the Seller: ");
        id = sc.nextInt();
        sellerDao.deleteById(id);

        sc.close();
    }
}