import java.util.*;

public class PricingComponentFactory {
  public static PricingComponent createRoomComponent(int roomType) {
    return switch (roomType) {
      case LegacyRoomTypes.SINGLE -> new SingleRoom();
      case LegacyRoomTypes.DOUBLE -> new DoubleRoom();
      case LegacyRoomTypes.TRIPLE -> new TripleRoom();
      default -> new DeluxeRoom();
    };
  }

  public static PricingComponent createAddOnComponent(AddOn addOn) {
    return switch (addOn) {
      case MESS -> new MessAddOn();
      case LAUNDRY -> new LaundryAddOn();
      case GYM -> new GymAddOn();
    };
  }

  public static List<PricingComponent> createComponents(BookingRequest req) {
    List<PricingComponent> components = new ArrayList<>();

    // Add room component
    components.add(createRoomComponent(req.roomType));

    // Add add-on components
    for (AddOn addOn : req.addOns) {
      components.add(createAddOnComponent(addOn));
    }

    // Add base deposit
    components.add(new BaseDeposit());

    return components;
  }
}
