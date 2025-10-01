package apsd.lesson01.Strategy;

import java.util.Collection;
import apsd.lesson01.Domain.Product;

public class CSVExport implements IExportStrategy {
    @Override
    public String export(Collection<Product> products) 
    {
        var sb = new StringBuilder();
        sb.append("Id,Name,SuppliedDate,Qty,UnitPrice\n");

        for (var p : products) {
            var id = p.getId();
            var name = escapeCSV(p.getName());
            var date = p.getSuppliedDate().toString();
            var quantity = p.getQuantity();
            var price = p.getUnitPrice();

            sb.append(String.format("%d,%s,%s,%d,%.2f\n", id, name, date, quantity, price));
        }

        return sb.toString();
    }

    private String escapeCSV(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }
}
