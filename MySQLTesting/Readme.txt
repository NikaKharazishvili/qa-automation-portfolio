How to Run This Test?

1. Start your MySQL server so it’s running locally.
2. Import game_accounts.sql into your MySQL instance.
3. Open Main.java and change the values of dbUser and dbPassword to match your MySQL credentials.
These can usually be found in:   C:\xampp\phpMyAdmin\config.inc.php
4. Open Command Prompt (CMD) and navigate to the project folder. Then compile and run:
cd desktop\MySQLTesting
javac Main.java
java -cp ".;mysql-connector.jar" Main
(Note: On Linux or Mac, replace .; with .:)