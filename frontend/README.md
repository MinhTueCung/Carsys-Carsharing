# Readme zum Laborprojekt Carsharing System




## Grundlegende Informationen
Die Software befindet sich aktuell, stand 21.07.2021, noch in der Entwicklung. Es sind keine
Bugs bekannt.

Die beteiligten Entwickler sind:\
Nils Jahns 5086478\
Fabio Seekamp 5105348\
André Hammernik 5115002\
Malte-Sweer Schubert 5109432\
Minh Tue Cung 5081738

## Voraussetzungen

Für die vollständige Umsetzung wird folgende Software vorausgesetzt:

● Java SE Software Development Kit 8\
● Maven version 3.8.1
https://howtodoinjava.com/maven/how-to-install-maven-on-windows/

● Git
https://www.atlassian.com/de/git/tutorials/install-git

Zusätzlich wird benötigt, allerdings im späteren Verlauf erklärt:
● Node.js
https://nodejs.org/de/download/

● PostgreSQL
https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Des weiteren wurde das Projekt auf Windows 10 entwickelt und getestet.
Herunterladen der Projektdateien

## Backend

Um lokal den Backend-Teil des Projektes zu hosten, sollte ein neues Verzeichnis erstellt
werden. In diesem Verzeichnis sollte mit Rechtsklick Git Bash geöffnet werden.
In der dortigen Kommandozeile gibt man nun den Befehl

● git clone https://git.informatik.hs-bremen.de/njahns/SOFTW2_TEAM_6_BACKEND

ein. Möglicherweise fragt Git nach Credentials für den Git Server der HS Bremen. Sofern die
Eingabe richtig war, wird nun der Code heruntergeladen.

## Frontend

Für den Frontend-Teil gilt dasselbe Prinzip. Es muss ein neues Verzeichnis angelegt und Git
Bash gestartet werden. In der Kommandozeile wird nun der Befehl:

● git clone
https://git.informatik.hs-bremen.de/ahammernik/SOFTW2_TEAM_6_FRONTEND

eingegeben.

## Erstellung der Datenbank

Für die Erstellung der Datenbank führt man PgAdmin aus und legt eine neue Datenbank an.
Falls es nicht zuvor installiert wurde:

https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Beim Namen der Datenbank besteht freie Wahl, allerdings ist darauf zu achten, wer als
Besitzer der eingestellt ist. Das Passwort und der Name des Besitzers der Datenbank ist
bereit zu halten.
Die Tabellen müssen nicht selbst erstellt werden.
Im Projektordner des Backends öffnen man nun unter dem Pfad src/main/resources die
Datei applications.properties und gibt unter spring.datasource unter url hinter
jdbc:postgresql://localhost:5432/ den Namen der Datenbank ein.

Beispiel:\
spring.datasource.url=jdbc:postgresql://localhost:5432/softw2
Bei Username und Passwort wird der zuvor zugeteilte Besitzer und die Credentials
eingegeben.

Beispiel:\
spring.datasource.username=postgres
spring.datasource.password=123
Starten der Projekte

## Backend

Zum Starten des Backends geht man nun in das dafür erstellte Verzeichnis und öffnet Git
Bash. Dort gibt man nun folgende Befehle ein. Dabei ist jeweils abzuwarten, bis der
vorherige Befehl vollständig ausgeführt wurde.

● mvn clean package\
● java -jar target/swagger-spring-1.0.0.jar

## Laden der Testdaten
Wenn sich die Software noch in der Testphase befindet, lassen sich die meisten Funktionen
am besten mit Testdaten ausprobieren. Dafür wurde eine Datei “Testdaten.sql” im Repository
des Backends mit bereitgestellt. Nun öffnet man die SQL Shell. Der Server ist zum Testen
Localhost und kann einfach mit Enter ohne weitere Eingabe bestätigt werden. Bei Database
muss nun der Name der erstellten Datenbank eingegeben werden und mit Enter bestätigt
werden. Der Port bleibt bei 5432 und wird mit Enter bestätigt. Bei Username wird der zuvor
angegebene Besitzer verwendet und mit Enter bestätigt. Bei Passwort wird nun ebenfalls
das zuvor angegebene Passwort eingegeben und bestätigt.
Mit \i lassen sich nun die Testdaten unter Eingabe des Verzeichnisses laden. Aufgrund
vielleicht fehlender Berechtigungen sind gleich zwei Beispiele angeben, wie die Datei
geladen werden kann. Es muss nur eins ausgeführt werden.

● \i C:/Users/Test/Softw2/Testdaten.sql\
● \i ‘C:\\Users\\Test\\Softw2\\Testdaten.sql’\

Diesen Befehl bestätigt man mit Enter und kann nach einem kurzen Augenblick die Shell
schließen.

## Frontend

Um das Frontend zu starten sollte Node.js heruntergeladen und installiert sein:\
https://nodejs.org/en/

Durch Eingabe der Windowstaste + R und eingabe von cmd öffnet sich die Konsole.
In dieser werden nun die folgenden Befehle eingegeben

● npm install -g npx\
● npm install -g yarn

Danach wechselt man mit dem folgenden Befehl in das Verzeichnis, das den Frontend-Teil
beinhaltet:

● cd [Verzeichnis]

Nun muss im Verzeichnis der folgende Befehl eingegeben werden:

● yarn

Nach vollständiger Installation kann nun das Projekt mit

● yarn start

gestartet werden. Es ist dann unter http://localhost:3000/ im eigenen Browser erreichbar.
Im Terminal wird ebenfalls die lokale IP-Adresse genannt, die verwendet werden kann um
mit verschiedenen Geräten im selben Netzwerk die Website zu testen.

## Support
Bei technischen Problemen oder Rückfragen steht das Entwicklerteam von Carsys
Carsharing zur Verfügung.
## Lizenzbestimmungen
Das alleinige Eigentum an der Software verbleibt bei Car Gateway. Sie dürfen die Software
lediglich entsprechend der Lizenzbestimmungen von Car Gateway nutzen.
