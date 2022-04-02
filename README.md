# application-api

**Microservice: java-assessment**

How to Run:

1. Run the banking.sql SQL Script at resources folder.
2. JavaAssessmentApplication.java <- run this to start the service locally for development purposes using embedded Tomcat

----API collections----
Run thru Postman.

1. Create Account Holder (POST http://localhost:8080/api/account)
![image](https://user-images.githubusercontent.com/69787232/160737620-a813e243-76fe-4921-9f94-a9c2975fb1cd.png)

2. Account Balance (GET http://localhost:8080/api/account/{accountPin})
![image](https://user-images.githubusercontent.com/69787232/160737685-d198c235-e372-4d3c-9d7b-2f407bb8effb.png)

3. Debit Transaction (POST http://localhost:8080/api/transaction)
![image](https://user-images.githubusercontent.com/69787232/160737734-fe811195-c8eb-4df5-bd25-41aff217b396.png)

4. Credit Transaction (POST http://localhost:8080/api/transaction)
![image](https://user-images.githubusercontent.com/69787232/160737784-e45dc1e6-947d-4a79-8b86-75f024f5ed77.png)

5. Transaction History (GET http://localhost:8080/api/transaction-history/{accountPin})
![image](https://user-images.githubusercontent.com/69787232/160738089-36eb88ab-bdf5-4432-8891-8e021f2314f5.png)

**Web Application: simple-banking**

How to Run:

1. SimpleBankingApplication.java <- run this to start the service locally for development purposes using embedded Tomcat
2. Access homepage using the URL: http://localhost:9090/home
