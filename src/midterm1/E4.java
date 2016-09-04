package midterm1;

import java.util.Stack;
import linearList.ArrayLinearList;
import linearList.Chain;

public class E4 {
//	1st
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Chain reverse(Chain l) {
        Stack s = new Stack();
        while (!l.isEmpty()) {
            s.push(l.remove(0));
        }
        int i = 0;
        while (!s.isEmpty()) {
            l.add(i, s.pop());
            i++;
        }
        return l;
    }
//	2nd
	public static String prefixToPostfix(ArrayLinearList<String> a) {
        Stack<Character> s = new Stack<Character>();
        int l = a.size();
        for(int i = 0; i < l; i++){
            s.push(a.remove(0).charAt(0));
        }
        String temp = "";
        while (!s.isEmpty()) {
            temp += s.pop();
        }      
        return temp;
    }
//	3rd
	public static int evalPostfix(Chain<Character> exp) {
        Stack<Character> s = new Stack<Character>();
        while (!exp.isEmpty()) {
            s.push(exp.remove(0));
        }
        switch(s.pop()){
        	case '+':
                return (Integer.parseInt(s.pop().toString()) + Integer.parseInt(s.pop().toString()));
            case '-':
                return (-Integer.parseInt(s.pop().toString()) + Integer.parseInt(s.pop().toString()));
            default:
            	return 0/0;
        }
	}
//	4th
	public static boolean isPalindrome(String pal) {
        String noSpacePal = pal.replace(" ", "");
        int l = noSpacePal.length();
        int half = l%2==0? l/2: (l+1)/2;
        Stack<Character> s = new Stack<Character>();
        for (int i = half; i < l; i++) {
            s.push(noSpacePal.charAt(i));
        }
        int i=0;
        while(!s.isEmpty()){
        	if (noSpacePal.length() % 2 == 0) {
        		if (s.pop() != noSpacePal.charAt(i)) {
                    return false;
                }
            }
        	else {
        		if (s.pop() != noSpacePal.charAt(i)) {
                    return false;
                }
            }
        	i++;
        }
        return true;
    }
//	5th
	public static int[] checkBinaryString(String exp) {
        Stack<Integer> s0 = new Stack<Integer>();
        Stack<Integer> s1 = new Stack<Integer>();
        int[] answer = new int[2];
        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) == '0') {
                s0.push(0);
            }
            else {
                s1.push(1);
            }
        }
        Stack<Integer> larger = s0.size() >= s1.size() ? s0 : s1;
        Stack<Integer> smaller = s0.size() < s1.size() ? s0 : s1;
        answer[0] = s0.size() >= s1.size() ? 0 : 1;
        while(!smaller.isEmpty()) {
            larger.pop();
            smaller.pop();
        }
        answer[1] = larger.size();
        return answer;
    }
//	6th on separate class
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("1st");
		Chain<Integer> linkList = new Chain<Integer>();
		for(int i=0;i<10;i++){
			linkList.add(i, i);
		}
		System.out.println(linkList.output());
		linkList=reverse(linkList);
		System.out.println(linkList.output());
		
		System.out.println("\n2nd");
		ArrayLinearList<String> a = new ArrayLinearList<String>();
		a.add(0, "+");
		a.add(1, "1");
		a.add(2, "2");
		System.out.println(a.output());
		System.out.println(prefixToPostfix(a));
		
		System.out.println("\n3rd");
		Chain<Character> lL = new Chain<Character>();
		lL.add(0, '1');
		lL.add(1, '4');
		lL.add(2, '+');
		System.out.println(evalPostfix(lL));

		System.out.println("\n4th");
		String b = "abcdedcba";
		System.out.println(b);
		System.out.println(isPalindrome(b));
		
		System.out.println("\n5th");
		String c = "0001001010111010";
		int[] d = checkBinaryString(c);
		System.out.println(d[0]+" "+d[1]);
		
		System.out.println("\n6th Push each command to a stack, pop every command to new stack, pop command from new stack");
		System.out.println("\n7th on separate java class");
		System.out.println("\n8th Create DLinkedList that inserts only at end and removes from the end");
		System.out.println("\n9th LL Remove firstNode and append to StringBuilder, AR remove array[0] for length and append to StringBuilder");
		System.out.println("\n10th on separate java class");
		System.out.println("\n11th on separate java class");
		System.out.println("\n12th Use a bottom pointer");
	}

}
