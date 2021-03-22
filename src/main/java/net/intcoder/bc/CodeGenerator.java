package net.intcoder.bc;

import org.apache.commons.lang3.StringUtils;

public class CodeGenerator {

    private static final String t1 = """
            for (double n0 : filteredList) {
            	<for>
            }
            """;

    private static final String t2 = """
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

    public String generate(int depth) {
        var sb = new StringBuilder();
        var s = t1;

        for (int i = 0; i < depth; i++) {
            s = s.replace("<for>", t2);
            s = s.replace("<n>", String.valueOf(i+1));
            s = s.replace("<sumn>", generateSumN(i+2));
            s = s.replace("<cn>", generateCN(i+2));
        }

        s = s.replace("<for>", "");
        s = s.replace("\r\n", "");
        s = s.replace("\n", "");
        s = StringUtils.normalizeSpace(s);
        return s;
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
