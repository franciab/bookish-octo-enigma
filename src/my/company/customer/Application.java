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
import java.util.stream.Stream;

public class Application {
	public static void main(String[] args) {
		String file = args[0], inputCountry = args[2].toLowerCase();
		List<Customer> customerList = new ArrayList<>();
		int inputMmembershipYears;

		try (BufferedReader fileBufReader = new BufferedReader(new FileReader(file))) {
			String fileLine;
			fileBufReader.readLine(); // To avoid the first header line to be processed.
			while ((fileLine = fileBufReader.readLine()) != null) {
				String[] fileLineTokens = fileLine.split(",");
				Customer customer = new Customer(fileLineTokens[0], LocalDate.parse(fileLineTokens[1].trim()),
						LocalDate.parse(fileLineTokens[2].trim()), fileLineTokens[3].toLowerCase(), fileLineTokens[4].equals("1"), fileLineTokens[5]);
				
				customer.setAge(Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears());
				customer.setYearsOfMembership(Period.between(customer.getMemberSince(), LocalDate.now()).getYears());
				customerList.add(customer);
			}

			
			Predicate<Customer> isAdult = c -> c.getAge() >18;
			Predicate<Customer> hasNewsLetterSubsciption = c -> c.isSubscribingToNewsletter();
			Predicate<Customer> countryFilter = c -> c.getCountry().equals(inputCountry);
			

			inputMmembershipYears = Integer.parseInt(args[1]);
			Predicate<Customer> memberShiopFilter = c -> c.getYearsOfMembership() >= inputMmembershipYears; 
			
			Consumer<Customer> printcustomer = c ->{
				System.out.println("Customer name: "+ c.getName() +"  Membership Number: "+c.getMembershipNumber());
			};
			
			Stream<Customer> customerStream = customerList.stream()
			.filter(hasNewsLetterSubsciption.and(isAdult).and(memberShiopFilter));
			if(!inputCountry.equals("all")) {
				customerStream=customerStream.filter(countryFilter);
			}
			customerStream.forEach(printcustomer);
		
		}catch(NumberFormatException e) {
			System.out.println("Enter valid number for 'Number of years for membership'");
		} catch (DateTimeParseException e) {
			System.out.println("Cannot Parse Date: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("File not found with exception: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOExcepiton: " + e.getMessage());
		}

	}
}
