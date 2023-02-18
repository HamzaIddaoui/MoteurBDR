/**
 * 
 */
public class Noeud {

    /**
     * Attributes
     */
    private Noeud fils_gauche;
    private Noeud fils_droit;
    private String relation;
    private boolean feuille;

    /**
     * Default constructor
     */
    public Noeud() {
        this.fils_droit = null;
        this.fils_gauche = null;
        this.relation = null;
    }

    /**
     * Constructors
     */
    public Noeud(String relation) {
        this.relation = relation;
        this.fils_gauche = null;
        this.fils_droit = null;
        this.feuille = true;
    }

    /**
     *
     * @param fils_gauche
     * @param fils_droit
     * @param relation
     */
    public Noeud(Noeud fils_gauche, Noeud fils_droit, String relation) {
        this.fils_gauche = fils_gauche;
        this.fils_droit = fils_droit;
        this.relation = relation;
        this.feuille = false;
    }

    public Noeud getFils_gauche() {
        return fils_gauche;
    }

    public void setFils_gauche(Noeud fils_gauche) {
        this.fils_gauche = fils_gauche;
        this.feuille = false;
    }

    public Noeud getFils_droit() {
        return fils_droit;
    }

    public void setFils_droit(Noeud fils_droit) {
        this.fils_droit = fils_droit;
        this.feuille = false;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public boolean estFeuille(){ return this.feuille;}


    /**
     *
     * @param nd
     */
    public void printTree(Noeud nd) {
        if (nd == null) {
            return;
        }
        System.out.print(nd.relation + " ");
        printTree(this.fils_gauche);
        printTree(this.fils_droit);
    }
}