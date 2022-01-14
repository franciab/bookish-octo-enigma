# Customer report task

The marketing department wants a program that they can use to send out special offers to a selected group of customers.
The program should read and parse a file with customers and give a report based on the input arguments to the program.
Each customer should be store in the Customer class. A list of all customers is stored in the file named "customers.txt".

The arguments to the program is as follows:
* First argument: the file containing the customers (customers.txt)
* Second argument: the number of years of membership
* Third argument: the country or "all" if all countries should be listed

For example this command:

`java my.company.customer.Application customers.txt 10 all`

should read the file customers.txt and print a list with the name and membership number for all adult customers (over 18 years) that has been member for 10 years or more and for all countries.

This command:

`java my.company.customer.Application customers.txt 5 germany`

should read the file customers.txt and print a list with the name and membership number for all adult customers (older than 18 years) that has been member for 5 years or more, but only from Germany.

Please fork this git repository and make pull request with your implementation.
