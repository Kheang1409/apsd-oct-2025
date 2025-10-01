package apsd.lesson01.DAO;

import java.util.Collection;

import apsd.lesson01.Domain.Product;

public interface IProductDAO {
    void addProduct(Product product);
    Collection<Product> getProducts();    
}
