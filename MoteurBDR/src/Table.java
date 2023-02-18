import java.util.Vector;

/**
 * 
 */
public class Table {

    /**
     * Attributes
     */
    private Vector<Colonne> colonnes;

    /**
     * Default constructor
     */
    public Table() {
        this.colonnes = new Vector<>();
    }

    /**
     *
     * @param colonnes
     */
    public Table(Vector<Colonne> colonnes) {
        this.colonnes = colonnes;
    }

    /**
     *
     * @param c
     */
    public void addColonne(Colonne c){
        this.colonnes.add(c);
    }






}