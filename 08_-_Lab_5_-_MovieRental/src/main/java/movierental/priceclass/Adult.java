package movierental.priceclass;

public class Adult implements PriceClass{

  @Override
  public double calculateRentingCost(int daysRented) {
    return 5 * daysRented;
  }
}
