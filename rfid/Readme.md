# Readme zum RFID-Modul vom Laborprojekt Carsharing System

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

Für die vollständige Umsetzung wird folgendes vorausgesetzt:

- Raspberry Pi Zero mit WLAN/LAN-Verbindung
- RFID-RC522-Lesegerät
- Python 3

## Zuordnung der GPIO-Pins

SDA 24\
SCK 23\
MOSI 19\
MISO 21\
GND 25\
RST 22\
3.3 Volt 17\
LED-Fahrzeug-Status(grün): Ausgeliehen 20\
LED-Fahrzeug-Status(rot): Nicht ausgeliehen 21\
LED-Karten-Status(grün): Akzeptiert 12\
LED-Karten-Status(rot): Abgeleht 16


## Ausführung

Das Python-Skript wird mit dem Befehl "sudo python3 carServer.py" auf einem Raspberry Pi Zero gestartet. Danach steht der Mikrocomputer als Server zur Verfügung um RFID-Daten entgegenzunehmen. 


## Support
Bei technischen Problemen oder Rückfragen steht das Entwicklerteam von Carsys
Carsharing zur Verfügung.

## Lizenzbestimmungen
Das alleinige Eigentum an der Software verbleibt bei Car Gateway. Sie dürfen die Software
lediglich entsprechend der Lizenzbestimmungen von Car Gateway nutzen.