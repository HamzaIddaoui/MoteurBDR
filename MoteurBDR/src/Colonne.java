/**
 * 
 */
public class Colonne {

    /**
     * Attributes
     */
    private String nom;

    /**
     * Default constructor
     */
    public Colonne(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}