package symtab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class SymTab{
	
	private SymTab parentScope;
	private ArrayList<SymTab> childScope;
	
	//First entry is name, second is type and last value -- default = 0
	private HashMap<String, Pair> entries;
		
	public SymTab() {
		entries = new HashMap<String, Pair>();
		childScope = new ArrayList<>();
	}

	public SymTab enterScope(SymTab ps) {
		SymTab newChild = new SymTab();
		ps.childScope.add(newChild);		//Check
		newChild.parentScope = ps;
		return newChild;
	}
	
	public SymTab exitScope() {
		Set<String> keys = entries.keySet();
		for(String s:keys) {
			System.out.print(s);
			System.out.println("\t" + entries.get(s).getKind() + "\t" + entries.get(s).getTypeOfVar());
		}
		System.out.println();
		return parentScope;
	}
	public boolean checkBack(String name)
	{
		SymTab tmp = this;
		while(tmp != null)
		{
			if(tmp.entries.containsKey(name) == false)
				tmp = tmp.parentScope;
			else return true;
		}
		return false;
	}
	public void insert(String name, String kind, String typeOfVar) {
		if(this.entries.containsKey(name) == false) {
			entries.put(name, new Pair(typeOfVar, kind));	
		}
		else if(this.entries.containsKey(name) == true)
		{
			System.out.println(name + " already declared.");
			System.exit(-1);
		}
		else
		{
			System.out.println("Variable "+name+" declaration not found.");
			System.exit(-1);
		}
	}
	
	
	
	
}
