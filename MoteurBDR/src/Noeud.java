/**
 * 
 */
public class Noeud {

    /**
     * Attributes
     */
    private Noeud fils_gauche;
    private Noeud fils_droit;
    private Noeud pere;
    private String relation;

    public Noeud(Noeud fils_gauche, Noeud fils_droit, Noeud pere, String relation) {
        this.fils_gauche = fils_gauche;
        this.fils_droit = fils_droit;
        this.pere = pere;
        this.relation = relation;
    }

    public Noeud(Noeud pere, String relation) {
        this.pere = pere;
        this.relation = relation;
    }


    public Noeud getFils_gauche() {
        return fils_gauche;
    }

    public void setFils_gauche(Noeud fils_gauche) {
        this.fils_gauche = fils_gauche;
    }

    public Noeud getFils_droit() {
        return fils_droit;
    }

    public void setFils_droit(Noeud fils_droit) {
        this.fils_droit = fils_droit;
    }

    public Noeud getPere() {
        return pere;
    }

    public void setPere(Noeud pere) {
        this.pere = pere;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}