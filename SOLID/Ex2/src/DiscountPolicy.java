public interface DiscountPolicy {
  double calculateDiscount(double subtotal, int distinctLines);
}
