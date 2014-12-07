build:
	javac Cliente/*.java -encoding ISO-8859-1
	javac Servidor/Main.java Servidor/Items/*.java Servidor/Game/*.java Servidor/Server/*.java -encoding ISO-8859-1
clean:
	rm Cliente/*.class Servidor/*.class Servidor/Items/*.class Servidor/Game/*.class Servidor/Server/*.class
client:
	cd Cliente && java Main
cliente:
	cd Cliente && java Main	
server:
	cd Servidor && java Main
servidor:
	cd Servidor && java Main
