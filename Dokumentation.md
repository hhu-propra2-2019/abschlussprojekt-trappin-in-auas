# Dokumentation nach arc24

## Einführung und Ziele
Wir haben die DB Modellierung und unser Ziel ist die Persistenz der einzelnen Bewerbern. 


### Aufgabenstellung

Für potentielle Korrektoren soll ein Bewerbungsportal erstellt werden.
Grundlegende Funktionalitäten sind die Datenspeicherung der Fragebögen sowie
teilautomatisierte Zuordnung zu den Dozenten.

### Qualitätsziele
Unsere Qualitätsziele sind Wartbarkeit, Effizient und Anpassungsfäigkeit, denn in illias haben wir das Problem, dass der Bewerbungsprozess sehr langsam dauert und unser Ziel ist es die DB und den Code effizient wie möglich zu gestalten, als hilfe verwenden Clean Code und SRP Prinzip.

### Stakeholder

- Bewerber (Studenten und nicht-Studenten)
- Dozenten (Stellenausschreiber)
- verantwortliche Person zum zuordnen der Bewerbung

| Rolle      | Kontakt | Erwartungshaltung |
|:-----------|-------------:| -----:|
| Bewerber   |  |  |
| Dozenten   |  |  |
| Verwaltung |  |  |

## Randbedingungen
- Spring Framework, JPA, Postgress, DBWeaver und Docker 

- Zeitlicher Rahmen: 1 Monat jeweils 4 arbeitstunden taeglich

- heyoka95 (Akin) und AcnoZed(Oussama) zuständig für Db Modelierung und herstellen der DB  zur Persistenz der Bewerber.


## Kontextabgrenzung


### Fachlicher Kontext
- beim fachlicher Abgrenzung ist unser Bereich wichtig für die Persistenz der Daten, denn es ist wichtig dass der die Bewerber und vorallem deren Informationen persistiert werden. 



## Lösungsstrategie
Entwerfen eines ER Modells, Implementieren des Er Modells in java und per JPA Annotationen ausführen mit docker compose der Datenbank, umso eine DB von Hibernate generieren zu lassen.






## Entwurfsentscheidungen


- Schichtenarchitektur

## Qualitätsanforderungen 


- OOP
- Testbarkeit
- Modularität von Modulen und Dozenten
- Schneligkeit



## Risiken und technische Schulden


- Datenschutz!
- Langsam
- Probleme beim Starten der Datenbank



