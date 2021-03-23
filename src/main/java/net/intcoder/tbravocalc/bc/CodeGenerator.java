package net.intcoder.tbravocalc.bc;

import org.apache.commons.lang3.StringUtils;

public class CodeGenerator {

    private static final String forTemplate = """
            for (double n0 : filteredList) {
            	<for>
            }
            """;

    private static final String subForTemplate = """
            for (double n<n> : filteredList.stream().skip(<n>).collect(Collectors.toList())) {
            	var sum<n> = <sumn>;
                if (sum<n> > minSum && sum<n> <= target) {
            		minSum = sum<n>;
                    printPath(<cn>);
                } else if (sum<n> <= target) {
                	debugPath(<cn>);
                }
                <for>
            }
            """;

    private static final String classTemplate = """
            package net.intcoder.tbravocalc.bc;
                        
            import java.util.Arrays;
            import java.util.stream.Collectors;
                        
            public class Calculator {
                public static void calculate(double[] spreadsheet, double target) {
                        
                    var filteredList = Arrays.stream(spreadsheet).filter(n -> n <= target)
                            .boxed()
                            .collect(Collectors.toList());
                        
                    System.out.println("Size: " + filteredList.size());
                        
                    double minSum = 0;
                        
                    <for>
                        
                    System.out.println("min sum = " + minSum);
                }
                        
                protected static void printPath(double... path) {
                    Arrays.stream(path).boxed().map(n -> n + " + ").forEach(System.out::print);
                    System.out.print("\\b\\b\\b");
                    System.out.println(" = " + Arrays.stream(path).sum());
                }
                        
                protected static void debugPath(double... path) {
                    if (true) return;
                    System.out.print("\\tDEBUG: ");
                    printPath(path);
                }
            }
                        
            """;

    public String generate(int depth) {
        var sb = new StringBuilder();
        var s = forTemplate;

        for (int i = 0; i < depth; i++) {
            s = s.replace("<for>", subForTemplate);
            s = s.replace("<n>", String.valueOf(i+1));
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
