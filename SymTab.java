package symtab;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SymTab {
	
	private SymTab parentScope;
	private ArrayList<SymTab> childScope;
	
	//First entry is name, second is type and last value -- default = 0
	private HashMap<String, HashMap<String, String>> entries;
		
	public SymTab() {
		entries = new HashMap<>();
		childScope = new ArrayList<>();
	}

	public SymTab enterScope(SymTab ps) {
		SymTab newChild = new SymTab();
		ps.childScope.add(newChild);		//Check
		newChild.parentScope = ps;
		return newChild;
	}
	
	public SymTab exitScope() {
		return parentScope;
	}
	
	public void insert(String name, String kind, String typeOfVar) {
		
		if(!this.entries.containsValue(name)) {
			HashMap< String, String> tmp = new HashMap();
			tmp.put(typeOfVar, kind);
			entries.put(name,tmp);	
			System.out.println(name + "\t" + kind + "\t" + typeOfVar);
		}
		
	}
	
	
	
	
}
