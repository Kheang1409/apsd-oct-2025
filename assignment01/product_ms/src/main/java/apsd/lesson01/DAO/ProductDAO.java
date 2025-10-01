package apsd.lesson01.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import apsd.lesson01.Domain.Product;

public class ProductDAO implements IProductDAO {

    private List<Product> products = new ArrayList<>();
    
    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Collection<Product> getProducts() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort((p1, p2) -> {
            int nameCmp = p1.getName().compareToIgnoreCase(p2.getName());
            if (nameCmp != 0) return nameCmp; // name ascending
            return Double.compare(p2.getUnitPrice(), p1.getUnitPrice()); // price descending
        });
        return sorted;
    }
}
