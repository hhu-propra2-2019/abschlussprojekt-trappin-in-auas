# Dokumentation nach arc24

## Einführung und Ziele


### Aufgabenstellung

Für potentielle Korrektoren soll ein Bewerbungsportal erstellt werden.
Grundlegende Funktionalitäten sind die Datenspeicherung der Fragebögen sowie
teilautomatisierte Zuordnung zu den Dozenten.

### Qualitätsziele

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


## Kontextabgrenzung


### Fachlicher Kontext


**\<Diagramm und/oder Tabelle\>**

**\<optional: Erläuterung der externen fachlichen Schnittstellen\>**

### Technischer Kontext

Das System soll als Self-contained-System in eine Verwaltungsplattform 
des Instituts für Informatik (MOPS) eingebunden sein. Zur vereinheitlichung
gibt es einen vorgegebenen [Styleguide](https://mops.style) sowie HTML-Templates.


**\<Diagramm oder Tabelle\>**

**\<optional: Erläuterung der externen technischen Schnittstellen\>**

**\<Mapping fachliche auf technische Schnittstellen\>**

## Lösungsstrategie


## Bausteinsicht


### Whitebox Gesamtsystem


***\<Übersichtsdiagramm\>***

Begründung

:   *\<Erläuternder Text\>*

Enthaltene Bausteine

:   *\<Beschreibung der enthaltenen Bausteine (Blackboxen)\>*

Wichtige Schnittstellen

:   *\<Beschreibung wichtiger Schnittstellen\>*

### \<Name Blackbox 1\>

*\<Zweck/Verantwortung\>*

*\<Schnittstelle(n)\>*

*\<(Optional) Qualitäts-/Leistungsmerkmale\>*

*\<(Optional) Ablageort/Datei(en)\>*

*\<(Optional) Erfüllte Anforderungen\>*

*\<(optional) Offene Punkte/Probleme/Risiken\>*

### \<Name Blackbox 2\>

*\<Blackbox-Template\>*

### \<Name Blackbox n\>

*\<Blackbox-Template\>*

### \<Name Schnittstelle 1\>

...

### \<Name Schnittstelle m\>

Ebene 2 {#_ebene_2}
-------

### Whitebox *\<Baustein 1\>*

*\<Whitebox-Template\>*

### Whitebox *\<Baustein 2\>* 

*\<Whitebox-Template\>*

...

### Whitebox *\<Baustein m\>* 

*\<Whitebox-Template\>*

Ebene 3
-------

### Whitebox \<\_Baustein x.1\_\> 

*\<Whitebox-Template\>*

### Whitebox \<\_Baustein x.2\_\>

*\<Whitebox-Template\>*

### Whitebox \<\_Baustein y.1\_\> 

*\<Whitebox-Template\>*

## Laufzeitsicht


### *\<Bezeichnung Laufzeitszenario 1\>* 


-   \<hier Laufzeitdiagramm oder Ablaufbeschreibung einfügen\>

-   \<hier Besonderheiten bei dem Zusammenspiel der Bausteine in diesem
    Szenario erläutern\>

### *\<Bezeichnung Laufzeitszenario 2\>*


...

### *\<Bezeichnung Laufzeitszenario n\>*


...

## Verteilungssicht 


### Infrastruktur Ebene 1 


***\<Übersichtsdiagramm\>***

Begründung

:   *\<Erläuternder Text\>*

Qualitäts- und/oder Leistungsmerkmale

:   *\<Erläuternder Text\>*

Zuordnung von Bausteinen zu Infrastruktur

:   *\<Beschreibung der Zuordnung\>*

### Infrastruktur Ebene 2 


### *\<Infrastrukturelement 1\>* 

*\<Diagramm + Erläuterungen\>*

### *\<Infrastrukturelement 2\>*

*\<Diagramm + Erläuterungen\>*

...

### *\<Infrastrukturelement n\>*

*\<Diagramm + Erläuterungen\>*

## Querschnittliche Konzepte


#### *\<Konzept 1\>*


*\<Erklärung\>*

####*\<Konzept 2\>*


*\<Erklärung\>*

...

#### *\<Konzept n\>* 


*\<Erklärung\>*

## Entwurfsentscheidungen


- Schichtenarchitektur

## Qualitätsanforderungen 


- OOP
- Testbarkeit
- Modularität von Modulen und Dozenten

### Qualitätsbaum 


### Qualitätsszenarien 


## Risiken und technische Schulden


- Datenschutz!
- 

## Glossar


| Begriff        | Definition    |
|:---------------|-------------:|
| Fragebogen     | Eine Art Einstellungsbogen, in dem persönliche Daten abgefragen werden |
| Begriff 2      | Erläuterung 2      |

## Frontend
### MarvinW1:
- Ich bin auf das erste große Problem gestoßen. Bei der Formular-Frage "Sind Sie derzeit an der HHU immatrikuliert?" gab es als Antwort-Möglichkeit "Ja, anderes Fach". Dort musste man eine Checkbox erstellen, die beim Anklicken ein Textfeld öffnet, damit dort der spezifische Studiengang hineingeschrieben werden konnte. HTML bietet zum einen die Methode mit dem type "radio", wo man nur einen Haken unter mehreren Optionen setzen kann, was mich nicht weiter brachte und dann den type "checkbox", das so ziemlich das selbe (kontextbezogen) wie radio ist, nur dass man mehrere Felder ankreuzen kann. Durch viel Googlen und viel Nachfragen kamen wir trotzdem nicht auf eine einfache Lösung, um den gewünschten Effekt zu erzielen. Nach vielem Nachdenken und Alternativen-Suchen, mussten wir den gewünschten Effekt letztendlich mittels Java Script programmieren, was aufgrund mangelnder Erfahrung sich als große Herausforderung herausstellte.
- Eine Woche später ist das Problem gelöst. Die Frage wurde so umformuliert, dass es nur noch ein Textfeld gibt, indem man das Fach als Text eingibt, dass man studiert. Durch Informationen, die ich von Jens erhalten habe, konnte ich den Code so anpassen, dass dort eine Tabelle erstellt wurde, wo man ausführliche Informationen brauchte und Dropdowns, wo man nicht alle Informationen braucht, damit man ein Platzsparendes und effektives Design hat. Mittlerweile ist die HTML Main Page fertig und Bootstrap wurde auch teilweise angewandt. Die Main Page wurde nun an LanPodder weitergeben, der die einzelnen Inputfelder des Formulars mit der Datenbank verknüpft.
### Tepri:
- Ich habe mich erstmals erkundigt, wie schöne Styles aussehen könnten und die Mops.style Seite begutachtet. Daran orientiert habe ich versucht schöne Dropdown-Menüs mit css selbst zu erstellen. Es viel mir zuerst nicht leicht eine css ordentlich zu schreiben, so dass das Dropdown-Menü einen gleichen Effekt erzielt beim Hovern wie bei der Mops.Style Seite. Zuerst musste ich mir eine Pseudo HTML-Struktur aufbauen mit Beispiel-Feldern für Eingabefeldern, Textfeldern und Radios. An der erstellten Pseudo-HTML-Datei habe ich dann meine Stylesheets angewandt und getestet. Durch einige Tutorials im Internet habe ich letzten endes ein einheitliches Design für Radios, Eingabefeldern und Textfelder erstellt, dies hat mehr Zeit eingenommen als zuvor erwartet war.






>>>>>>> c311cf1db8411cef4808b15c735e5b6747027967
