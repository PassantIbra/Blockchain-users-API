# about the application

This is application is allowing users to create accounts, monitor 
and exchange virtual currencies betwen each others

# used technologies:
Spring boot framework 2.2.1 release.
Java 12.
MYSQL server with JDBC.

# used tools:
MYSQL.
Eclipse.
Postman as a client for the API.

# Database tables:
this API uses MYSQL server for database
it makes use of 2 tables
1-user
2-transaction
in order to consume this API you need to create a database named "practicum"
and the two tables with sample recoreds will be autogenerated from schema.sql and data.sql files in the project class path.

# REST API
This REST API is prioviding three different services:
1-Create new account.
2-Make new transaction.
3-Retrieve previous transactions from or to user.
Specifications of request and response for each of them is described below.
It also increments users balances by .25 VC automatically every 30 minutes. 

## Create new account: 

### Request:
    URI:../users
    Method: Post
### Request body Example 
    {
    "name":{user name},
    "email":{user email},
    "password":{password}
    }  
### Request body parameters specifications:
Name: not empty, size>2.
Email: not empty and valid email EX: example@ex.com.
Password: not empty and between 8 and 16 chars.

### Request body Example for a specific user:
    
     {
    "name":"Pasant",
    "email":"pasant @gmail.com",
    "password":"pasant123"
     }

### Response for a specific user:

    HTTP status: 201 Created
    
     {
    "name": "Pasant",
    "password": "pasant123",
    "email": "pasant@gmail.com",
    "id": "5a89a8aa-4c4f-4ba4-ad4d-2db1e2a357a8",
    "vc": 0.0
     }
      

### Invalid requests and their response:

Request: any request with any missing attribute or any attribute with unvalid value
Response: response body contains time, error message and details about the
Missing field.

      HTTP status: 400 bad request.
      
    {
    "timestamp": "2020-12-24T23:27:33.985+0000",
    "message": "Validation Failed",
    "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'user' on field 'name': rejected value [null]; codes [NotBlank.user.name,NotBlank.name]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.name,name]; arguments []; default message [name]]; default message [Name                    cannot be missing or empty]
    }

  
## Make new transaction: 

### Request:
    
    URI:../users/{id}/transactions
    Method: Post
    Header: id:{id}
### Request body example:
    [
    {
    "receiverEmail": {receiver email},
    "amount": {amount of virtual currencies to be sent}
    },
    {
    "receiverEmail": {receiver email},
    "amount": {amount of virtual currencies to be sent}
    }
    ]
### Request parameters specifications:

recreiverEmail: not empty ,valid email and for an existing user.
Amount: cannot be 0 or negative or empty.

  Request body is array of objects, it cannot be empty 
    or containing empty objects and the.
   
Maximum number of transactions is 10 transactions for 
different users.

A user should have enough balance for the requested transactions.

A user has to include receiver eamil and amount only in the request any other attributes will be discarded.

The id attribute in the header is mandatory and has to be a valid id.

### Request body example for a specific user:

    [
    {
    "receiverEmail": "passant@gmail.com",
    "amount": 1
    },
    {
    "receiverEmail": "mohamed@gmail.com",
    "amount": 1
    }
    ]
### Response for specific user:

    HTTP status: 201 created
    response body: empty.

### Invalid requests and their response:
 Request: request with invalid id header parameter.
Response:

   
    HTTP status: 401 Unauthorized.
    
    {
    "timestamp": "2020-12-24T23:41:06.260+0000",
    "message": "the id 854e67db-cfa4-4b53-97a6-077b2769ff9 is not valid",
    "details": "uri=/users/kl/transactions"
     }

Request: request with a missing field or a field with invalid value
Response:

    
    HTTP status: 400 bad request.
    
    {
     "timestamp": "2020-12-24T23:54:06.839+0000"
     "message": "Validation Failed",
     "details": {Exception msg}
    }
    
Request: request with more than 10 transactions for different useres
Response:
     
      HTTP status: 400 bad request.
    
    {
    "timestamp": "2020-12-28T12:18:58.946+0000",
    "message": "Validation Failed",
    "details": "makeTransactions.transactionsList: maximum number of transactions is 10 transactions for different users"
    }

Request: any request with inexistent receiver email
Response:

    HTTP status: 404 not found.

    {
    "timestamp": "2020-12-25T00:04:41.793+0000",
    "message": "these transactions contain some inexisted receivers, 
     none of the transactions will be processed",
    "details": "uri=/users/e90adfb9-3eff-48f1-8be6-3ab932a7e218/transactions"
     }

Request: any request has transactions that exceeds user's current balance.
Response:

    HTTP status: 422 unprocessable entity.
    
    {
    "timestamp": "2020-12-25T00:12:11.770+0000",
    "message": "your current balance is insufficient",
    "details": "uri=/users/e90adfb9-3eff-48f1-8be6-3ab932a7e218/transactions"
    }

## Retrieve previous transactions from or to user:

### Request: 

    Method: Get
    URI::../users/{id}/transactions
    Header: id:{id}
    Request body: empty
 
The id attribute in the header is mandatory and has to be a valid id.
 
the transactions retrieved is the transactions from or to the user with the id passed in the request header.

### Response for a specific user:

    HTTP Status: 200 Ok
    [{
        "senderEmail": "passant@gmail.com",
        "receiverEmail": "mohamed@gmail",
        "date": "2020-12-24T20:58:36.000+0000",
        "amount": 9.0
    },
    {
        "senderEmail": "passant@gmail.com",
        "receiverEmail": "aliaa@gmail",
        "date": "2020-12-24T20:58:36.000+0000",
        "amount": 11.0
    }]

### Invalid requests and their response:
request: empty request body with in valid id header
response: response of Request with invalid id header parameter.
    Response:
    
    HTTP status: 401 Unauthorized.
    {
    "timestamp": "2020-12-24T23:41:06.260+0000",
    "message": "the id 854e67db-cfa4-4b53-97a6-077b2769ff9 is not valid",
    "details": "uri=/users/id/transactions"
     }
