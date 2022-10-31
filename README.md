# PiBot
Evolution of the Java Telegram Bot, 

It's a simple telegram echo bot written in java. Unlike the bot I wrote earlier, this one is slightly different. Send messages received from any user to a
logServer listening on a machine. Since once the bot code is loaded and started, it runs constantly until it is deleted. If I tried to start the bot code on
a local machine it would create multiple instances of the same bot and consequently there would be conflicts and malfunctions in reading the console log.
So this code must be loaded and started only once and every time you want to see the user log you just have to start the ConsoleServer which will receive
all the information on a port.

Use:
for the first time:
 -"mvn install" in the PIbot folder (pom.xml folder)
 -"java -jar Main <logConsole ipaddress> <logConsole listening port> " 

-and in the machine that will receive the log:
 -"javac ServerMain.java"
 -"java ServerMain <listeningPort>"

for all other times (until the bot is deleted):
 -just run the ServerMain.class file
   "java ServerMain <listeningPort>"
 
 
 
 
 
 #Note
 I know this code can still improve a lot, I will try to make updates and improvements whenever possible.
If you find any bugs or problems of any kind please do not hesitate to report it, thank you.
