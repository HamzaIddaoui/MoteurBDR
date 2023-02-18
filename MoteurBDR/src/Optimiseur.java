/**
 * 
 */
public class Optimiseur {

    /**
     * Default constructor
     */
    public Optimiseur() {
    }

    /**
    public static HashMap<String,String> transformerRequete(String requete){
        Vector<String> mot_cles = new Vector<>();
        mot_cles.add("select");
        mot_cles.add("distinct");
        mot_cles.add("from");
        mot_cles.add("where");
        mot_cles.add("and");
        //{"select","distinct","from","where","and"};
        String key = null;
        String value = null;
        String[] tableaux_mot_cles = requete.split(" ");
        HashMap<String, String> requete_transformee = new HashMap<>();
        for(String mot : tableaux_mot_cles) {

            if(mot.equals(";")){
                requete_transformee.put(key,value);
                break;
            }

            if( (mot_cles.contains(mot.toLowerCase())) && (key == null) )
                key = mot;

            else if( (mot_cles.contains(mot.toLowerCase())) && (key != null) && (value == null))
                key += mot;

            else if ( (!mot_cles.contains(mot.toLowerCase())) && (value == null))
                value = mot;

            else if ( (!mot_cles.contains(mot.toLowerCase())) && (value != null))
                value += mot;

            else if ( (mot_cles.contains(mot.toLowerCase())) && (value != null)) {
                requete_transformee.put(key, value);
                key = mot;
                value = null;
            }
        }
        return requete_transformee;
    }
     **/

    /**
     * Fonction d'ajout d'une projection a l'arbre
     * @param formule
     * @return
     */
    public static Arbre AjouterProjection(String formule){
        String[] arrFormule = formule.split("Select",2);
        String projection = "Projection("+arrFormule[1]+")";
        Arbre arbre = new Arbre(new Noeud(projection));
        return arbre;
    }


    /**
     *
     * @param arbre
     * @param tables
     */
    public static void AjouterTables(Arbre arbre, String tables){
        String[] arrtables = tables.split(",",2);
        arbre.getTete().setFils_gauche(new Noeud(arrtables[0]));
        arbre.getTete().setFils_droit(new Noeud(arrtables[1]));
    }


    /**
     *
     * @param arbre
     */
    public static void AjouterJointure(Arbre arbre){
        Noeud noeud = new Noeud("Jointure");
        noeud.setFils_droit(arbre.getTete().getFils_droit());
        noeud.setFils_gauche(arbre.getTete().getFils_gauche());
        arbre.getTete().setFils_gauche(noeud);
        arbre.getTete().setFils_droit(null);
    }



    public static Noeud getTableNoeud(Noeud nd, String table){
        if(nd == null)
            return null;
        // Si le fils droit est Feuille (Une table)
        if( (nd.getFils_gauche() != null) && (nd.getFils_gauche().estFeuille())){
            // Si c'est la table cherchee retourne le noeud (Pere)
            if(nd.getFils_gauche().getRelation().split(" ")[2].equals(table))
                return nd;
        }

        // Si le fils gauche est noeud
        if( (nd.getFils_droit() != null) && (nd.getFils_droit().estFeuille())){
            // Si c'est la table cherchee retourne le noeud (Pere)
            if(nd.getFils_droit().getRelation().split(" ")[2].equals(table))
                return nd;
        }

        Noeud nv_Nd = getTableNoeud(nd.getFils_gauche(),table);
        if(nv_Nd != null)
            return nv_Nd;
        else return getTableNoeud(nd.getFils_droit(),table);
    }


    /**
     *
     * @param arbre
     * @param selection
     */
    public static void AjouterSelection(Arbre arbre, String selection){
        String[] arrSelection = selection.split("[.]",2);
        Noeud Ndselection = new Noeud();
        Noeud nd = getTableNoeud(arbre.getTete(),arrSelection[0].split(" ",2)[1]);
        //System.out.println(nd == null ? null : nd.getRelation());
        if(nd.getFils_gauche().getRelation().split(" ")[2].equals(arrSelection[0].split(" ",2)[1])){
            Ndselection.setRelation("Selection("+ arrSelection[1].split(";")[0] + ")");
            Ndselection.setFils_gauche(nd.getFils_gauche());
            nd.setFils_gauche(Ndselection);
        }
        else{
            Ndselection.setRelation("Selection("+ arrSelection[1].split(";")[0] + ")");
            Ndselection.setFils_gauche(nd.getFils_droit());
            nd.setFils_droit(Ndselection);

        }


    }



    /**
     *
     * @param args
     */
    public static void main(String args[]){
        String requete = "Select t1.first_name, t2.id From table1 t1, table2 t2 Where t1.id = t2.id_table1 And t2.last_name = 'test';";
        String[] arrRequete = requete.split("From",2);
        String select = arrRequete[0];
        arrRequete = arrRequete[1].split("Where",2);
        String tables = arrRequete[0];
        arrRequete = arrRequete[1].split("And",2);
        String selection1 = arrRequete[0];
        String selection2 = arrRequete[1];
        Arbre arbre = AjouterProjection(select);
        AjouterTables(arbre,tables);
        AjouterJointure(arbre);
        AjouterSelection(arbre,selection2);
        arbre.printTree(arbre.getTete(),0);
        //System.out.println(selection2);
        //Noeud test = getTableNoeud(arbre.getTete(),"t1");
        //System.out.println(test == null ? null : test.getRelation());





        /**
        Table users = new Table();
        users.addColonne(new Colonne("id_user"));
        users.addColonne(new Colonne("first_name_user"));
        users.addColonne(new Colonne("last_name_user"));
        HashMap<String,String> requete_transformee = transformerRequete(requete);
        Vector<Relation> relations = new Vector<>();
        for(Map.Entry<String,String> elem : requete_transformee.entrySet()) {
            //System.out.println(elem.getKey() + " " + elem.getValue());
            if(elem.getKey().toLowerCase().equals("select")) {
                String[] colonnes = elem.getValue().split(",");
                Vector<Colonne> v_colonnes = new Vector<>();
                for(String colonne : colonnes)
                    v_colonnes.add(new Colonne(colonne));
                Projection projection = new Projection(v_colonnes);
                System.out.println(projection);
            }

            else if(elem.getKey().toLowerCase().equals("where"))
            System.out.println("Selection("+elem.getValue()+ ")");
        }

         **/




    }




}