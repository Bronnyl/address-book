# Address Book

A simple java application which performs various queries (such as count by gender, who is the oldest etc) based on the Address Book data.
The Address Book data is supplied via a file.

### TODOs (and Known Issues)
* Need to ensure that validations are in place when reading data from file when the supplied data could be incorrect.
Currently the application assumes that each line will contain all the required data and in the correct format.
At the moment, the application only handles scenarios when the file is empty.

* The DateFormatter assumes that 16/03/77 belongs to the year 2077 which needs to be corrected.
However the required logic still works fine (such as who is oldest, difference in days works correctly as well) since everyone is in the same century.
This can easily be corrected by making the year as 4 digit, but since that's the input (which probably we have no control over) we need to work around it and
this will need to be rectified.

### Getting Started

* Ensure you have Java 8 and Maven 3.3.x installed on your machine.
* You then need to download the Project zip file. To do this:
	* Click the "Clone or download" button (the green colour button) and select "Download ZIP".
	* Save the zip file (address-book-master.zip) somewhere on your computer (say in /Users/{yourname}/Projects)
	* Extract the zip file in the same location. After extraction, you would see the folder as /Users/{yourname}/Projects/address-book-master
* Open terminal and go to the root folder of the project
	* In other words, go to "cd /Users/{yourname}/Projects/address-book-master"
* Type the following in terminal and hit enter: mvn clean install
* The above command will compile the project as well as run all the tests. You should see that all tests have been passed and the build was successful.

### See the source code
* To see the source code, you can open the project directly in IntelliJ

##### Note: 
* If you are using Windows, then the folder paths would be \ instead of a /
* This application was developed on a Mac OS, and these "Getting Started" instructions were verified on Windows machine.

## Authors

* Bronnyl Dias



