# MOPS Styleguide

<img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/hhu-propra2/styleguide">

Ihr könnt dieses Repository klonen und eigene Änderungen per Pull Request an uns
schicken. Auch könnt ihr eigene Links zu euren Subsystemen einpflegen, wenn ihr
das möchtet.

Eine Live-Demo findet ihr unter http://mops.style

Eine Demoanwendung, die diesen Styleguide implementiert, ist die
[Keycloak-Demo](https://github.com/hhu-propra2/keycloak-demo).

## Ausführung

Die Anwendung startet mit

    gradle bootRun
    
## Entwicklung

Zur Entwicklung ist nodejs notwendig.

Die einzelnen Schritte zum Bauen einer JAR stehen im CI Workflow.

Gradle kann auch die SCSS und JavaScript Files transpilieren und minimieren. Das
findet beim Erzeugen der Jar statt oder mit dem Befehl:

    gradle gulp
    
Der neue Styleguide wird mit

    gradle dist
    
gebaut. Das macht die CI automatisch bei jedem Push nach Master.