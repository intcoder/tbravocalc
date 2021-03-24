package net.intcoder.tbravocalc.bc;

import org.apache.commons.lang3.StringUtils;

public class CodeGenerator {

    private static final String forTemplate = """
            for (int i0 = 0; i0 < spreadsheet.length; i0++) {
                double n0 = spreadsheet[i0];
                if (!continueCalculate) break;
                
                <for>
            }
            """;

    private static final String subForTemplate = """
            for (int i<n> = i<n-1>+1; i<n> < spreadsheet.length; i<n>++) {
                double n<n> = spreadsheet[i<n>];
                if (!continueCalculate) break;
                continueCalculate = !pathChecker.check(<cn>);
                
                <for>
            }
            """;

    private static final String classTemplate = """
            package net.intcoder.tbravocalc.bc;
                        
            import java.util.Arrays;
            import java.util.stream.Collectors;
            
            import net.intcoder.tbravocalc.calculator.PathChecker;
                        
            public class Calculator {
            
                private PathChecker pathChecker;
                private boolean continueCalculate = true;
                
                public Calculator(PathChecker pathChecker) {
                    this.pathChecker = pathChecker;
                }
            
                public void calculate(double... spreadsheet) {
                        
                    var filteredList = Arrays.stream(spreadsheet)
                            .boxed()
                            .collect(Collectors.toList());
                        
                    <for>
                }
            }
                        
            """;


    public String generate(int depth) {
        var s = forTemplate;

        for (int i = 0; i < depth; i++) {
            s = s.replace("<for>", subForTemplate);
            s = s.replace("<n>", String.valueOf(i+1));
            s = s.replace("<n-1>", String.valueOf(i));
            s = s.replace("<sumn>", generateSumN(i+2));
            s = s.replace("<cn>", generateCN(i+2));
        }

        s = s.replace("<for>", "");
        s = s.replace("\r\n", "");
        s = s.replace("\n", "");
        s = StringUtils.normalizeSpace(s);

        return classTemplate.replace("<for>", s);
    }

    protected String generateSumN(int length) {
        var sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("n").append(i).append("+");
        }
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    protected String generateCN(int length) {
        var sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("n").append(i).append(",");
        }
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
}
