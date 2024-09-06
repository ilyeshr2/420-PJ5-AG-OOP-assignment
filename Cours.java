public class Cours {
    private String nom;
    private int coefficient;

    public Cours(String nom, int coefficient) {
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public String getNom() {
        return nom;
    }

    public int getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return nom + " (Coeff: " + coefficient + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cours)) return false;
        Cours other = (Cours) obj;
        return this.nom.equalsIgnoreCase(other.nom);
    }

    @Override
    public int hashCode() {
        return nom.toLowerCase().hashCode();
    }
}
