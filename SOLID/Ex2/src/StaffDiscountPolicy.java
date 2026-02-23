public class StaffDiscountPolicy implements DiscountPolicy {
  @Override
  public double calculateDiscount(double subtotal, int distinctLines) {
    if (distinctLines >= 3) {
      return 15.0;
    }
    return 5.0;
  }
}
