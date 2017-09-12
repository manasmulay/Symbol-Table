package symtab;

public class Run {
	public static void main(String[] args) {
		String txt = new String("void foo() { int x=5; }");
		Parser ps = new Parser(txt);
	}

}
