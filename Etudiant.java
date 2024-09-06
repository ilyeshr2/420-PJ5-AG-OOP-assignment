package tpoop;

// Etudiant.java
import java.util.HashMap;
import java.util.Map;

public class Etudiant {
    private String nom;
    private String matricule;
    private String departement;
    private int niveau;
    private Map<Cours, Note> notes;

    public Etudiant(String nom, String matricule, String departement, int niveau) {
        this.nom = nom;
        this.matricule = matricule;
        this.departement = departement;
        this.niveau = niveau;
        this.notes = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getDepartement() {
        return departement;
    }

    public int getNiveau() {
        return niveau;
    }

    public Map<Cours, Note> getNotes() {
        return notes;
    }

    public void ajouterNote(Cours cours, Note note) {
        notes.put(cours, note);
    }

    public double calculerMoyenne() {
        double somme = 0;
        int totalCoefficients = 0;
        for (Note note : notes.values()) {
            somme += note.getMoyenne() * note.getCoefficient();
            totalCoefficients += note.getCoefficient();
        }
        return totalCoefficients == 0 ? 0 : somme / totalCoefficients;
    }

    @Override
    public String toString() {
        return nom + " (" + matricule + ")";
    }
}
