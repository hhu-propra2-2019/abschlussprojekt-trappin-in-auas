**Über arc42**

arc42, das Template zur Dokumentation von Software- und
Systemarchitekturen.

Erstellt von Dr. Gernot Starke, Dr. Peter Hruschka und Mitwirkenden.

Template Revision: 7.0 DE (asciidoc-based), January 2017

© We acknowledge that this document uses material from the arc42
architecture template, <http://www.arc42.de>. Created by Dr. Peter
Hruschka & Dr. Gernot Starke.

> **Note**
>
> Diese Version des Templates enthält Hilfen und Erläuterungen. Sie
> dient der Einarbeitung in arc42 sowie dem Verständnis der Konzepte.
> Für die Dokumentation eigener System verwenden Sie besser die *plain*
> Version.

Einführung und Ziele {#section-introduction-and-goals}
====================

Beschreibt die wesentlichen Anforderungen und treibenden Kräfte, die bei
der Umsetzung der Softwarearchitektur und Entwicklung des Systems
berücksichtigt werden müssen.

Dazu gehören:

-   zugrunde liegende Geschäftsziele,

-   wesentliche Aufgabenstellungen und

-   essenzielle fachliche Anforderungen an das System sowie

-   Qualitätsziele für die Architektur und

-   relevante Stakeholder und deren Erwartungshaltung.

Aufgabenstellung {#_aufgabenstellung}
----------------

**Inhalt.**

Kurzbeschreibung der fachlichen Aufgabenstellung, treibenden Kräfte,
Extrakt (oder Abstract) der Anforderungen. Verweis auf (hoffentlich
vorliegende) Anforderungsdokumente (mit Versionsbezeichnungen und
Ablageorten).

**Motivation.**

Aus Sicht der späteren Nutzung ist die Unterstützung einer fachlichen
Aufgabe oder Verbesserung der Qualität der eigentliche Beweggrund, ein
neues System zu schaffen oder ein bestehendes zu modifizieren.

**Form.**

Kurze textuelle Beschreibung, eventuell in tabellarischer Use-Case Form.
Sofern vorhanden, sollte die Aufgabenstellung Verweise auf die
entsprechenden Anforderungsdokumente enthalten.

Halten Sie diese Auszüge so knapp wie möglich und wägen Sie Lesbarkeit
und Redundanzfreiheit gegeneinander ab.

Qualitätsziele {#_qualit_tsziele}
--------------

**Inhalt.**

Die Top-3 bis Top-5 der Qualitätsziele für die Architektur, deren
Erfüllung oder Einhaltung den maßgeblichen Stakeholdern besonders
wichtig sind. Gemeint sind hier wirklich Qualitätsziele, die nicht
unbedingt mit den Zielen des Projekts übereinstimmen. Beachten Sie den
Unterschied.

**Motivation.**

Weil Qualitätsziele grundlegende Architekturentscheidungen oft
maßgeblich beeinflussen, sollten Sie die für Ihre Stakeholder relevanten
Qualitätsziele kennen, möglichst konkret und operationalisierbar.

**Form.**

Tabellarische Darstellung der Qualitätsziele mit möglichst konkreten
Szenarien, geordnet nach Prioritäten.

Stakeholder {#_stakeholder}
-----------

**Inhalt.**

Expliziter Überblick über die Stakeholder des Systems -- über alle
Personen, Rollen oder Organisationen --, die

-   die Architektur kennen sollten oder

-   von der Architektur überzeugt werden müssen,

-   mit der Architektur oder dem Code arbeiten (z.B. Schnittstellen
    nutzen),

-   die Dokumentation der Architektur für ihre eigene Arbeit benötigen,

-   Entscheidungen über das System und dessen Entwicklung treffen.

**Motivation.**

Sie sollten die Projektbeteiligten und -betroffenen kennen, sonst
erleben Sie später im Entwicklungsprozess Überraschungen. Diese
Stakeholder bestimmen unter anderem Umfang und Detaillierungsgrad der
von Ihnen zu leistenden Arbeit und Ergebnisse.

**Form.**

Tabelle mit Rollen- oder Personennamen, sowie deren Erwartungshaltung
bezüglich der Architektur und deren Dokumentation.

+-----------------+-----------------+-----------------------------------+
| Rolle           | Kontakt         | Erwartungshaltung                 |
+=================+=================+===================================+
| *\<Rolle-1\>*   | *\<Kontakt-1\>* | *\<Erwartung-1\>*                 |
+-----------------+-----------------+-----------------------------------+
| *\<Rolle-2\>*   | *\<Kontakt-2\>* | *\<Erwartung-2\>*                 |
+-----------------+-----------------+-----------------------------------+

Randbedingungen {#section-architecture-constraints}
===============

**Inhalt.**

Randbedingungen und Vorgaben, die ihre Freiheiten bezüglich Entwurf,
Implementierung oder Ihres Entwicklungsprozesses einschränken. Diese
Randbedingungen gelten manchmal organisations- oder firmenweit über die
Grenzen einzelner Systeme hinweg.

**Motivation.**

Für eine tragfähige Architektur sollten Sie genau wissen, wo Ihre
Freiheitsgrade bezüglich der Entwurfsentscheidungen liegen und wo Sie
Randbedingungen beachten müssen. Sie können Randbedingungen vielleicht
noch verhandeln, zunächst sind sie aber da.

**Form.**

Einfache Tabellen der Randbedingungen mit Erläuterungen. Bei Bedarf
unterscheiden Sie technische, organisatorische und politische
Randbedingungen oder übergreifende Konventionen (beispielsweise
Programmier- oder Versionierungsrichtlinien, Dokumentations- oder
Namenskonvention).

Kontextabgrenzung {#section-system-scope-and-context}
=================

**Inhalt.**

Die Kontextabgrenzung grenzt das System von allen
Kommunikationsbeziehungen (Nachbarsystemen und Benutzerrollen) ab. Sie
legt damit die externen Schnittstellen fest.

Differenzieren Sie fachliche (fachliche Ein- und Ausgaben) und
technische Kontexte (Kanäle, Protokolle, Hardware), falls nötig.

**Motivation.**

Die fachlichen und technischen Schnittstellen zur Kommunikation gehören
zu den kritischsten Aspekten eines Systems. Stellen Sie sicher, dass Sie
diese komplett verstanden haben.

**Form.**

Verschiedene Optionen:

-   Diverse Kontextdiagramme

-   Listen von Kommunikationsbeziehungen mit deren Schnittstellen

Fachlicher Kontext {#_fachlicher_kontext}
------------------

**Inhalt.**

Festlegung **aller** Kommunikationsbeziehungen (Nutzer, IT-Systeme, ...)
mit Erklärung der fachlichen Ein- und Ausgabedaten oder Schnittstellen.
Zusätzlich (bei Bedarf) fachliche Datenformate oder Protokolle der
Kommunikation mit den Nachbarsystemen.

**Motivation.**

Alle Beteiligten müssen verstehen, welche fachlichen Informationen mit
der Umwelt ausgetauscht werden.

**Form.**

Alle Diagrammarten, die das System als Blackbox darstellen und die
fachlichen Schnittstellen zu den Nachbarsystemen beschreiben.

Alternativ oder ergänzend können Sie eine Tabelle verwenden. Der Titel
gibt den Namen Ihres Systems wieder; die drei Spalten sind:
Kommunikationsbeziehung, Eingabe, Ausgabe.

**\<Diagramm und/oder Tabelle\>**

**\<optional: Erläuterung der externen fachlichen Schnittstellen\>**

Technischer Kontext {#_technischer_kontext}
-------------------

**Inhalt.**

Technische Schnittstellen (Kanäle, Übertragungsmedien) zwischen dem
System und seiner Umwelt. Zusätzlich eine Erklärung (*mapping*), welche
fachlichen Ein- und Ausgaben über welche technischen Kanäle fließen.

**Motivation.**

Viele Stakeholder treffen Architekturentscheidungen auf Basis der
technischen Schnittstellen des Systems zu seinem Kontext.

Insbesondere bei der Entwicklung von Infrastruktur oder Hardware sind
diese technischen Schnittstellen durchaus entscheidend.

**Form.**

Beispielsweise UML Deployment-Diagramme mit den Kanälen zu
Nachbarsystemen, begleitet von einer Tabelle, die Kanäle auf
Ein-/Ausgaben abbildet.

**\<Diagramm oder Tabelle\>**

**\<optional: Erläuterung der externen technischen Schnittstellen\>**

**\<Mapping fachliche auf technische Schnittstellen\>**

Lösungsstrategie {#section-solution-strategy}
================

**Inhalt.**

Kurzer Überblick über die grundlegenden Entscheidungen und
Lösungsansätze, die Entwurf und Implementierung des Systems prägen.
Hierzu gehören:

-   Technologieentscheidungen

-   Entscheidungen über die Top-Level-Zerlegung des Systems,
    beispielsweise die Verwendung gesamthaft prägender Entwurfs- oder
    Architekturmuster,

-   Entscheidungen zur Erreichung der wichtigsten Qualitätsanforderungen
    sowie

-   relevante organisatorische Entscheidungen, beispielsweise für
    bestimmte Entwicklungsprozesse oder Delegation bestimmter Aufgaben
    an andere Stakeholder.

**Motivation.**

Diese wichtigen Entscheidungen bilden wesentliche „Eckpfeiler" der
Architektur. Von ihnen hängen viele weitere Entscheidungen oder
Implementierungsregeln ab.

**Form.**

Fassen Sie die zentralen Entwurfsentscheidungen **kurz** zusammen.
Motivieren Sie, ausgehend von Aufgabenstellung, Qualitätszielen und
Randbedingungen, was Sie entschieden haben und warum Sie so entschieden
haben. Vermeiden Sie redundante Beschreibungen und verweisen Sie eher
auf weitere Ausführungen in Folgeabschnitten.

Bausteinsicht {#section-building-block-view}
=============

**Inhalt.**

Diese Sicht zeigt die statische Zerlegung des Systems in Bausteine sowie
deren Beziehungen. Beispiele für Bausteine sind unter anderem:

-   Module

-   Komponenten

-   Subsysteme

-   Klassen

-   Interfaces

-   Pakete

-   Bibliotheken

-   Frameworks

-   Schichten

-   Partitionen

-   Tiers

-   Funktionen

-   Makros

-   Operationen

-   Datenstrukturen

-   ...

Diese Sicht sollte in jeder Architekturdokumentation vorhanden sein. In
der Analogie zum Hausbau bildet die Bausteinsicht den *Grundrissplan*.

**Motivation.**

Behalten Sie den Überblick über den Quellcode, indem Sie die statische
Struktur des Systems durch Abstraktion verständlich machen.

Damit ermöglichen Sie Kommunikation auf abstrakterer Ebene, ohne zu
viele Implementierungsdetails offenlegen zu müssen.

**Form.**

Die Bausteinsicht ist eine hierarchische Sammlung von Blackboxen und
Whiteboxen (siehe Abbildung unten) und deren Beschreibungen.

![Baustein Sichten](images/05_building_blocks-DE.png)

**Ebene 1** ist die Whitebox-Beschreibung des Gesamtsystems, zusammen
mit Blackbox-Beschreibungen der darin enthaltenen Bausteine.

**Ebene 2** zoomt in einige Bausteine der Ebene 1 hinein. Sie enthält
somit die Whitebox-Beschreibungen ausgewählter Bausteine der Ebene 1,
jeweils zusammen mit Blackbox-Beschreibungen darin enthaltener
Bausteine.

**Ebene 3** zoomt in einige Bausteine der Ebene 2 hinein, usw.

Whitebox Gesamtsystem {#_whitebox_gesamtsystem}
---------------------

An dieser Stelle beschreiben Sie die Zerlegung des Gesamtsystems anhand
des nachfolgenden Whitebox-Templates. Dieses enthält:

-   Ein Übersichtsdiagramm

-   die Begründung dieser Zerlegung

-   Blackbox-Beschreibungen der hier enthaltenen Bausteine. Dafür haben
    Sie verschiedene Optionen:

    -   in *einer* Tabelle, gibt einen kurzen und pragmatischen
        Überblick über die enthaltenen Bausteine sowie deren
        Schnittstellen.

    -   als Liste von Blackbox-Beschreibungen der Bausteine, gemäß dem
        Blackbox-Template (siehe unten). Diese Liste können Sie, je nach
        Werkzeug, etwa in Form von Unterkapiteln (Text), Unter-Seiten
        (Wiki) oder geschachtelten Elementen (Modellierungswerkzeug)
        darstellen.

-   (optional:) wichtige Schnittstellen, die nicht bereits im
    Blackbox-Template eines der Bausteine erläutert werden, aber für das
    Verständnis der Whitebox von zentraler Bedeutung sind. Aufgrund der
    vielfältigen Möglichkeiten oder Ausprägungen von Schnittstellen
    geben wir hierzu kein weiteres Template vor. Im schlimmsten Fall
    müssen Sie Syntax, Semantik, Protokolle, Fehlerverhalten,
    Restriktionen, Versionen, Qualitätseigenschaften, notwendige
    Kompatibilitäten und vieles mehr spezifizieren oder beschreiben. Im
    besten Fall kommen Sie mit Beispielen oder einfachen Signaturen
    zurecht.

***\<Übersichtsdiagramm\>***

Begründung

:   *\<Erläuternder Text\>*

Enthaltene Bausteine

:   *\<Beschreibung der enthaltenen Bausteine (Blackboxen)\>*

Wichtige Schnittstellen

:   *\<Beschreibung wichtiger Schnittstellen\>*

Hier folgen jetzt Erläuterungen zu Blackboxen der Ebene 1.

Falls Sie die tabellarische Beschreibung wählen, so werden Blackboxen
darin nur mit Name und Verantwortung nach folgendem Muster beschrieben:

+-----------------------+-----------------------------------------------+
| **Name**              | **Verantwortung**                             |
+=======================+===============================================+
| *\<Blackbox 1\>*      |  *\<Text\>*                                   |
+-----------------------+-----------------------------------------------+
| *\<Blackbox 2\>*      |  *\<Text\>*                                   |
+-----------------------+-----------------------------------------------+

Falls Sie die ausführliche Liste von Blackbox-Beschreibungen wählen,
beschreiben Sie jede wichtige Blackbox in einem eigenen
Blackbox-Template. Dessen Überschrift ist jeweils der Namen dieser
Blackbox.

### \<Name Blackbox 1\> {#__name_blackbox_1}

Beschreiben Sie die \<Blackbox 1\> anhand des folgenden
Blackbox-Templates:

-   Zweck/Verantwortung

-   Schnittstelle(n), sofern diese nicht als eigenständige
    Beschreibungen herausgezogen sind. Hierzu gehören eventuell auch
    Qualitäts- und Leistungsmerkmale dieser Schnittstelle.

-   (Optional) Qualitäts-/Leistungsmerkmale der Blackbox, beispielsweise
    Verfügbarkeit, Laufzeitverhalten o. Ä.

-   (Optional) Ablageort/Datei(en)

-   (Optional) Erfüllte Anforderungen, falls Sie Traceability zu
    Anforderungen benötigen.

-   (Optional) Offene Punkte/Probleme/Risiken

*\<Zweck/Verantwortung\>*

*\<Schnittstelle(n)\>*

*\<(Optional) Qualitäts-/Leistungsmerkmale\>*

*\<(Optional) Ablageort/Datei(en)\>*

*\<(Optional) Erfüllte Anforderungen\>*

*\<(optional) Offene Punkte/Probleme/Risiken\>*

### \<Name Blackbox 2\> {#__name_blackbox_2}

*\<Blackbox-Template\>*

### \<Name Blackbox n\> {#__name_blackbox_n}

*\<Blackbox-Template\>*

### \<Name Schnittstelle 1\> {#__name_schnittstelle_1}

...

### \<Name Schnittstelle m\> {#__name_schnittstelle_m}

Ebene 2 {#_ebene_2}
-------

Beschreiben Sie den inneren Aufbau (einiger) Bausteine aus Ebene 1 als
Whitebox.

Welche Bausteine Ihres Systems Sie hier beschreiben, müssen Sie selbst
entscheiden. Bitte stellen Sie dabei Relevanz vor Vollständigkeit.
Skizzieren Sie wichtige, überraschende, riskante, komplexe oder
besonders volatile Bausteine. Normale, einfache oder standardisierte
Teile sollten Sie weglassen.

### Whitebox *\<Baustein 1\>* {#_whitebox_emphasis_baustein_1_emphasis}

...zeigt das Innenleben von *Baustein 1*.

*\<Whitebox-Template\>*

### Whitebox *\<Baustein 2\>* {#_whitebox_emphasis_baustein_2_emphasis}

*\<Whitebox-Template\>*

...

### Whitebox *\<Baustein m\>* {#_whitebox_emphasis_baustein_m_emphasis}

*\<Whitebox-Template\>*

Ebene 3 {#_ebene_3}
-------

Beschreiben Sie den inneren Aufbau (einiger) Bausteine aus Ebene 2 als
Whitebox.

Bei tieferen Gliederungen der Architektur kopieren Sie diesen Teil von
arc42 für die weiteren Ebenen.

### Whitebox \<\_Baustein x.1\_\> {#_whitebox_baustein_x_1}

...zeigt das Innenleben von *Baustein x.1*.

*\<Whitebox-Template\>*

### Whitebox \<\_Baustein x.2\_\> {#_whitebox_baustein_x_2}

*\<Whitebox-Template\>*

### Whitebox \<\_Baustein y.1\_\> {#_whitebox_baustein_y_1}

*\<Whitebox-Template\>*

Laufzeitsicht {#section-runtime-view}
=============

**Inhalt.**

Diese Sicht erklärt konkrete Abläufe und Beziehungen zwischen Bausteinen
in Form von Szenarien aus den folgenden Bereichen:

-   Wichtige Abläufe oder *Features*: Wie führen die Bausteine der
    Architektur die wichtigsten Abläufe durch?

-   Interaktionen an kritischen externen Schnittstellen: Wie arbeiten
    Bausteine mit Nutzern und Nachbarsystemen zusammen?

-   Betrieb und Administration: Inbetriebnahme, Start, Stop.

-   Fehler- und Ausnahmeszenarien

Anmerkung: Das Kriterium für die Auswahl der möglichen Szenarien (d.h.
Abläufe) des Systems ist deren Architekturrelevanz. Es geht nicht darum,
möglichst viele Abläufe darzustellen, sondern eine angemessene Auswahl
zu dokumentieren.

**Motivation.**

Sie sollten verstehen, wie (Instanzen von) Bausteine(n) Ihres Systems
ihre jeweiligen Aufgaben erfüllen und zur Laufzeit miteinander
kommunizieren.

Nutzen Sie diese Szenarien in der Dokumentation hauptsächlich für eine
verständlichere Kommunikation mit denjenigen Stakeholdern, die die
statischen Modelle (z.B. Bausteinsicht, Verteilungssicht) weniger
verständlich finden.

**Form.**

Für die Beschreibung von Szenarien gibt es zahlreiche
Ausdrucksmöglichkeiten. Nutzen Sie beispielsweise:

-   Nummerierte Schrittfolgen oder Aufzählungen in Umgangssprache

-   Aktivitäts- oder Flussdiagramme

-   Sequenzdiagramme

-   BPMN (Geschäftsprozessmodell und -notation) oder EPKs
    (Ereignis-Prozessketten)

-   Zustandsautomaten

-   ...

*\<Bezeichnung Laufzeitszenario 1\>* {#__emphasis_bezeichnung_laufzeitszenario_1_emphasis}
------------------------------------

-   \<hier Laufzeitdiagramm oder Ablaufbeschreibung einfügen\>

-   \<hier Besonderheiten bei dem Zusammenspiel der Bausteine in diesem
    Szenario erläutern\>

*\<Bezeichnung Laufzeitszenario 2\>* {#__emphasis_bezeichnung_laufzeitszenario_2_emphasis}
------------------------------------

...

*\<Bezeichnung Laufzeitszenario n\>* {#__emphasis_bezeichnung_laufzeitszenario_n_emphasis}
------------------------------------

...

Verteilungssicht {#section-deployment-view}
================

**Inhalt.**

Die Verteilungssicht beschreibt:

1.  die technische Infrastruktur, auf der Ihr System ausgeführt wird,
    mit Infrastrukturelementen wie Standorten, Umgebungen, Rechnern,
    Prozessoren, Kanälen und Netztopologien sowie sonstigen
    Bestandteilen, und

2.  die Abbildung von (Software-)Bausteinen auf diese Infrastruktur.

Häufig laufen Systeme in unterschiedlichen Umgebungen, beispielsweise
Entwicklung-/Test- oder Produktionsumgebungen. In solchen Fällen sollten
Sie alle relevanten Umgebungen aufzeigen.

Nutzen Sie die Verteilungssicht insbesondere dann, wenn Ihre Software
auf mehr als einem Rechner, Prozessor, Server oder Container abläuft
oder Sie Ihre Hardware sogar selbst konstruieren.

Aus Softwaresicht genügt es, auf die Aspekte zu achten, die für die
Softwareverteilung relevant sind. Insbesondere bei der
Hardwareentwicklung kann es notwendig sein, die Infrastruktur mit
beliebigen Details zu beschreiben.

**Motivation.**

Software läuft nicht ohne Infrastruktur. Diese zugrundeliegende
Infrastruktur beeinflusst Ihr System und/oder querschnittliche
Lösungskonzepte, daher müssen Sie diese Infrastruktur kennen.

**Form.**

Das oberste Verteilungsdiagramm könnte bereits in Ihrem technischen
Kontext enthalten sein, mit Ihrer Infrastruktur als EINE Blackbox. Jetzt
zoomen Sie in diese Infrastruktur mit weiteren Verteilungsdiagrammen
hinein:

-   Die UML stellt mit Verteilungsdiagrammen (Deployment diagrams) eine
    Diagrammart zur Verfügung, um diese Sicht auszudrücken. Nutzen Sie
    diese, evtl. auch geschachtelt, wenn Ihre Verteilungsstruktur es
    verlangt.

-   Falls Ihre Infrastruktur-Stakeholder andere Diagrammarten
    bevorzugen, die beispielsweise Prozessoren und Kanäle zeigen, sind
    diese hier ebenfalls einsetzbar.

Infrastruktur Ebene 1 {#_infrastruktur_ebene_1}
---------------------

An dieser Stelle beschreiben Sie (als Kombination von Diagrammen mit
Tabellen oder Texten):

-   die Verteilung des Gesamtsystems auf mehrere Standorte, Umgebungen,
    Rechner, Prozessoren o. Ä., sowie die physischen Verbindungskanäle
    zwischen diesen,

-   wichtige Begründungen für diese Verteilungsstruktur,

-   Qualitäts- und/oder Leistungsmerkmale dieser Infrastruktur,

-   Zuordnung von Softwareartefakten zu Bestandteilen der Infrastruktur

Für mehrere Umgebungen oder alternative Deployments kopieren Sie diesen
Teil von arc42 für alle wichtigen Umgebungen/Varianten.

***\<Übersichtsdiagramm\>***

Begründung

:   *\<Erläuternder Text\>*

Qualitäts- und/oder Leistungsmerkmale

:   *\<Erläuternder Text\>*

Zuordnung von Bausteinen zu Infrastruktur

:   *\<Beschreibung der Zuordnung\>*

Infrastruktur Ebene 2 {#_infrastruktur_ebene_2}
---------------------

An dieser Stelle können Sie den inneren Aufbau (einiger)
Infrastrukturelemente aus Ebene 1 beschreiben.

Für jedes Infrastrukturelement kopieren Sie die Struktur aus Ebene 1.

### *\<Infrastrukturelement 1\>* {#__emphasis_infrastrukturelement_1_emphasis}

*\<Diagramm + Erläuterungen\>*

### *\<Infrastrukturelement 2\>* {#__emphasis_infrastrukturelement_2_emphasis}

*\<Diagramm + Erläuterungen\>*

...

### *\<Infrastrukturelement n\>* {#__emphasis_infrastrukturelement_n_emphasis}

*\<Diagramm + Erläuterungen\>*

Querschnittliche Konzepte {#section-concepts}
=========================

**Inhalt.**

Dieser Abschnitt beschreibt übergreifende, prinzipielle Regelungen und
Lösungsansätze, die an mehreren Stellen (=*querschittlich*) relevant
sind.

Solche Konzepte betreffen oft mehrere Bausteine. Dazu können vielerlei
Themen gehören, beispielsweise:

-   fachliche Modelle,

-   eingesetzte Architektur- oder Entwurfsmuster,

-   Regeln für den konkreten Einsatz von Technologien,

-   prinzipielle --- meist technische --- Festlegungen übergreifender
    Art,

-   Implementierungsregeln

**Motivation.**

Konzepte bilden die Grundlage für *konzeptionelle Integrität*
(Konsistenz, Homogenität) der Architektur und damit eine wesentliche
Grundlage für die innere Qualität Ihrer Systeme.

Manche dieser Themen lassen sich nur schwer als Baustein in der
Architektur unterbringen (z.B. das Thema „Sicherheit"). Hier ist der
Platz im Template, wo Sie derartige Themen geschlossen behandeln können.

**Form.**

Kann vielfältig sein:

-   Konzeptpapiere mit beliebiger Gliederung,

-   übergreifende Modelle/Szenarien mit Notationen, die Sie auch in den
    Architektursichten nutzen,

-   beispielhafte Implementierung speziell für technische Konzepte,

-   Verweise auf „übliche" Nutzung von Standard-Frameworks
    (beispielsweise die Nutzung von Hibernate als Object/Relational
    Mapper).

**Struktur.**

Eine mögliche (nicht aber notwendige!) Untergliederung dieses
Abschnittes könnte wie folgt aussehen (wobei die Zuordnung von Themen zu
den Gruppen nicht immer eindeutig ist):

-   Fachliche Konzepte

-   User Experience (UX)

-   Sicherheitskonzepte (Safety und Security)

-   Architektur- und Entwurfsmuster

-   Unter-der-Haube

-   Entwicklungskonzepte

-   Betriebskonzepte

![Possible topics for crosscutting
concepts](images/08-Crosscutting-Concepts-Structure-DE.png)

*\<Konzept 1\>* {#__emphasis_konzept_1_emphasis}
---------------

*\<Erklärung\>*

*\<Konzept 2\>* {#__emphasis_konzept_2_emphasis}
---------------

*\<Erklärung\>*

...

*\<Konzept n\>* {#__emphasis_konzept_n_emphasis}
---------------

*\<Erklärung\>*

Entwurfsentscheidungen {#section-design-decisions}
======================

**Inhalt.**

Wichtige, teure, große oder riskante Architektur- oder
Entwurfsentscheidungen inklusive der jeweiligen Begründungen. Mit
\"Entscheidungen\" meinen wir hier die Auswahl einer von mehreren
Alternativen unter vorgegebenen Kriterien.

Wägen Sie ab, inwiefern Sie Entscheidungen hier zentral beschreiben,
oder wo eine lokale Beschreibung (z.B. in der Whitebox-Sicht von
Bausteinen) sinnvoller ist. Vermeiden Sie Redundanz. Verweisen Sie evtl.
auf Abschnitt 4, wo schon grundlegende strategische Entscheidungen
beschrieben wurden.

**Motivation.**

Stakeholder des Systems sollten wichtige Entscheidungen verstehen und
nachvollziehen können.

**Form.**

Verschiedene Möglichkeiten:

-   Liste oder Tabelle, nach Wichtigkeit und Tragweite der
    Entscheidungen geordnet

-   ausführlicher in Form einzelner Unterkapitel je Entscheidung

-   ADR ([Architecture Decision
    Record](http://thinkrelevance.com/blog/2011/11/15/documenting-architecture-decisions))
    für jede wichtige Entscheidung

Qualitätsanforderungen {#section-quality-scenarios}
======================

**Inhalt.**

Dieser Abschnitt enthält möglichst alle Qualitätsanforderungen als
Qualitätsbaum mit Szenarien. Die wichtigsten davon haben Sie bereits in
Abschnitt 1.2 (Qualitätsziele) hervorgehoben.

Nehmen Sie hier auch Qualitätsanforderungen geringerer Priorität auf,
deren Nichteinhaltung oder -erreichung geringe Risiken birgt.

**Motivation.**

Weil Qualitätsanforderungen die Architekturentscheidungen oft maßgeblich
beeinflussen, sollten Sie die für Ihre Stakeholder relevanten
Qualitätsanforderungen kennen, möglichst konkret und operationalisiert.

Qualitätsbaum {#_qualit_tsbaum}
-------------

**Inhalt.**

Der Qualitätsbaum (à la ATAM) mit Qualitätsszenarien an den Blättern.

**Motivation.**

Die mit Prioritäten versehene Baumstruktur gibt Überblick über
die --- oftmals zahlreichen --- Qualitätsanforderungen.

-   Baumartige Verfeinerung des Begriffes „Qualität", mit „Qualität"
    oder „Nützlichkeit" als Wurzel.

-   Mindmap mit Qualitätsoberbegriffen als Hauptzweige

In jedem Fall sollten Sie hier Verweise auf die Qualitätsszenarien des
folgenden Abschnittes aufnehmen.

Qualitätsszenarien {#_qualit_tsszenarien}
------------------

**Inhalt.**

Konkretisierung der (in der Praxis oftmals vagen oder impliziten)
Qualitätsanforderungen durch (Qualitäts-)Szenarien.

Diese Szenarien beschreiben, was beim Eintreffen eines Stimulus auf ein
System in bestimmten Situationen geschieht.

Wesentlich sind zwei Arten von Szenarien:

-   Nutzungsszenarien (auch bekannt als Anwendungs- oder
    Anwendungsfallszenarien) beschreiben, wie das System zur Laufzeit
    auf einen bestimmten Auslöser reagieren soll. Hierunter fallen auch
    Szenarien zur Beschreibung von Effizienz oder Performance. Beispiel:
    Das System beantwortet eine Benutzeranfrage innerhalb einer Sekunde.

-   Änderungsszenarien beschreiben eine Modifikation des Systems oder
    seiner unmittelbaren Umgebung. Beispiel: Eine zusätzliche
    Funktionalität wird implementiert oder die Anforderung an ein
    Qualitätsmerkmal ändert sich.

**Motivation.**

Szenarien operationalisieren Qualitätsanforderungen und machen deren
Erfüllung mess- oder entscheidbar.

Insbesondere wenn Sie die Qualität Ihrer Architektur mit Methoden wie
ATAM überprüfen wollen, bedürfen die in Abschnitt 1.2 genannten
Qualitätsziele einer weiteren Präzisierung bis auf die Ebene von
diskutierbaren und nachprüfbaren Szenarien.

**Form.**

Entweder tabellarisch oder als Freitext.

Risiken und technische Schulden {#section-technical-risks}
===============================

**Inhalt.**

Eine nach Prioritäten geordnete Liste der erkannten Architekturrisiken
und/oder technischen Schulden.

> Risikomanagement ist Projektmanagement für Erwachsene.
>
> ---  Tim Lister Atlantic Systems Guild

Unter diesem Motto sollten Sie Architekturrisiken und/oder technische
Schulden gezielt ermitteln, bewerten und Ihren Management-Stakeholdern
(z.B. Projektleitung, Product-Owner) transparent machen.

**Form.**

Liste oder Tabelle von Risiken und/oder technischen Schulden, eventuell
mit vorgeschlagenen Maßnahmen zur Risikovermeidung, Risikominimierung
oder dem Abbau der technischen Schulden.

Glossar {#section-glossary}
=======

**Inhalt.**

Die wesentlichen fachlichen und technischen Begriffe, die Stakeholder im
Zusammenhang mit dem System verwenden.

Nutzen Sie das Glossar ebenfalls als Übersetzungsreferenz, falls Sie in
mehrsprachigen Teams arbeiten.

**Motivation.**

Sie sollten relevante Begriffe klar definieren, so dass alle Beteiligten

1.  diese Begriffe identisch verstehen, und

2.  vermeiden, mehrere Begriffe für die gleiche Sache zu haben.

-   Zweispaltige Tabelle mit \<Begriff\> und \<Definition\>

-   Eventuell weitere Spalten mit Übersetzungen, falls notwendig.

+-----------------------+-----------------------------------------------+
| Begriff               | Definition                                    |
+=======================+===============================================+
| *\<Begriff-1\>*       | *\<Definition-1\>*                            |
+-----------------------+-----------------------------------------------+
| *\<Begriff-2*         | *\<Definition-2\>*                            |
+-----------------------+-----------------------------------------------+
