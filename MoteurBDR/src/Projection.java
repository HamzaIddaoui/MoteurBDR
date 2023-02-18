import java.util.Vector;

/**
 * 
 */
public class Projection extends Relation {

    /**
     * Attributes
     */
    private Vector<Colonne> colonnes;

    /**
     * Default constructor
     */
    public Projection() {
        this.colonnes = new Vector<>();
    }

    /**
     *
     * @param colonnes
     */
    public Projection(Vector<Colonne> colonnes) {
        this.colonnes = colonnes;
    }

    @Override
    public String toString() {
        return "Projection"+this.colonnes.toString();
    }
}