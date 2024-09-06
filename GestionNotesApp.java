package tpoop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestionNotesApp extends JFrame {
    private ArrayList<Etudiant> etudiants;
    private ArrayList<Cours> coursList;

    private JTabbedPane tabbedPane;

    private JTextField txtNom, txtMatricule, txtDepartement, txtNiveau;

    private JTextField txtNomCours, txtCoefficient;

    private JComboBox<Etudiant> comboEtudiant;
    private JComboBox<Cours> comboCours;
    private JTextField txtControle, txtTP, txtExamen;

    private JTable tableMoyennes;

    private JTextField txtRecherche;
    private JComboBox<String> comboRecherchePar;
    private JTable tableRecherche;

    public GestionNotesApp() {
        etudiants = new ArrayList<>();
        coursList = new ArrayList<>();

        setTitle("Gestion des Notes - Institut Universitaire");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Ajouter Étudiant", createPanelAjouterEtudiant());
        tabbedPane.addTab("Ajouter Cours", createPanelAjouterCours());
        tabbedPane.addTab("Enregistrer Notes", createPanelEnregistrerNotes());
        tabbedPane.addTab("Calculer Moyennes", createPanelCalculerMoyennes());
        tabbedPane.addTab("Rechercher", createPanelRechercher());

        add(tabbedPane);
    }

    private JPanel createPanelAjouterEtudiant() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblNom = new JLabel("Nom:");
        JLabel lblMatricule = new JLabel("Matricule:");
        JLabel lblDepartement = new JLabel("Département:");
        JLabel lblNiveau = new JLabel("Niveau:");

        txtNom = new JTextField(20);
        txtMatricule = new JTextField(20);
        txtDepartement = new JTextField(20);
        txtNiveau = new JTextField(5);

        JButton btnAjouter = new JButton("Ajouter Étudiant");
        btnAjouter.addActionListener(e -> ajouterEtudiant());

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNom, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(txtNom, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblMatricule, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(txtMatricule, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblDepartement, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(txtDepartement, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblNiveau, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(txtNiveau, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(btnAjouter, gbc);

        return panel;
    }

    private JPanel createPanelAjouterCours() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblNomCours = new JLabel("Nom du Cours:");
        JLabel lblCoefficient = new JLabel("Coefficient:");

        txtNomCours = new JTextField(20);
        txtCoefficient = new JTextField(5);

        JButton btnAjouterCours = new JButton("Ajouter Cours");
        btnAjouterCours.addActionListener(e -> ajouterCours());

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNomCours, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(txtNomCours, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblCoefficient, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(txtCoefficient, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(btnAjouterCours, gbc);

        return panel;
    }

    private JPanel createPanelEnregistrerNotes() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblEtudiant = new JLabel("Étudiant:");
        JLabel lblCours = new JLabel("Cours:");
        JLabel lblControle = new JLabel("Contrôle:");
        JLabel lblTP = new JLabel("TP:");
        JLabel lblExamen = new JLabel("Examen:");

        comboEtudiant = new JComboBox<>();
        comboCours = new JComboBox<>();
        txtControle = new JTextField(5);
        txtTP = new JTextField(5);
        txtExamen = new JTextField(5);

        JButton btnEnregistrerNote = new JButton("Enregistrer Note");
        btnEnregistrerNote.addActionListener(e -> enregistrerNote());

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblEtudiant, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(comboEtudiant, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblCours, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(comboCours, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblControle, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(txtControle, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblTP, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(txtTP, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(lblExamen, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(txtExamen, gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(btnEnregistrerNote, gbc);

        return panel;
    }

    private JPanel createPanelCalculerMoyennes() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table setup
        String[] columns = {"Matricule", "Nom", "Département", "Niveau", "Moyenne"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        tableMoyennes = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMoyennes);

        JButton btnCalculer = new JButton("Calculer et Afficher Moyennes");
        btnCalculer.addActionListener(e -> afficherMoyennes());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnCalculer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPanelRechercher() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel lblRecherche = new JLabel("Recherche:");
        txtRecherche = new JTextField(20);
        String[] options = {"Nom", "Matricule", "Niveau"};
        comboRecherchePar = new JComboBox<>(options);
        JButton btnRecherche = new JButton("Rechercher");
        btnRecherche.addActionListener(e -> rechercherEtudiant());

        topPanel.add(lblRecherche);
        topPanel.add(txtRecherche);
        topPanel.add(comboRecherchePar);
        topPanel.add(btnRecherche);

        String[] columns = {"Matricule", "Nom", "Département", "Niveau", "Moyenne"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        tableRecherche = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableRecherche);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void ajouterEtudiant() {
        String nom = txtNom.getText().trim();
        String matricule = txtMatricule.getText().trim();
        String departement = txtDepartement.getText().trim();
        String niveauStr = txtNiveau.getText().trim();

        if (nom.isEmpty() || matricule.isEmpty() || departement.isEmpty() || niveauStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int niveau;
        try {
            niveau = Integer.parseInt(niveauStr);
            if (niveau <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le niveau doit être un entier positif.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Etudiant e : etudiants) {
            if (e.getMatricule().equalsIgnoreCase(matricule)) {
                JOptionPane.showMessageDialog(this, "Un étudiant avec ce matricule existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Etudiant etudiant = new Etudiant(nom, matricule, departement, niveau);
        etudiants.add(etudiant);
        comboEtudiant.addItem(etudiant);
        JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

        txtNom.setText("");
        txtMatricule.setText("");
        txtDepartement.setText("");
        txtNiveau.setText("");
    }

    private void ajouterCours() {
        String nomCours = txtNomCours.getText().trim();
        String coefficientStr = txtCoefficient.getText().trim();

        if (nomCours.isEmpty() || coefficientStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int coefficient;
        try {
            coefficient = Integer.parseInt(coefficientStr);
            if (coefficient <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le coefficient doit être un entier positif.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cours nouveauCours = new Cours(nomCours, coefficient);
        if (coursList.contains(nouveauCours)) {
            JOptionPane.showMessageDialog(this, "Ce cours existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        coursList.add(nouveauCours);
        comboCours.addItem(nouveauCours);
        JOptionPane.showMessageDialog(this, "Cours ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

        txtNomCours.setText("");
        txtCoefficient.setText("");
    }

    private void enregistrerNote() {
        Etudiant etudiant = (Etudiant) comboEtudiant.getSelectedItem();
        Cours cours = (Cours) comboCours.getSelectedItem();

        if (etudiant == null || cours == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant et un cours.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String controleStr = txtControle.getText().trim();
        String tpStr = txtTP.getText().trim();
        String examenStr = txtExamen.getText().trim();

        double controle, tp, examen;
        try {
            controle = Double.parseDouble(controleStr);
            tp = Double.parseDouble(tpStr);
            examen = Double.parseDouble(examenStr);

            if (controle < 0 || controle > 20 || tp < 0 || tp > 20 || examen < 0 || examen > 20) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Les notes doivent être des nombres entre 0 et 20.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (etudiant.getNotes().containsKey(cours)) {
            int option = JOptionPane.showConfirmDialog(this, "Une note pour ce cours existe déjà. Voulez-vous la mettre à jour ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }

        Note note = new Note(controle, tp, examen, cours.getCoefficient());
        etudiant.ajouterNote(cours, note);
        JOptionPane.showMessageDialog(this, "Note enregistrée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

        txtControle.setText("");
        txtTP.setText("");
        txtExamen.setText("");
    }

    private void afficherMoyennes() {
        DefaultTableModel model = (DefaultTableModel) tableMoyennes.getModel();
        model.setRowCount(0); 

        for (Etudiant e : etudiants) {
            Object[] row = {
                e.getMatricule(),
                e.getNom(),
                e.getDepartement(),
                e.getNiveau(),
                String.format("%.2f", e.calculerMoyenne())
            };
            model.addRow(row);
        }
    }

    private void rechercherEtudiant() {
        String recherche = txtRecherche.getText().trim().toLowerCase();
        String critere = (String) comboRecherchePar.getSelectedItem();

        if (recherche.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un terme de recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tableRecherche.getModel();
        model.setRowCount(0);

        for (Etudiant e : etudiants) {
            boolean correspond = false;
            switch (critere) {
                case "Nom":
                    correspond = e.getNom().toLowerCase().contains(recherche);
                    break;
                case "Matricule":
                    correspond = e.getMatricule().toLowerCase().contains(recherche);
                    break;
                case "Niveau":
                    correspond = String.valueOf(e.getNiveau()).equals(recherche);
                    break;
            }

            if (correspond) {
                Object[] row = {
                    e.getMatricule(),
                    e.getNom(),
                    e.getDepartement(),
                    e.getNiveau(),
                    String.format("%.2f", e.calculerMoyenne())
                };
                model.addRow(row);
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Aucun étudiant trouvé correspondant à la recherche.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionNotesApp app = new GestionNotesApp();
            app.setVisible(true);
        });
    }
}
