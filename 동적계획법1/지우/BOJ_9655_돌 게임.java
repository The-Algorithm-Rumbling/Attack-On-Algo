package 동적계획법1.지우;

import java.util.*;

class Main {
    static int N;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if(N%2 != 0) System.out.println("SK");
        else System.out.println("CY");
    }
}