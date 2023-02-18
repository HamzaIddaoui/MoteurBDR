/**
 * 
 */
public class Selection extends Relation {

    /**
     * Attributes
     */
    private Colonne colonne;
    private String operateur;
    private String operand;

    /**
     * Default constructor
     */
    public Selection() {
        this.colonne = null;
        this.operand = null;
        this.operateur = null;
    }

    /**
     *
     * @param colonne
     */
    public Selection(Colonne colonne, String operand, String operateur) {
        this.colonne = colonne;
        this.operateur = operateur;
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "Selection( " + this.colonne.toString() + " )";
    }
}