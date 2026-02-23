import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        // Setup dependencies
        InvoicePersistence persistence = new FileStore();
        InvoiceFormatter formatter = new InvoiceFormatter();
        CafeteriaSystem sys = new CafeteriaSystem(persistence, formatter);

        // Setup menu
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        // Process student order
        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1));

        TaxPolicy studentTax = new StudentTaxPolicy();
        DiscountPolicy studentDiscount = new StudentDiscountPolicy();
        sys.checkout(studentTax, studentDiscount, order);

        // Process staff order (stretch goal)
        System.out.println();
        List<OrderLine> staffOrder = List.of(
                new OrderLine("M1", 1),
                new OrderLine("C1", 2),
                new OrderLine("S1", 1));

        TaxPolicy staffTax = new StaffTaxPolicy();
        DiscountPolicy staffDiscount = new StaffDiscountPolicy();
        sys.checkout(staffTax, staffDiscount, staffOrder);
    }
}
