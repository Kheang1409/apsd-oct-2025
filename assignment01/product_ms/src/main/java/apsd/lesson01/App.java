package apsd.lesson01;

import java.math.BigInteger;
import java.sql.Date;

import apsd.lesson01.DAO.ProductDAO;
import apsd.lesson01.Domain.Product;
import apsd.lesson01.Service.IProductService;
import apsd.lesson01.Service.ProductService;
import apsd.lesson01.Strategy.CSVExport;
import apsd.lesson01.Strategy.JsonExport;
import apsd.lesson01.Strategy.XmlExport;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        IProductService productService = new ProductService(new ProductDAO());
        

        // Add products with BigInteger IDs
        productService.addProduct(new Product(
            new BigInteger("31288741190182539912"),
            "Banana",
            Date.valueOf("2025-01-24"),
            124,
            0.55
        ));

        productService.addProduct(new Product(
            new BigInteger("29274582650152771644"),
            "Apple",
            Date.valueOf("2024-12-09"),
            18,
            1.09
        ));

        productService.addProduct(new Product(
            new BigInteger("91899274600128155167"),
            "Carrot",
            Date.valueOf("2025-03-31"),
            89,
            2.99
        ));

        productService.addProduct(new Product(
            new BigInteger("31288741190182539913"),
            "Banana",
            Date.valueOf("2025-02-13"),
            240,
            0.65
        ));


        // Display JsonExport products
        productService.setExportStrategy(new JsonExport());
        String jsonOutput = productService.displayProducts();
        System.out.println(jsonOutput);
        // Display XmlExport products
        productService.setExportStrategy(new XmlExport());
        String xmlOutput = productService.displayProducts();
        System.out.println(xmlOutput);
        // Display CSVExport products
        productService.setExportStrategy(new CSVExport());
        String csvOutput = productService.displayProducts();
        System.out.println(csvOutput);
    }
}
