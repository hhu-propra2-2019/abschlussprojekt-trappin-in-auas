package mops.models;

import lombok.Data;

@Data
public class KarriereInformationen {
    private KarriereID karriereId;
    private Abschluss Universitaetsabschluss;
    private Praferenzen praferenzen;
    private BestandeneModule bestandeneModule;
    private status immartiuklierungStatus;
    private Einstellungstype einstellungstyp;
    private Einschraenkungen einschraenkungen;
    private ArbeitsErfahrung arbeitsErfahrung;
    private TutorenSchulung tutorenschulundg;
    private Kommentar kommentar;
}
