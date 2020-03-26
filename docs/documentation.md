# Dokumentation nach arc24

## Einführung und Ziele


### Aufgabenstellung
Für die Modulare Online Plattform für Studierende (MOPS) beschäftigt sich das Team TrappinInAuas mit dem Subsystem
"Korrektorinnenberwerbung". Dort wird ein Formular bereitgestellt, wo sich Studenten als Korrektor für ein Fach oder mehrer Fächer bewerben können. 
Der Dozent soll die Möglichkeit haben dem Bewerber eine Prioriät zu geben, bevor dann endgültig dem Studenten das Fach, indem Er oder Sie korrigieren wird, zugewiesen wird.

### Qualitätsziele
- einfach
- übersichtlich
- optisch ansprechend
- optimale Ansicht für mobile Endgeräte
- flexibel/anpassbar (Module hinzufügen/entfernen)
- schneller Bewerbungs- und Zuteilungsprozess
### Stakeholder

- Bewerber (Studenten und nicht-Studenten)
- Dozenten (Stellenausschreiber)
- verantwortliche Person zum zuordnen der Bewerbung
- verantwortliche Person zum erstellen eines Moduls

| Rolle      | Kontakt | Erwartungshaltung |
|:-----------|-------------:| -----:|
| Bewerber   |  |  |
| Dozenten   |  |  |
| Ersteller  |  |  |
| Zuordner   |  |  | 

## Randbedingungen
- Team aus 8 Personen
- 4 Wochen à 4 Stunden Arbeitszeit
- Jeweils 2 Tage Urlaub
- Beachtung des MOPS.STYLE Style-Sheet
- Einbettung von Key-Cloak
- Spring Framework, JPA, Postgress, DBeaver, Docker und PDFBOX von Apache

## Kontextabgrenzung


### Fachlicher Kontext
- fachliche Abgrenzung für die Persistenz der Daten (Informationen der Bewerber persistieren)

### Technischer Kontext

Das System soll als Self-contained-System in eine Verwaltungsplattform 
des Instituts für Informatik (MOPS) eingebunden sein. Zur vereinheitlichung
gibt es einen vorgegebenen [Styleguide](https://mops.style) sowie HTML-Templates.


### Lösungsstrategie
- Entwerfen eines ER-Modells
- Implementieren der Modellstruktur mit der Datenbank mithilfe des ER-Modells
- anhand von Modells DTOs ertellt und diese mit JPA Annoationen versehen
- Datenbank von Hibernate generieren lassen
- PDF-Service mit PDFBOX von Apache implementieren


## Entwurfsentscheidungen


- Schichtenarchitektur/ Zwibelarchitektur

## Qualitätsanforderungen 

- OOP
- Testbarkeit (nach AAA)
- Änderbarkeit (Module und Dozenten hinzufügen/entfernen)
- Effizienz (schnelles Bewerbungs- und Auswahlverfahren)
- Benutzbarkeit (verständlich und übersichtlich)
- Zuverlässigkeit und Funktionalität 
- Sicherheit (durch Key-Cloak)

## Risiken und technische Schulden


- Datenschutz
- Gleichzeitiges Abschicken von Bewerbungen
- fehlerhafte oder falsche Eingaben

## ADR template by Michael Nygard
### Radio-Buttons
#### Status
Wir wollten Radio-Buttons im Bewerbungsformular einbauen, wo sich ein Input-Textfeld öffnen nachdem man sie ausgewählt hat. Als Beispiel: "Sonstiges" auswählen und dann ins Textfeld die spezifische Antwort, falls diese nicht zum ankreuzen aufgelistet war.
#### Context
Das Problem ist, dass HTML-Form dieses Feature nicht so anbietet und man es mit Java-Script nur hinbekommt. Dort kommt ergibt sich aber dann das Problem, dass die Einbettung mit Thymeleaf dann unnötig kompliziert ist.
#### Decision
Wir haben uns entschieden, dieses schöne Feature weg zu lassen, damit wir keine Zeit mit der Implementierung verschwenden, falls vermutete auftauchen sollten.
#### Consequences
Das Design des Formular hat etwas dran gelitten, jedoch haben wir im Nachhinein eine Menge Zeit gewonnen.