package apsd.lesson01.Strategy;

import java.util.Collection;

import apsd.lesson01.Domain.Product;


public class XmlExport implements IExportStrategy
{
    @Override
    public String export(Collection<Product> products) {
        var sb = new StringBuilder();
        sb.append("<Products>\n");

        for (Product p : products) {
            sb.append("  <Product>\n");
            sb.append("    <Id>").append(p.getId()).append("</Id>\n");
            sb.append("    <Name>").append(escapeXml(p.getName())).append("</Name>\n");
            sb.append("    <SuppliedDate>").append(p.getSuppliedDate()).append("</SuppliedDate>\n");
            sb.append("    <Qty>").append(p.getQuantity()).append("</Qty>\n");
            sb.append("    <UnitPrice>").append(String.format("%.2f", p.getUnitPrice())).append("</UnitPrice>\n");
            sb.append("  </Product>\n");
        }

        sb.append("</Products>");
        return sb.toString();
    }

    private String escapeXml(String value) {
        if (value == null) return "";
        return value
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;");
    }
}
