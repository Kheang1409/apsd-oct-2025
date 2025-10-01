package apsd.lesson01.Strategy;

import java.util.Collection;

import apsd.lesson01.Domain.Product;

public interface IExportStrategy
{
    String export(Collection<Product> products);
}
