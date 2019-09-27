package movierental.priceclass;

public class Regular implements PriceClass {

  @Override
  public double calculateRentingCost(int daysRented) {
    double costForMovie = 2;
    if (daysRented > 2) {
      costForMovie += (daysRented - 2) * 1.5;
    }
    return costForMovie;
  }

}
