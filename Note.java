package tpoop;

// Note.java
public class Note {
    private double controle;
    private double tp;
    private double examen;
    private int coefficient;

    public Note(double controle, double tp, double examen, int coefficient) {
        this.controle = controle;
        this.tp = tp;
        this.examen = examen;
        this.coefficient = coefficient;
    }

    public double getControle() {
        return controle;
    }

    public double getTp() {
        return tp;
    }

    public double getExamen() {
        return examen;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public double getMoyenne() {
        return (controle * 0.3 + tp * 0.2 + examen * 0.5);
    }

    @Override
    public String toString() {
        return String.format("Contrôle: %.2f, TP: %.2f, Examen: %.2f, Moyenne: %.2f", 
                              controle, tp, examen, getMoyenne());
    }
}
