package net.intcoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TCalculator {
    public static void main(String... args) {
        double target = 3101;
        double[] spreadsheet = new double[] {
                780,
                184,
                688,
                280,
                414,
                3135,
                1499,
                654,
                652,
                1000,
                2791,
                120,
                120,
                120,
                358,
                120,
                99,
                198,
                50,
                120,
                120,
                150,
                265,
                290,
                83,
                279,
                250,
                170,
                210,
                746,
                5330,
                99,98
        };



        var filteredList = Arrays.stream(spreadsheet).filter(n -> n <= target)
                .boxed()
                .collect(Collectors.toList());

        double minSum = 0;

        for (double n0 : filteredList) {
            for (double n1 : filteredList.stream().skip(1).collect(Collectors.toList())) {
                for (double n2 : filteredList.stream().skip(2).collect(Collectors.toList())) {
                    for (double n3 : filteredList.stream().skip(3).collect(Collectors.toList())) {
                        for (double n4 : filteredList.stream().skip(4).collect(Collectors.toList())) {
                            var sum = n0+n1+n2+n3+n4;
                            if (sum > minSum && sum <= target) {
                                minSum = sum;
                                printPath(n0,n1,n2,n3,n4);
                            }
                        }
                    }
                }
            }
        }

/*        for (int i0 = 0; i0 < filteredList.size(); i0++) {
            for (int i1 = 1; i1 < filteredList.size(); i1++) {
                for (int i2 = 2; i2 < filteredList.size(); i2++) {
                    for (int i3 = 3; i3 < filteredList.size(); i3++) {
                        for (int i4 = 4; i4 < filteredList.size(); i4++) {
                            var sum = i0+i1+i2+i3+i4;
                            if (sum > minSum && sum <= target) {
                                minSum = sum;
                                printPath(i0,i1,i2,i3,i4);
                            }
                        }
                    }
                }
            }
        }*/

        System.out.println("min sum = " + minSum);
    }

    protected static void printPath(double... path) {
        Arrays.stream(path).boxed().map(n -> n + " + ").forEach(System.out::print);
        System.out.println(" = " + Arrays.stream(path).sum());
    }
}
