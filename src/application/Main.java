package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
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
        sc.close();
    }
}