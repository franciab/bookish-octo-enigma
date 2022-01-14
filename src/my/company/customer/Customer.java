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
}
