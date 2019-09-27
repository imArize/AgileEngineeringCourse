package movierental.priceclass;

public class Childrens implements PriceClass {

  @Override
  public double calculateRentingCost(int daysRented) {
    double costForMovie = 1.5;
    if (daysRented > 3) {
      costForMovie += (daysRented - 3) * 1.5;
    }
    return costForMovie;
  }

}
