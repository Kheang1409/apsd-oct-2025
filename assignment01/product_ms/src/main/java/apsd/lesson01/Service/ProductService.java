package apsd.lesson01.Service;

import java.lang.reflect.Proxy;
import apsd.lesson01.DAO.IProductDAO;
import apsd.lesson01.Domain.Product;
import apsd.lesson01.Proxies.Logging;
import apsd.lesson01.Strategy.IExportStrategy;

public class ProductService implements IProductService{
    
    IProductDAO productDAO;
    IExportStrategy exportStrategy;

    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
        var classLoader = this.productDAO.getClass().getClassLoader();
        this.productDAO = (IProductDAO) Proxy.newProxyInstance(
                classLoader, 
                new Class[] { IProductDAO.class},
                new Logging(this.productDAO)
        );
    }
    @Override
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    public void setExportStrategy(IExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    @Override
    public String displayProducts() {
        
        return exportStrategy.export(productDAO.getProducts());
    }
    
}
