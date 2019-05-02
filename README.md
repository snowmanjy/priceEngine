<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu" /></a>

Run:
This is a maven project, to run it, run this command under the project root folder:
mvn package && java -jar target/priceEngine-1.0-SNAPSHOT.jar

Creat quote:
use postman
http://localhost:8080/quotes/
post
{
  "name": "testQuote",
  "quoteLines": [
    {
      "lineNumber": 0,
      "product": {
        "productName": "testProd1",
        "price": 20
      },
      "quantity": 3,
      "discount": 0,
      "totalPrice": 60
    }
  ],
  "discount": 0,
  "totalPrice": 0
}

Check quote:
type in browser:
http://localhost:8080/quotes/testQuote

change discount:
{
  "actionType" : "CHANGEDISCOUNT",
  "discount" : 0.10,
  "quoteLine" : {
  }
}
