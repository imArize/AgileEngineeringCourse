package movierental;

import movierental.priceclass.NewRelease;

import java.util.List;

public class Invoice {

    private final List<Rental> rentals;
    private final String customerName;

    private int points;
    private double totalCost;
    private StringBuilder invoiceBody;

    public Invoice(List<Rental> rentals, String name) {
        this.rentals = rentals;
        this.customerName = name;
        this.invoiceBody = new StringBuilder();

        compileInvoice();
    }

    private void compileInvoice() {
        for (Rental rental : rentals) {
            updateInvoiceBody(rental);
            updateTotalCost(rental);
            updateLoyaltyPoints(rental);
        }
    }

    private void updateInvoiceBody(Rental each) {
        invoiceBody.append(each.getInvoiceInfo());
        invoiceBody.append(System.lineSeparator());
    }

    private void updateTotalCost(Rental rental) {
        totalCost += rental.rentalAmount();
    }

    private void updateLoyaltyPoints(Rental rental) {
        points += calculateLoyaltyPoints(rental);
    }

    public String formatInvoiceText() {
        String invoiceText = getInvoiceHeader();
        invoiceText += invoiceBody;
        invoiceText += totalCostLine();
        invoiceText += renterPointLine();
        return invoiceText;
    }

    private String getInvoiceHeader() {
        return "Rental Record for " + customerName + System.lineSeparator();
    }

    private String totalCostLine() {
        return "Amount owed is " + totalCost + System.lineSeparator();
    }

    private String renterPointLine() {
        return "You earned " + this.points + " frequent renter points";
    }

    private int calculateLoyaltyPoints(Rental rental) {
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
