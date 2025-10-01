package apsd.lesson01.Service;

import apsd.lesson01.Domain.Product;
import apsd.lesson01.Strategy.IExportStrategy;

public interface IProductService {
    void addProduct(Product product);
    void setExportStrategy(IExportStrategy exportStrategy);
    String displayProducts();
}