drop database Store;
create database Store;
use Store;


create table Orders(OrderId int not null auto_increment, Products char(50), CustomerId int, Quantity int, 
	PurchaseTotal double, CreditCardNumber char(25), ExpirationDate char(7), SecurityCode int, primary key (OrderId));
insert into Orders values(null, "1,2,3,4", 1, 1, 14.99, '1234123412341234', '04/17', 123);


create table Customers(CustomerId int not null auto_increment, FirstName char(50), MiddleName char(50), LastName char(50), 
	MailingAddressId int, BillingAddressId int, PhoneNumber char(50), Email char(100),
	UserName char(50), Password char(50), primary key (CustomerId));
insert into Customers values(null, 'Christopher', 'Michael', 'Smith', 1, 1, '(503) 313-5804', 'csmith@fakesite.com', 'csmith', '12345'),
							(null, 'Johnathan', 'Robert', 'Ericson', 2, 2, '(503) 555-1212', 'johnny2014@abcxyz.com', 'johnny2014', '12345'),
							(null, 'Superman', 'X', 'X', 1, 2, '(555) 555-5555', 'kal.el@krypton.net', 'q', 'q');


create table Addresses(AddressId int not null auto_increment, Street1 char(100), Street2 char(100), City char(100), 
	State char(2), ZipCode char(10), primary key (AddressId));
insert into Addresses values(null, '123 Fake Street', '', 'Beaverton', 'OR', '97007'),
							(null, '12345 NW Cedar Ct', '', 'Hillsboro', 'OR', '97124');


create table Products(ProductId int not null auto_increment, Description char(200), Image char(200), 
	Price decimal(6, 2), Count int, primary key (ProductId));
insert into Products values(null, 'Nike Dunk Low Pro SB', 'Nike Dunk Low Pro SB.jpg', 85.00, 50),
							(null, 'Nike Rabona', 'Nike Rabona.jpg', 70.00, 20),
							(null, 'Nike Flyknit Air Max', 'Nike Flyknit Air Max.jpg', 225.00, 45),
							(null, 'Nike Lacrosse All Time Pullover', 'Nike Lacrosse All Time Pullover.jpg', 55.00, 30),
							(null, 'Nike Kampai Print 2.0 Down Mens Jacket', 'Nike Kampai.jpg', 230.00, 12),
							(null, 'Nike True Snapback Hat', 'Nike True Snapback Hat.jpg', 25.00, 30);