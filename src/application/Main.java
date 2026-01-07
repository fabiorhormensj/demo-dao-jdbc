package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department department = new Department(2, "Books");
        Seller seller = new Seller(1, "João", "joao@gmail.com", new Date(), 2000.0, department);

        System.out.println(seller);
    }
}