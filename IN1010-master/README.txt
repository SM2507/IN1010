Når du skal kompilere til en bestemt mappe:
javac -d (mappeadresse fra din posisjon) filnavn.java

Når du skal kjøre kompilert program:
java -cp (mappeadresse fra din posisjon) filnavn

Når du skal kjøre kompilert program hvis package-deklarasjon er inkludert:
java -cp (mappeadresse fra din posisjon) packagenavn.filnavn 



Use the -d command line parameter with javac to tell it what directory you'd like to store the compiled class files in. Then, to run the program, simply include this directory in the classpath:

javac -d some/directory myjavafile.java
java -cp some/directory myjavafile

