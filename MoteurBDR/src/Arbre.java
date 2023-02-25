import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public Arbre(String requete){
        String[] columns = null;
        HashMap<String, String> tables_allias = new HashMap<>();
        //String sql = "SELECT test,column2,column3 FROM test t1, table t2 WHERE t1.column1 = t2.column2 AND column4 = 'value' AND column3 = 'value2';";
        Pattern pattern = Pattern.compile("SELECT\\s+(?:DISTINCT\\s+)?\\s*([a-zA-Z_][a-zA-Z0-9_]*(?:\\s*,\\s*[a-zA-Z_][a-zA-Z0-9_]*)*)\\s+FROM");
        Matcher matcher = pattern.matcher(requete);
        if (matcher.find()) {
            String columnNames = matcher.group(1);
            columns = columnNames.split("\\s*,\\s*");
            /**
            for (String s : columns)
                System.out.println(s);
             **/
        } else {
            System.out.println("Column names not found");
        }
        pattern = Pattern.compile("FROM\\s+([a-zA-Z_][a-zA-Z0-9_]*(?:\\s+[a-zA-Z_][a-zA-Z0-9_]*|)(?:\\s*,\\s*[a-zA-Z_][a-zA-Z0-9_]*(?:\\s+[a-zA-Z_][a-zA-Z0-9_]*|)*|)*)\\s+WHERE");
        matcher = pattern.matcher(requete);
        if (matcher.find()) {
            String tableNamesAndAliases = matcher.group(1);
            String[] tables = tableNamesAndAliases.split("\\s*,\\s*");
            for(String s : tables){
                String[] allias = s.split(" ");
                if(allias.length > 1){
                    tables_allias.put(
                            allias[1],
                            allias[0]
                    );
                }
                else {
                    tables_allias.put(
                            allias[0],
                            allias[0]
                    );
                }
            }

        } else {
            System.out.println("Table names and aliases not found");
        }

        pattern = Pattern.compile("WHERE\\s+(.*?)(?:\\s+GROUP\\s+BY|\\s+HAVING|\\s+ORDER\\s+BY|;)");
        matcher = pattern.matcher(requete);
        if (matcher.find()) {
            String conditions = matcher.group(1);
            //System.out.println("Conditions: " + conditions);




            Pattern joinConditionPattern = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\b");
            Matcher joinConditionMatcher = joinConditionPattern.matcher(conditions);
            if (joinConditionMatcher.find()) {
                String leftTable = joinConditionMatcher.group(1);
                String leftColumn = joinConditionMatcher.group(2);
                String rightTable = joinConditionMatcher.group(3);
                String rightColumn = joinConditionMatcher.group(4);

                Noeud jointureNd = new Noeud(null,"Jointure");

                String[] conditions_array = conditions.split("AND");

                Noeud leftJointure = new Noeud(jointureNd,tables_allias.get(leftTable));
                Noeud rightJointure = new Noeud(jointureNd,tables_allias.get(rightTable));
                jointureNd.setFils_gauche(leftJointure);
                jointureNd.setFils_droit(rightJointure);

                for(String s : conditions_array){
                    Pattern selectConditionPattern = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\b");
                    Matcher selectConditionMatcher = selectConditionPattern.matcher(s);
                    if(!selectConditionMatcher.find()) {
                        String[] selections = s.split("[=]")[0].split("[.]");
                        if(leftTable.equals(selections[0].trim())){
                            Noeud nd = new Noeud(leftJointure.getPere(),"Selection("+s+")");
                            nd.setFils_gauche(leftJointure);
                            leftJointure.getPere().setFils_gauche(nd);
                            leftJointure.setPere(nd);
                        }
                        if(rightTable.equals(selections[0].trim())){
                            Noeud nd = new Noeud(rightJointure.getPere(),"Selection("+s+")");
                            nd.setFils_gauche(rightJointure);
                            rightJointure.getPere().setFils_droit(nd);
                            rightJointure.setPere(nd);
                        }
                    }

                }
                this.Tete = jointureNd;
                jointureNd.setPere(this.Tete);

                for(String s : columns){
                    Noeud nd = new Noeud(null,"Projection("+s+")");
                    nd.setFils_gauche(this.Tete);
                    this.Tete.setPere(nd);
                    this.Tete = nd;
                }
                //System.out.println(leftTable);
                //System.out.println(rightTable);
                //System.out.println("Join condition: " + leftTable + "." + leftColumn + " = " + rightTable + "." + rightColumn);
            } else {
                System.out.println("No join condition found");
            }
        } else {
            System.out.println("No conditions found");
        }
    }

    // utility function to print the binary tree horizontally
    public void printTree(Noeud node, int level) {
        if (node == null) {
            return;
        }

        printTree(node.getFils_droit(), level + 5);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.getRelation());
        printTree(node.getFils_gauche(), level + 5);
    }

    public Noeud getTete() {
        return Tete;
    }

    public void setTete(Noeud tete) {
        Tete = tete;
    }

    public Noeud getQueue() {
        return Queue;
    }

    public void setQueue(Noeud queue) {
        Queue = queue;
    }
}



