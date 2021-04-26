import java.util.*;
public class Preprocessor {
	Stack<Character> stackBracket = null;
	Preprocessor(){
		stackBracket = new Stack<Character>();
	}
	public void display(String str) {
		System.out.print(str);
	}
	public boolean validation(String exp) {
		stackBracket.clear();
			for(int i = 0;i<exp.length();i++) {
				try {
					if(exp.charAt(i)=='(' || exp.charAt(i)=='{' || exp.charAt(i)=='[') {
						stackBracket.add(exp.charAt(i));
					}else if(exp.charAt(i)==')' || exp.charAt(i)=='}' || exp.charAt(i)==']') {
						try {
							stackBracket.pop();
						}catch(Exception e) {
							return false;
						}
					}else if(exp.charAt(i)=='*' || exp.charAt(i)=='/' || exp.charAt(i)=='+' || exp.charAt(i)=='-') {
						try {
							if((exp.charAt(i-1)==')' && exp.charAt(i+1)=='(') ||
									(exp.charAt(i-1)=='}' && exp.charAt(i+1)=='{') ||
									(exp.charAt(i-1)==']' && exp.charAt(i+1)=='[') ||
									(exp.charAt(i+1)=='(' || exp.charAt(i+1)=='[' || exp.charAt(i+1)=='{') ||
									(exp.charAt(i-1)==')' || exp.charAt(i-1)==']' || exp.charAt(i-1)=='}') ||
									((exp.charAt(i-1)>='0' && exp.charAt(i-1)<='9') && (exp.charAt(i+1)>='0' && exp.charAt(i+1)<='9')) ){
								continue;
							}else {
								return false;
							}
						}catch(Exception e) {
							return false;
						}
					}
				}catch(Exception e) {
					System.out.print(exp.charAt(i));
				}
			}
		if(!stackBracket.empty()) {
			return false;
		}
		return true;
	}
}
