package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepartmentName "
                            + "FROM seller JOIN department ON seller.DepartmentId = department.Id "
                            + "ORDER BY department.Id",
                    Statement.RETURN_GENERATED_KEYS
            );
            resultSet = preparedStatement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(resultSet.next()) {
                Department dep = map.get(resultSet.getInt("DepartmentId"));
                if(dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                list.add(seller);

            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepartmentName "
                            + "FROM seller JOIN department ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(resultSet.next()) {
                Department dep = map.get(resultSet.getInt("DepartmentId"));
                if(dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(resultSet, dep);
                list.add(seller);

            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepartmentName "
                    + "FROM seller JOIN department ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Department department = instantiateDepartment(resultSet);

                Seller seller = instantiateSeller(resultSet, department);

                return seller;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally{
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setDepartment(department);

        return seller;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepartmentName"));

        return department;
    }
}
