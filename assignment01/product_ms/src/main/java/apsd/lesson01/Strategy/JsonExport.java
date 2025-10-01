package apsd.lesson01.Strategy;

import java.util.Collection;
import java.util.stream.Collectors;

import apsd.lesson01.Domain.Product;

public class JsonExport implements IExportStrategy
{  
     @Override
    public String export(Collection<Product> products) {
        var json = products.stream()
            .map(p -> String.format(
                "  {\n" +
                "    \"Id\": \"%s\",\n" +
                "    \"Name\": \"%s\",\n" +
                "    \"SuppliedDate\": \"%s\",\n" +
                "    \"Qty\": %d,\n" +
                "    \"UnitPrice\": %.2f\n" +
                "  }",
                p.getId().toString(),
                escapeJson(p.getName()),
                p.getSuppliedDate().toString(),
                p.getQuantity(),
                p.getUnitPrice()
            ))
            .collect(Collectors.joining(",\n"));

        return "[\n" + json + "\n]";
    }

    private String escapeJson(String value) {
        return value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
    }
}
