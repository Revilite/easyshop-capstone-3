# Easyshop Capstone 3

## Installation

To use this project you must run the backend application first before running any tests on a API application or running
the front end. When the back end application is ran, spring boot creates a webserver that is used to create all of the 
requests that is needed. Next make sure that the correct database is made. Ensure this by running the SQL script that
is located inside of the database folder. Running that script will create the database as well as populate it with
some sample items.

## Features

This project includes the eashyshop website which is a front end and back end projects. The main focus of this capstone
was to focus on the back end and working with different REST requests to send to the frontend. There are 5 phases that the 
capstone focused on, mainly focusing on the controllers and implementing MYSQL databases.

## Required phases

Included were the first 2 phases of the capstone. This includes implementing the DAO and controller classes to the category
classes ensuring that all of the GET, POST, PUT and DELETE requests were handled with the right authority. the 2nd phase was
fixing the bugs that were inside of the product DAO and controller classes.

![image](https://github.com/user-attachments/assets/7dc6abf8-68ce-44b0-afd4-e08c8ab19db5)

## Optional phases

Included were the last 3 phases of the capstone. Including adding logic to the DAO and controller classes of the Shopping cart, 
Profile, and completly implementing orders. By far I would have to say that creating the orders was the hardest part, because 
I did not have any starter code to work with. Everything that was made to handle creating and checking out order was made by me. 


## Testing 

For the testing, we used postman to tests whether the requests were handled right and gave back or updated that right information. 
To help us with this there were included tests made in JavaScript to make sure that we got the right data and response codes. 

![image](https://github.com/user-attachments/assets/21330e24-f3d0-4d68-873a-7d88e654b762)

The problem then becomes making sure that all of the prequesits are fulfilled to make sure the end to end tests all run green. 
1. Make sure that the webserver is running
2. Make sure that the testing DB is completley refreshed
3. Run postman tests and pray to god they work


## Author
Jacob Lockhart :)

