package my.company.customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		
		if (args.length < 3)
			LOGGER.log(Level.SEVERE, "Insufficient Input Arguments.");
		else {
			String fileName = args[0];
			String inputCountry = args[2].toLowerCase();
			int inputMmembershipYears = Integer.parseInt(args[1]);
			List<Customer> customerList = getCustomerList(fileName);
			getFilteredCustomerList(customerList, inputCountry, inputMmembershipYears);
		}
	}

	private static List<Customer> getCustomerList(String fileName) {
		List<Customer> customerList =  new ArrayList<>();
		try (BufferedReader fileBufReader = new BufferedReader(new FileReader(fileName))) {
			String fileLine;
			while ((fileLine = fileBufReader.readLine()) != null) {
				if (!fileLine.startsWith("#")) {
					String[] fileLineTokens = fileLine.split(",");
					Customer customer = new Customer(fileLineTokens[0], LocalDate.parse(fileLineTokens[1].trim()),
							LocalDate.parse(fileLineTokens[2].trim()), fileLineTokens[3].toLowerCase(),
							fileLineTokens[4].equals("1"), fileLineTokens[5]);

					customer.setAge(Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears());
					customer.setYearsOfMembership(
							Period.between(customer.getMemberSince(), LocalDate.now()).getYears());
					customerList.add(customer);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.log(Level.SEVERE, "Some data is missing in the input file. Please check.", e);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "Exception while parsing. Enter valid number.", e);
		} catch (DateTimeParseException e) {
			LOGGER.log(Level.SEVERE, "Cannot Parse Date: ", e);
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "File not found with exception: ", e);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		} 
		return customerList;
	}

	private static void getFilteredCustomerList(List<Customer> customerList, String countryName, int membershipYears) {
		Predicate<Customer> isAdult = c -> c.getAge() > 18;
		Predicate<Customer> hasNewsLetterSubsciption = Customer::isSubscribingToNewsletter;
		Predicate<Customer> countryFilter = c -> c.getCountry().equals(countryName);
		Predicate<Customer> memberShipFilter = c -> c.getYearsOfMembership() >= membershipYears;

		Consumer<Customer> printCustomerList = c -> System.out.println(
				String.format("Customer name: %s    Membership Number: %s", c.getName(), c.getMembershipNumber()));
		Stream<Customer> customerStream = customerList.stream()
				.filter(hasNewsLetterSubsciption.and(isAdult).and(memberShipFilter));
		if (!countryName.equals("all")) {
			customerStream = customerStream.filter(countryFilter);
		}
		customerStream.forEach(printCustomerList);

	}
}
