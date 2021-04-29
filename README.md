###### url-shortener-microservice

This service helps you to make you URLs shorter.

**About project**
This project uses next technologies:
- Spring Boot;
- Spring JPA;  
- Maven;
- h2 database (used in memory and TCP mode);
- simple web form based on HTML/CSS/Bootstrap/AJAX;

**#How does it work**
Yo\`re enter link in Full URL field and clicking on the button. That all. Server response  in shortened version of
you link, like this:
`http://localhost:8080/{randomchar}`

Take this url and type it in new tab of your browser.
Notice, that the service works while app is running. After rebooting database cleans and all information 
will be lost.

**#Algorithm** 
Algorithm is based on id of url in database. For example, you enter :"https://www.baeldung.com/".
ID will 1. Then this id divides on number 66 (number of possible chars) and in chain of possible char returns
index of remainder. Possible chars:
`"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"`

**#How to start this app**
Clone this project. Then run application in IDE or build it with Maven (stage package) and run 
with the following command :
`java -jar url-shortener-1.0.jar`
Open browser and enter in address bar: http://localhost:8080/.
