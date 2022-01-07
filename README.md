1) To run the application
	-> Right click the application and click run as "Spring Boot Application" (select ExcelApplication file)
         or
	-> Open ExcelApplication or Click ExcelApplication, Right click the file and run as "Java application"     
	
2) Since it is small filters using GET Method (maximum URL length is 2048 characters)
	
3) Exposed as an API so we need Postman to test	or browser since it is get method.
	1) Without any filters
		-> http://localhost:8080/getData 
	2) With single filters
		a) with BankName filter
			-> http://localhost:8080/getData?bankName=Amazing%20Bank
		b) with Type filter
			-> http://localhost:8080/getData?type=ATM
		c) with City filter
			-> http://localhost:8080/getData?city=Dallas
		d) with State filter
			-> http://localhost:8080/getData?state=TX
		e) with ZipCode filter
			-> http://localhost:8080/getData?zipCode=75202
	3) With multiple filters	
		a) with BankName and ZipCode filter
			-> http://localhost:8080/getData?bankName=Amazing%20Bank&zipCode=75202
		b) with ZipCode, City and State filter
			-> http://localhost:8080/getData?zipCode=75202&city=Dallas&state=TX
		c) with City and State filter
			-> http://localhost:8080/getData?city=Dallas&state=TX
		d) with ZipCode, City, State and BankName filter
			-> http://localhost:8080/getData?zipCode=75202&city=Dallas&state=TX&bankName=Amazing	
		e) with All filter
			-> http://localhost:8080/getData?bankName=Amazing%20Bank&zipCode=75202&city=Dallas&state=TX&type=ATM	
	
4) The other way to run the application is, Right click the ExcelRead.java and click "Run As" and then "Run Configuration" and click 
    "Arguments" tab and add the "VM Agruments" like mentioned below
    -> -DbankName="Amazing Bank" -Dstate=NY
    -> If any spaces in the content like Amazon Bank, then use double quotes
	-> Since we are using apache poi(3rd party), I used eclipse VM agruments to run.			

5) I guess, Junit can be enough for testing this.				

Note:
1) Server by default run at 8080		
2) Attached VMArguments.PNG to find how to pass VM Arguments
3) Attached xls file for reference