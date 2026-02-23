public abstract class TaxPolicy {
  public double calculateTax(double subtotal) {
    return subtotal * (getTaxPercent() / 100.0);
  }

  public abstract double getTaxPercent();
}
