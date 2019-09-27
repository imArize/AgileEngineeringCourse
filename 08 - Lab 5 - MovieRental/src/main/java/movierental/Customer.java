package movierental;

import java.util.ArrayList;
import java.util.List;
import movierental.priceclass.NewRelease;
import movierental.priceclass.PriceClass;

public class Customer {

  private String _name;
  private List<Rental> _rentals = new ArrayList<Rental>();

  public Customer(String name) {
    _name = name;
  }

  public void addRental(Rental arg) {
    _rentals.add(arg);
  }

  public String getName() {
    return _name;
  }

  public String învoice() {
    StringBuilder invoiceText = new StringBuilder(getInvoiceHeader());

    for (Rental each : _rentals) {
      invoiceText.append(each.getInvoiceInfo());
      invoiceText.append(System.lineSeparator());
    }

    invoiceText.append(footerLines());
    return invoiceText.toString();
  }

  private String getInvoiceHeader() {
    return "Rental Record for " + getName() + System.lineSeparator();
  }

  private String footerLines() {
    return totalCostLine() + renterPointLine();
  }

  private String renterPointLine() {
    return "You earned " + earnedLoyaltyPoints() + " frequent renter points";
  }

  private String totalCostLine() {
    return "Amount owed is " + getTotalCost() + System.lineSeparator();
  }

  private double getTotalCost() {
    double totalAmount = 0;
    for (Rental each : _rentals) {
      totalAmount += each.rentalAmount();
    }
    return totalAmount;
  }

  private int earnedLoyaltyPoints() {
    int points = 0;
    for (Rental rental : _rentals) {
      points += updateLoyaltyPoints(rental);
    }
    return points;

  }

  private int updateLoyaltyPoints(Rental rental) {
    return 1 + bonusPoints(rental);
  }

  private int bonusPoints(Rental rental) {
    if (eligableForBonusPoints(rental)) {
      return 1;
    }
    return 0;
  }

  private boolean eligableForBonusPoints(Rental each) {
    return (each.getMovie().getPriceClass() instanceof NewRelease)
        && each.getDaysRented() > 1;
  }
}
