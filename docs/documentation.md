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
#### Woche 1
In der ersten Woche hat man sich nach der großen groben Planung des Sub-Systems in kleinere Teams aufgeteilt (Front-end und Back-end), wo dann jeweils die spezifischen Details besprochen wurden. Für das Front-end haben sich Yousef, Marvin und Bayar zusammengesetzt. Zunächst sind wir in dieser Woche die alte Bewerbungs-PDF durchgegangen und haben dort gefiltert und ergänzt, wo es uns sinnvoll erschien. Unser Fokus lag dabei auf eine möglichst knappe, übersichtliche, aber dennoch detailierte und verständliche Bewerbungs-PDF, die wir unter "Vision_Bewerbungsbogen.pdf" gespeichert haben. Anschließend fingen wir direkt mit Coden an. Yousef hat sich zunächst um das Design gekümmert, Bayar und Marvin um das HTML-Gerüst. Wir fingen in dieser Woche mit dem Bewerbungsformular (main.html) an. Dort entstanden die ersten Probleme mit Radio-Input-Feldern. In unseren Vision sollte sich nach Anklicken einer Radio-Box ein Input-Textfeld öffnen, wo dann eine spezifische Antwort reingeschrieben werden könnte. Um dieses Problem zu lösen kam man nicht an Java-Script vorbei, wo man sich zunächst stark reinlesen musste. Nach viel googlen und nachfragen gelang es uns schließlich die Vision umzusetzen. Enttäuschenderweise wurde das Feature im Nachhinein rausgenommen, da es sich nicht so leicht mit Thymeleaf verknüpfen lies und deshalb sind wir aus Zeit-Gründen auf einfach Input-Textfelder umgestiegen. Zum Ende der Woche hatten wir jedenfalls ein vollständiges Gerüst der Hauptseite (Bewerbungsformular) inklusive Thymeleaf Einbettung, sowie erste CSS-Templates für ein sauberes und modernes Design.

#### Woche 2

#### Woche 3

#### Woche 4

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