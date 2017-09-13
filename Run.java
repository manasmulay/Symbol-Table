package symtab;

public class Run {
	public static void main(String[] args) {
		String txt = new String("{void foo (int a,int b) {int zzz; {     int x = 5;}{ int z = 7;  z = 5; {int y = 5;}}}} {int foo2() {int z;}}");
		Parser ps = new Parser(txt);
	}

}
