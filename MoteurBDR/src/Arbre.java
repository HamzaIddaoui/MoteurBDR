/**
 * 
 */
public class Arbre {

    /**
     * Attributes
     */
    private Noeud Tete;
    private Noeud Queue;

    /**
     * Default constructor
     */
    public Arbre() {
        this.Queue = null;
        this.Tete = null;
    }

    /**
     * Constructor
     * @param tete
     */

    public Arbre(Noeud tete){
        this.Tete = tete;
        this.Queue = null;
    }

    public Noeud getTete() {
        return Tete;
    }

    public void setTete(Noeud tete) {
        Tete = tete;
    }

    /**
     *
     */
    public void printArbre() {
        this.Tete.printTree(this.Tete);
    }

    // recursive function to print the binary tree
    void printTree(Noeud node, int level) {
        if (node == null) {
            return;
        }
        printTree(node.getFils_droit(), level + 15);
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.println(node.getRelation());
        printTree(node.getFils_gauche(), level + 15);
    }



    public Noeud ajouterNoeud(Noeud nd, Noeud nv_nd){
        if(nd == null) {
            nd = nv_nd;
            return nd;
        }
        nd.setFils_gauche(ajouterNoeud(nd.getFils_gauche(),nv_nd));
        return nd;
    }








}