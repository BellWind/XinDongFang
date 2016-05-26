package Calculator;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static BigInteger quickPow(int a, int b) {
		BigInteger ans = new BigInteger(1+"");
		BigInteger p = new BigInteger(a+"");
		while(b != 0) {
			if(b % 2 != 0) 
				ans = ans.multiply(p);
			p = p.multiply(p);
			b = b / 2;
		}
		return ans;
	}
	public static void main(String[] args) {
		Set<BigInteger> set = new TreeSet<BigInteger>();
		for(int a = 2; a <= 100; a++) {
			for(int b = 2; b <= 100; b++) {
				BigInteger ans = quickPow(a, b);
				set.add(ans);
			}
		}
		System.out.println(set.size());
	}
}
