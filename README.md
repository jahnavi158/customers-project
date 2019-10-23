a. The purpose of this repository is to create an application to process all the information of Customers one by one and identify valid and invalid records.
This data of Customers is store in a CSV file named Info.csv. The Java application will open, read, parses and stores each valid data in database system.

b. The application first opens the file and reads using BufferReader. All the records of data are used using looping method. Each record is checked if 
it's valid or not. The data would be considered valid if it has an non-empty entry for all 10 columns(A-J). If it's valid each data is stored in a database in the system using the JDBC API.
First, a driver is connected to the location of where the database is stored by using its url. Next, a statement is created. Then, the statement is executed by using execiteUpdate method which 
which updates or modifies the data in the database. In this application, it adds the entries to the database. If the recors isn't valid, it sores the record data in a 2D array list. 
All the invalid records are written to a CSV file, named "newfile-bad.csv", using FileWriter. The application also keeps track of all the processed, succesfull, and failed records to write it to a log file. 
It writes to a logfile using Logger and FileHandler. 

c. The design concept I used is to first process he data, then check for valid data and do operations on them, and then take care of the invalid data and store it another CSV file. 
I also tested at each step to make sure the application is working like it's supposed to. Some of the assumptions I had are that the database was empty initially so I created an empty database.