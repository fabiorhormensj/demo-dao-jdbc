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

        System.out.println("\n=== TEST 2: Find Sellers by Department ID ===");
        System.out.print("Enter the ID of the Department: ");
        Department department = new Department(sc.nextInt(), null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller s : list) {
            System.out.println(s);
        }

        sc.close();
    }
}