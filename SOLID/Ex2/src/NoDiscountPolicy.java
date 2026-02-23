public class NoDiscountPolicy implements DiscountPolicy {
  @Override
  public double calculateDiscount(double subtotal, int distinctLines) {
    return 0.0;
  }
}
