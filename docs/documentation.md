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


- Schichtenarchitektur

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
- 

## Frontend
### MarvinW1:
- Ich bin auf das erste große Problem gestoßen. Bei der Formular-Frage "Sind Sie derzeit an der HHU immatrikuliert?" gab es als Antwort-Möglichkeit "Ja, anderes Fach". Dort musste man eine Checkbox erstellen, die beim Anklicken ein Textfeld öffnet, damit dort der spezifische Studiengang hineingeschrieben werden konnte. HTML bietet zum einen die Methode mit dem type "radio", wo man nur einen Haken unter mehreren Optionen setzen kann, was mich nicht weiter brachte und dann den type "checkbox", das so ziemlich das selbe (kontextbezogen) wie radio ist, nur dass man mehrere Felder ankreuzen kann. Durch viel Googlen und viel Nachfragen kamen wir trotzdem nicht auf eine einfache Lösung, um den gewünschten Effekt zu erzielen. Nach vielem Nachdenken und Alternativen-Suchen, mussten wir den gewünschten Effekt letztendlich mittels Java Script programmieren, was aufgrund mangelnder Erfahrung sich als große Herausforderung herausstellte.
- Eine Woche später ist das Problem gelöst. Die Frage wurde so umformuliert, dass es nur noch ein Textfeld gibt, indem man das Fach als Text eingibt, dass man studiert. Durch Informationen, die ich von Jens erhalten habe, konnte ich den Code so anpassen, dass dort eine Tabelle erstellt wurde, wo man ausführliche Informationen brauchte und Dropdowns, wo man nicht alle Informationen braucht, damit man ein Platzsparendes und effektives Design hat. Mittlerweile ist die HTML Main Page fertig und Bootstrap wurde auch teilweise angewandt. Die Main Page wurde nun an LanPodder weitergeben, der die einzelnen Inputfelder des Formulars mit der Datenbank verknüpft.
### Tepri:
- Ich habe mich erstmals erkundigt, wie schöne Styles aussehen könnten und die Mops.style Seite begutachtet. Daran orientiert habe ich versucht schöne Dropdown-Menüs mit css selbst zu erstellen. Es viel mir zuerst nicht leicht eine css ordentlich zu schreiben, so dass das Dropdown-Menü einen gleichen Effekt erzielt beim Hovern wie bei der Mops.Style Seite. Zuerst musste ich mir eine Pseudo HTML-Struktur aufbauen mit Beispiel-Feldern für Eingabefeldern, Textfeldern und Radios. An der erstellten Pseudo-HTML-Datei habe ich dann meine Stylesheets angewandt und getestet. Durch einige Tutorials im Internet habe ich letzten endes ein einheitliches Design für Radios, Eingabefeldern und Textfelder erstellt, dies hat mehr Zeit eingenommen als zuvor erwartet war.
### bayarabd:
- Ich habe mich die zweite Woche mit Frontend beschäftigt und an ein paar Seiten gearbeitet. Eine Endseite, wohin man nach der Bewerbung weitergeleitet wird, eine Dozentseite, wo der Dozent die Anzahl der Bewerbungen und die Infos der BewerberInnen ansehen könnte. Ich habe zusätzlich eine Seite für Verteiler geschrieben, wo der Verteiler die Studentendaten und Prioritäten ansehen könnten und dann entscheiden welcher Student zu welchem fach gehört. Außerdem habe ich etwas an Formular gearbeitet; besonders ein paar Felder habe ich umstruktuiert, früher haben wir die Prioritäten in verschiedenen Zeilen geschrieben, aber ich die Radiobuttons in eine Tabelle zusammengebracht. Es wäre so meiner Meinung nach "benutzerfreundlicher". Außerdem habe ich mich für Bootstrap entschieden, statt eigene css zu schreiben, da mehr Möglichkeiten angeboten sind.

## Backend
### LanPodder

### AcnoZed

### heyoka95

### jhandke

### Mimbulus