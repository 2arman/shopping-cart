# shopping-card
an implementation of a supermarket checkout that calculates the total price of purchased items.

## Case Description
 implement the code for a supermarket checkout that calculates the total price of some
items.
An item has the following attributes
- Name
- Unit Price

Our goods are priced individually. Some items are multi-priced: buy n of them, and they will cost
you less than buying them individually. For example, item A might cost $50 individually, but this
week we have a special offer: buy three A s and they will cost you $130.

Here is an example of prices

|Item |Unit Price |Special Price|
| --- |:---------:|:-------------|
| A   |   $50      | 3 for $130  |
| B   |   $30      | 2 for $45   |
| C   |   $20      |             |
| D   |   $15      |             |

Our checkout accepts items in any order, so that if we scan a B, an A, and another B, well
recognize two Bs and price them at 45 (for a total price so far of 95). Because the pricing
changes frequently, we need to be able to pass in a set of pricing rules each time we start
handling a checkout transaction.

Here are some examples of cases:

|Items |Total |
| -------   |:-----------:|
| A,B       |   $80       | 
| A,A       |   $100      | 
| A,A,A     |   $130      |       
| C,D,B,A   |   $115      |            
###Design
This implementation use `java 11` , `Maven` , `Spring boot` , `cucumber` , `Mockito` , `Junit 5`

also,we use `Undertow` as and embedded web server. 

for API Documentation and manual testing we use the `Swagger` that can easily export and import to `Postman` collections.  
####Domain
Our main domains are **Item** , **Rule** and **Cart**.
We don't implement database connection or using any ORM.

We consider `Rule` as abstract domain that can extends by custom rules later.
At first try we only implement `QuantityRule` for handling discount on quantity.

####Test
For better sense use cucumber tools for implementing bdd and the Integration Tests.

####Improvement issues

Here are some improvements suggestion:

- Improving unit test coverage.
- Adding Spring security for adding the Authentication and Authorization.
- Using a Database for more consistency.

##  Build / Run
This spring boot project can run with both docker or directly compile with maven and run it.
Anyway, you can build and run it with any IDE.

#####![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) Docker Compose
 * **Docker Compose [deploy project]** : ```docker-compose up```
 * **Docker Compose [stop project]** : ```docker-compose down```
 * **Test Via Swagger** http://localhost:8080/swagger-ui/
 
#####![#c5f015](https://via.placeholder.com/15/c5f015/000000?text=+) Docker
  * **Docker Image Build** : ```docker build -t shopping-cart .```
  * **Docker Run** : ```docker run -p 8080:8080 shopping-cart```
  * **Test Via Swagger** http://localhost:8080/swagger-ui/
 
#####![#1589F0](https://via.placeholder.com/15/1589F0/000000?text=+) Maven     
 * **Build** : ```./mvnw clean package``` | ```mvnw.cmd clean package```
 * **Run** : ```java -jar target\shopping-cart-0.0.1-SNAPSHOT.jar```
 * **Test Via Swagger** http://localhost:8080/swagger-ui/
