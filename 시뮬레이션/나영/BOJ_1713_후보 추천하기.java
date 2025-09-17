import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, min;
    static List<Integer> list = new ArrayList<>();
    static List<Integer> prospect;
    static int [] student = new int [101];
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            student[a]++;
            if (list.size() == n) {
                if (!list.contains(a)) {
                    min = Integer.MAX_VALUE;
                    prospect = new ArrayList<>();
                    for (int j = 0; j < list.size(); j++) {
                        int b = list.get(j);
                        if (min > student[b]) {
                            min = student[b];
                            prospect.clear();
                            prospect.add(j);
                        } else if (min == student[b]) prospect.add(j);
                    }

                    int removeIndex = prospect.get(0);
                    int removeStudent = list.get(removeIndex);
                    student[removeStudent] = 0;
                    list.remove(removeIndex);
                    list.add(a);
                }
            } else {
                if (!list.contains(a)) list.add(a);
            }
        }

        list.sort(null);

        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}