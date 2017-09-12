package symtab;

public class Pair {
	private String typeOfVar;
	private String kind;

	public Pair(String s, String t) {
		this.typeOfVar = s;
		this.kind = t;
	}

	public String getTypeOfVar() {
		return typeOfVar;
	}

	public void setTypeOfVar(String typeOfVar) {
		this.typeOfVar = typeOfVar;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
}
