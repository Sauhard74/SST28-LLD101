import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoicePersistence persistence;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoicePersistence persistence, InvoiceFormatter formatter) {
        this.persistence = persistence;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(TaxPolicy taxPolicy, DiscountPolicy discountPolicy, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        
        List<InvoiceFormatter.LineItem> invoiceLines = new ArrayList<>();
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            if (item == null) {
                throw new IllegalArgumentException("Unknown menu item: " + l.itemId);
            }
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceFormatter.LineItem(item.name, l.qty, lineTotal));
        }

        
        double tax = taxPolicy.calculateTax(subtotal);
        double taxPercent = taxPolicy.getTaxPercent();
        double discount = discountPolicy.calculateDiscount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        
        String printable = formatter.format(invId, invoiceLines, subtotal, taxPercent, tax, discount, total);
        System.out.print(printable);

        
        persistence.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + persistence.countLines(invId) + ")");
    }
}
