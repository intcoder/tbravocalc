package net.intcoder.bc;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calculator {
    public static void calculate(double[] spreadsheet, double target) {

        var filteredList = Arrays.stream(spreadsheet).filter(n -> n <= target)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Size: " + filteredList.size());

        double minSum = 0;

        for (double n0 : filteredList) { for (double n1 : filteredList.stream().skip(1).collect(Collectors.toList())) { var sum1 = n0+n1; if (sum1 > minSum && sum1 <= target) { minSum = sum1; printPath(n0,n1); } else if (sum1 <= target) { debugPath(n0,n1); } for (double n2 : filteredList.stream().skip(2).collect(Collectors.toList())) { var sum2 = n0+n1+n2; if (sum2 > minSum && sum2 <= target) { minSum = sum2; printPath(n0,n1,n2); } else if (sum2 <= target) { debugPath(n0,n1,n2); } for (double n3 : filteredList.stream().skip(3).collect(Collectors.toList())) { var sum3 = n0+n1+n2+n3; if (sum3 > minSum && sum3 <= target) { minSum = sum3; printPath(n0,n1,n2,n3); } else if (sum3 <= target) { debugPath(n0,n1,n2,n3); } for (double n4 : filteredList.stream().skip(4).collect(Collectors.toList())) { var sum4 = n0+n1+n2+n3+n4; if (sum4 > minSum && sum4 <= target) { minSum = sum4; printPath(n0,n1,n2,n3,n4); } else if (sum4 <= target) { debugPath(n0,n1,n2,n3,n4); } for (double n5 : filteredList.stream().skip(5).collect(Collectors.toList())) { var sum5 = n0+n1+n2+n3+n4+n5; if (sum5 > minSum && sum5 <= target) { minSum = sum5; printPath(n0,n1,n2,n3,n4,n5); } else if (sum5 <= target) { debugPath(n0,n1,n2,n3,n4,n5); } for (double n6 : filteredList.stream().skip(6).collect(Collectors.toList())) { var sum6 = n0+n1+n2+n3+n4+n5+n6; if (sum6 > minSum && sum6 <= target) { minSum = sum6; printPath(n0,n1,n2,n3,n4,n5,n6); } else if (sum6 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6); } for (double n7 : filteredList.stream().skip(7).collect(Collectors.toList())) { var sum7 = n0+n1+n2+n3+n4+n5+n6+n7; if (sum7 > minSum && sum7 <= target) { minSum = sum7; printPath(n0,n1,n2,n3,n4,n5,n6,n7); } else if (sum7 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7); } for (double n8 : filteredList.stream().skip(8).collect(Collectors.toList())) { var sum8 = n0+n1+n2+n3+n4+n5+n6+n7+n8; if (sum8 > minSum && sum8 <= target) { minSum = sum8; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8); } else if (sum8 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8); } for (double n9 : filteredList.stream().skip(9).collect(Collectors.toList())) { var sum9 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9; if (sum9 > minSum && sum9 <= target) { minSum = sum9; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9); } else if (sum9 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9); } for (double n10 : filteredList.stream().skip(10).collect(Collectors.toList())) { var sum10 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10; if (sum10 > minSum && sum10 <= target) { minSum = sum10; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10); } else if (sum10 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10); } for (double n11 : filteredList.stream().skip(11).collect(Collectors.toList())) { var sum11 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11; if (sum11 > minSum && sum11 <= target) { minSum = sum11; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11); } else if (sum11 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11); } for (double n12 : filteredList.stream().skip(12).collect(Collectors.toList())) { var sum12 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11+n12; if (sum12 > minSum && sum12 <= target) { minSum = sum12; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12); } else if (sum12 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12); } for (double n13 : filteredList.stream().skip(13).collect(Collectors.toList())) { var sum13 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11+n12+n13; if (sum13 > minSum && sum13 <= target) { minSum = sum13; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13); } else if (sum13 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13); } for (double n14 : filteredList.stream().skip(14).collect(Collectors.toList())) { var sum14 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11+n12+n13+n14; if (sum14 > minSum && sum14 <= target) { minSum = sum14; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14); } else if (sum14 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14); } for (double n15 : filteredList.stream().skip(15).collect(Collectors.toList())) { var sum15 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11+n12+n13+n14+n15; if (sum15 > minSum && sum15 <= target) { minSum = sum15; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15); } else if (sum15 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15); } for (double n16 : filteredList.stream().skip(16).collect(Collectors.toList())) { var sum16 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11+n12+n13+n14+n15+n16; if (sum16 > minSum && sum16 <= target) { minSum = sum16; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16); } else if (sum16 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16); } for (double n17 : filteredList.stream().skip(17).collect(Collectors.toList())) { var sum17 = n0+n1+n2+n3+n4+n5+n6+n7+n8+n9+n10+n11+n12+n13+n14+n15+n16+n17; if (sum17 > minSum && sum17 <= target) { minSum = sum17; printPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17); } else if (sum17 <= target) { debugPath(n0,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17); } }}}}}}}}}}}}}}}}}}

        System.out.println("min sum = " + minSum);
    }

    protected static void printPath(double... path) {
        Arrays.stream(path).boxed().map(n -> n + " + ").forEach(System.out::print);
        System.out.print("\b\b\b");
        System.out.println(" = " + Arrays.stream(path).sum());
    }

    protected static void debugPath(double... path) {
        if (true) return;
        System.out.print("\tDEBUG: ");
        printPath(path);
    }
}
