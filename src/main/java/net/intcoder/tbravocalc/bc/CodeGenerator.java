package net.intcoder.tbravocalc.bc;

import org.apache.commons.lang3.StringUtils;

public class CodeGenerator {

    private static final String forTemplate = """
            for (double n0 : filteredList) {
                if (!continueCalculate) break;
            
            	<for>
            }
            """;

    private static final String subForTemplate = """
            for (double n<n> : filteredList.stream().skip(<n>).collect(Collectors.toList())) {
                if (!continueCalculate) break;
            
            	var sum<n> = <sumn>;
            	
            	if (sum<n> == target) {
            	    continueCalculate = false;
            	}
            	
                if (sum<n> > minSum && sum<n> <= target) {
            		minSum = sum<n>;
                    pathPrinter.printPath(<cn>);
                } else if (sum<n> <= target) {
                	pathPrinter.debugPath(<cn>);
                } else {
                    pathPrinter.tracePath(<cn>);
                }
                
                <for>
            }
            """;

    private static final String subForTemplatePrintAll = """
            for (double n<n> : filteredList.stream().skip(<n>).collect(Collectors.toList())) {
                if (!continueCalculate) break;
            
            	var sum<n> = <sumn>;
                if (sum<n> >= minSum && sum<n> <= target) {
            		minSum = sum<n>;
                    pathPrinter.printPath(<cn>);
                } else if (sum<n> <= target) {
                	pathPrinter.debugPath(<cn>);
                } else {
                    pathPrinter.tracePath(<cn>);
                }
                
                <for>
            }
            """;

    private static final String classTemplate = """
            package net.intcoder.tbravocalc.bc;
                        
            import java.util.Arrays;
            import java.util.stream.Collectors;
            
            import net.intcoder.tbravocalc.PathPrinter;
                        
            public class Calculator {
            
                private PathPrinter pathPrinter;
                private boolean continueCalculate = true;
                
                public Calculator(PathPrinter pathPrinter) {
                    this.pathPrinter = pathPrinter;
                }
            
                public void calculate(double[] spreadsheet, double target) {
                        
                    var filteredList = Arrays.stream(spreadsheet).filter(n -> n <= target)
                            .boxed()
                            .collect(Collectors.toList());
                        
                    System.out.println("Size: " + filteredList.size());
                        
                    double minSum = 0;
                        
                    <for>
                        
                    System.out.println("min sum = " + minSum);
                }
            }
                        
            """;

    private boolean printAll;

    public CodeGenerator(boolean printAll) {
        this.printAll = printAll;
    }

    public String generate(int depth) {
        var sb = new StringBuilder();
        var s = forTemplate;

        for (int i = 0; i < depth; i++) {
            s = s.replace("<for>", printAll ? subForTemplatePrintAll : subForTemplate);
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
