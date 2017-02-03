# user-storage-app

Simple program that takes a few csv-like files, containing records, keeps the records in memory, and outputs the data sorted in a few different ways.
Sorting output is initally printed to console, then is available through webserver

This program expects the file formats to be 

LastName.FirstName.Gender.FavoriteColor.DateOfBirth

Where "." is the delimiter of choice. Date of birth must be in American format i.e. MM/DD/YYYY

The webserver will launch on loopback port 3000.

Supported endpoints: 

/records/gender (GET)  
/records/name (GET)  
/records/birth-date (GET)  
/records (POST)

Once the server is running. You can post a new record in CSV format 
        
        curl -d "London,Mike,Male,Blue,11/11/2012" http://localhost:3000/records

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To launch the program, you need to pass in three text files containing pipe separated values, comma separated values, and space separated values in that order


        lein run text1.psv text2.csv text2.ssv


## Running test suite

        lein test

## License

Copyright © 2017 Brian Gorman

Distributed under the Apache 2.0 License
