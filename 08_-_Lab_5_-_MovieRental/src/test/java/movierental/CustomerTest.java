package movierental;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static movierental.MovieFactory.*;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void testCustomer() {
		Customer c = new CustomerBuilder().build();
		assertNotNull(c);	
	}

	@Test
	public void testAddRental() {
		Customer customer2 = new CustomerBuilder().withName("Sallie").build();
		Movie movie1 = regular("Gone with the Wind");
		Rental rental1 = new Rental(movie1, 3); // 3 day rental
		customer2.addRental(rental1);
	}

	@Test
	public void testGetName() {
		Customer c = new Customer("David");
		assertEquals("David", c.getName());
	}

	@Test
	public void statementForRegularMovie() {
		Movie movie1 = regular("Gone with the Wind");
		Rental rental1 = new Rental(movie1, 3); // 3 day rental
		Customer customer2 = 
			new CustomerBuilder()
				.withName("Sallie")
				.withRentals(rental1)
				.build();
		String expected = "Rental Record for Sallie" + System.lineSeparator() + "" +
							"\tGone with the Wind\t3.5" + System.lineSeparator() + "" +
							"Amount owed is 3.5" + System.lineSeparator() + "" +
							"You earned 1 frequent renter points";
		String statement = customer2.invoice();
		assertEquals(expected, statement);
	}
	
	@Test
	public void statementForNewReleaseMovie() {
		Movie movie1 = newRelease("Star Wars");
		Rental rental1 = new Rental(movie1, 3); // 3 day rental
		Customer customer2 = 
			new CustomerBuilder()
				.withName("Sallie")
				.withRentals(rental1)
				.build();
		String expected = "Rental Record for Sallie" + System.lineSeparator() + "" +
							"\tStar Wars\t9.0" + System.lineSeparator() + "" +
							"Amount owed is 9.0" + System.lineSeparator() + "" +
							"You earned 2 frequent renter points";
		String statement = customer2.invoice();
		assertEquals(expected, statement);
	}
	
	@Test
	public void statementForChildrensMovie() {
		Movie movie1 = childrens("Madagascar");
		Rental rental1 = new Rental(movie1, 3); // 3 day rental
		Customer customer2 
			= new CustomerBuilder()
					.withName("Sallie")
					.withRentals(rental1)
					.build();
		String expected = "Rental Record for Sallie" + System.lineSeparator() + "" +
							"\tMadagascar\t1.5" + System.lineSeparator() + "" +
							"Amount owed is 1.5" + System.lineSeparator() + "" +
							"You earned 1 frequent renter points";
		String statement = customer2.invoice();
		assertEquals(expected, statement);
	}

	@Test
	public void statementForAdultMovie() {
		Movie movie1 = adult("Backstreet sloeries V");
		Rental rental1 = new Rental(movie1, 5); // 3 day rental
		Customer customer2
				= new CustomerBuilder()
				.withName("Sallie")
				.withRentals(rental1)
				.build();
		String expected = "Rental Record for Sallie" + System.lineSeparator() + "" +
				"\tBackstreet sloeries V\t25.0" + System.lineSeparator() + "" +
				"Amount owed is 25.0" + System.lineSeparator() + "" +
				"You earned 1 frequent renter points";
		String statement = customer2.invoice();
		assertEquals(expected, statement);
	}
	
	@Test
	public void statementForManyMovies() {
		Movie movie1 = childrens("Madagascar");
		Rental rental1 = new Rental(movie1, 6); // 6 day rental
		Movie movie2 = newRelease("Star Wars");
		Rental rental2 = new Rental(movie2, 2); // 2 day rental
		Movie movie3 = regular("Gone with the Wind");
		Rental rental3 = new Rental(movie3, 8); // 8 day rental
		Customer customer1
			= new CustomerBuilder()
				.withName("David")
				.withRentals(rental1, rental2, rental3)
				.build();
		String expected = "Rental Record for David" + System.lineSeparator() + "" +
							"\tMadagascar\t6.0" + System.lineSeparator() + "" +
							"\tStar Wars\t6.0" + System.lineSeparator() + "" +
							"\tGone with the Wind\t11.0" + System.lineSeparator() + "" +
							"Amount owed is 23.0" + System.lineSeparator() + "" +
							"You earned 4 frequent renter points";
		String statement = customer1.invoice();
		assertEquals(expected, statement);
	}
}
