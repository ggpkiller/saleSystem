package xyz.nxlexiaoyao.sales.util;

public class MathUtil {
	public static int multiplyAndAdd(int base,int start,int end) {
		int  count = 0;
		for(int i=start;i<end+1;i++) {
			count += (int)Math.pow(base, i);
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		System.out.println(multiplyAndAdd(3, 0, 6));
	}
}
