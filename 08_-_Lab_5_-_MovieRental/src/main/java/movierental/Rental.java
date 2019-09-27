package movierental;

/**
 * The rental class represents a customer renting a movie.
 */
public class Rental {

  private Movie _movie;
  private int _daysRented;

  public Rental(Movie movie, int daysRented) {
    _movie = movie;
    _daysRented = daysRented;
  }

  int getDaysRented() {
    return _daysRented;
  }

  Movie getMovie() {
    return _movie;
  }


  double rentalAmount() {
    return _movie.costForMovie(getDaysRented());
  }


  String getInvoiceInfo() {
    return "\t" + getMovie().getTitle() + "\t" + rentalAmount();
  }
}
