package movierental;

import movierental.priceclass.PriceClass;

public class Movie {

  private String title;
  private PriceClass priceClass;

  public Movie(String title, PriceClass priceClass) {
    this.title = title;
    this.priceClass = priceClass;
  }

  public String getTitle() {
    return title;
  }

  double costForMovie(int daysRented) {
    return priceClass.calculateRentingCost(daysRented);
  }

  public PriceClass getPriceClass() {
    return priceClass;
  }
}
