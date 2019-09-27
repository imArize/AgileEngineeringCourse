package movierental.priceclass;

public class NewRelease implements PriceClass {

  @Override
  public double calculateRentingCost(int daysRented) {
    return daysRented * 3;
  }


}
