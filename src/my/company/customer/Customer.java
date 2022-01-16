package my.company.customer;

import java.time.LocalDate;

// First implementation of Customer class - please modify as needed.
public class Customer {
    private final String name;
    private final LocalDate dateOfBirth;
    private final LocalDate memberSince;
    private final String country;
    private final boolean subscribeNewsletter;
    private final String membershipNumber;
    private int age;
    private int yearsOfMembership;

    public Customer(String name, LocalDate dateOfBirth, LocalDate memberSince, String country, boolean subscribeNewsletter, String membershipNumber) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.memberSince = memberSince;
        this.country = country;
        this.subscribeNewsletter = subscribeNewsletter;
        this.membershipNumber = membershipNumber;
    }

    public boolean isSubscribingToNewsletter() {
        return subscribeNewsletter;
    }

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public LocalDate getMemberSince() {
		return memberSince;
	}

	public String getCountry() {
		return country;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public int getYearsOfMembership() {
		return yearsOfMembership;
	}

	public void setYearsOfMembership(int yearsOfMembership) {
		this.yearsOfMembership = yearsOfMembership;
	}
    
}
