package bd.edu.seu.lendengo.interfaces;

import bd.edu.seu.lendengo.models.Customer;
import bd.edu.seu.lendengo.models.User;

import java.util.ArrayList;

public interface CustomerInterface {
    public int insertCustomer(Customer customer);
    public int updateCustomer(Customer customer);
    public int deleteCustomer(Customer customer);
    public ArrayList<Customer> getAllCustomers();
}
