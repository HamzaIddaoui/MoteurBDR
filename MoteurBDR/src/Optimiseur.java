import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class Optimiseur {

    /**
     * Default constructor
     */
    public Optimiseur() {

    }


    public static void main(String[] args) {
        /**
        String sql = "SELECT test,column2,column3 FROM test t1, table t2 WHERE t1.column1 = t2.column2 AND column4 = 'value' AND column3 = 'value2';";
        Pattern pattern = Pattern.compile("SELECT\\s+(?:DISTINCT\\s+)?\\s*([a-zA-Z_][a-zA-Z0-9_]*(?:\\s*,\\s*[a-zA-Z_][a-zA-Z0-9_]*)*)\\s+FROM");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String columnNames = matcher.group(1);
            String[] columns = columnNames.split("\\s*,\\s*");
            for (String s : columns)
                System.out.println(s);
        } else {
            System.out.println("Column names not found");
        }
        pattern = Pattern.compile("FROM\\s+([a-zA-Z_][a-zA-Z0-9_]*(?:\\s+[a-zA-Z_][a-zA-Z0-9_]*|)(?:\\s*,\\s*[a-zA-Z_][a-zA-Z0-9_]*(?:\\s+[a-zA-Z_][a-zA-Z0-9_]*|)*|)*)\\s+WHERE");
        matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableNamesAndAliases = matcher.group(1);
            String[] tables = tableNamesAndAliases.split("\\s*,\\s*");
            System.out.println("Tables and aliases: " + Arrays.toString(tables));
        } else {
            System.out.println("Table names and aliases not found");
        }

        pattern = Pattern.compile("WHERE\\s+(.*?)(?:\\s+GROUP\\s+BY|\\s+HAVING|\\s+ORDER\\s+BY|;)");
        matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String conditions = matcher.group(1);
            System.out.println("Conditions: " + conditions);

            Pattern joinConditionPattern = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\.([a-zA-Z_][a-zA-Z0-9_]*)\\b");
            Matcher joinConditionMatcher = joinConditionPattern.matcher(conditions);
            if (joinConditionMatcher.find()) {
                String leftTable = joinConditionMatcher.group(1);
                String leftColumn = joinConditionMatcher.group(2);
                String rightTable = joinConditionMatcher.group(3);
                String rightColumn = joinConditionMatcher.group(4);
                System.out.println(leftTable);
                System.out.println(rightTable);
                System.out.println("Join condition: " + leftTable + "." + leftColumn + " = " + rightTable + "." + rightColumn);
            } else {
                System.out.println("No join condition found");
            }
        } else {
            System.out.println("No conditions found");
        }

    }**/
        Arbre arb = new Arbre("SELECT id,first_name,last_name FROM test t1 , table t2 WHERE t1.column1 = t2.column2 AND t1.column4 = 'value' AND t2.column3 = 'value2';");
        arb.printTree(arb.getTete(),5);

}
}
