# Dokumentation nach arc42

## Einführung und Ziele
Für jedes Semester werden in jeglichen Fächern Korrektoren und Tutoren gesucht. Die HHU hat den Bewerbungs- und Einstellungsprozess Jahre lang händisch mit Papier betrieben, wodurch ein nerviger Bearbeitungsprozess herschte. Aus vielen Ordnern und jede Menge Papierbögen, wird nun eine Datenbank mit digitalen Bewerbungen, dazu eine Webseite mit einem Online Formular, welches der Bewerber von überall ausfüllen kann und die physisch keinen Platz mehr einnimmt.
Das Team TrappinInAuas strebt ein praktisches und selbstverständliches Seitendesign an, welches durch seine Simplizität einen angenehmen und frustfreien Bewerbungsprozess ermöglicht. Dabei setzen wir auf ein gesundes Maß zwischen Ästhetik und Funktionalität, da jeder die Anwendung bedienen können soll, egal ob alt oder jung, egal ob am Handy oder am PC. Bei uns macht Bewerben Spaß!

### Aufgabenstellung/ Funktionalität

#### Setup Ansicht
- Kann Module erstellen, für die man sich bewerber kann
- Kann dem Modul einen Dozentennamen zuteilen, dazu die jeweilige E-mail und den Namen des Moduls
- Kann einzelne Module löschen oder alle auf einmal, welches praktisch zum Semesterbeginn ist
#### Bewerber Ansicht
- Kann während der Bewerbungsfrist das Bewerbungsformular ausfüllen
- Kann nach dem Abschicken des Bewerbungsformulars das Formular einsehen und editieren
- Kann sehen, ob zurzeit eine Bewerbungsphase läuft
- Kann das Formular nur richtig ausfüllen, da anderweilig das Formular nicht erfolgreich abgeschickt werden kann (Fehlermeldungen)
- Kriegt eine Bestätigung nach erfolgreichem Abschicken
#### Dozent Ansicht
- Kann alle Bewerbungen für sein(e) Modul(e) einsehen
- Kann jeder Bewerbung eine Priorität geben
- Kann bis zum Fristablauf seine gesetzten Prioritäten beliebig editieren
#### Verteiler Ansicht
- Kann die einzelnen Phasen des Bewerbungsprozesses setzen (Setup-, Bewerber-, Dozentphase)
- Kann alle Bewerbungen + die festgelegten Prioritäten der Dozenten sehen
- Kann dem Bewerber ein Modul zuordnen, worin er korrigieren wird
### Qualitätsziele
- einfach
- übersichtlich
- optisch ansprechend
- optimale Ansicht für mobile Endgeräte
- flexibel/anpassbar (Module hinzufügen/entfernen)
- schneller Bewerbungs- und Zuteilungsprozess
### Stakeholder


| Rolle        | Erwartungshaltung |
|:-------------|--------:|
| SetUp Person |Möchte Module, die im Semester angeboten werden, eintragen.       |
| Bewerber     |Würde sich gerne unkompliziert als Korrektor bewerben können.         |
| Dozenten     |Will für eine ideale Auswahl Bewerbungen priorisieren können.         |
| Verteiler    |Möchte eine gute Verteilung für alle Beteiligten erzeugen.        |

## Randbedingungen
- Team aus 8 Personen
- 4 Wochen à 4 Stunden Arbeitszeit
- Jeweils 2 Tage Urlaub
- Beachtung des MOPS.STYLE Style-Sheets
- Einbettung von Key-Cloak
- Spring Framework, JPA, Postgress, DBeaver, Docker und PDFBOX von Apache

### Technischer Kontext

Das System soll als Self-contained-System in eine Verwaltungsplattform 
des Instituts für Informatik (MOPS) eingebunden sein.
#### KeyCloak
Erlaubt es den Usern verschiedene Rollen zu geben, wodurch der Zugang zu einzelnen Features blockiert oder freigegeben wird.
#### Datenbank
Wir benutzen postgresql-Datenbank, weil die in der Übung genutzte MySQL nicht schön handzuhaben war. Da war die Nutzung der postgresql-DB intuitiver.
#### Docker
Um die komplette Umgebung als Anwendung zu verpacken und diese leicht ausführen zu können, habe wir Docker genutzt.
#### Bootstrap
Bootstrap wurde als Designsprache für die optische Gestaltung der Seiten genutzt. Das Augenmerk lag dabei auf eine minimalistische, dennoch futuristische optische Ansicht.
### Lösungsstrategie
- Entwerfen eines ER-Modells
- Implementieren der Modellstruktur mit der Datenbank mithilfe des ER-Modells
- anhand von Modells DTOs ertellt und diese mit JPA Annoationen versehen
- Datenbank von Hibernate generieren lassen
- PDF-Service mit PDFBOX von Apache implementieren


## Entwurfsentscheidungen


- Schichtenarchitektur/ Zwiebelarchitektur

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
