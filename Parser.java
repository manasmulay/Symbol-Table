package symtab;

import java.util.ArrayList;


public class Parser {
	private String text;
	private String kindOfVar[] = {"var", "fun", "par"};
	private String typeOfVar[] = {"int", "float", "bool", "double", "void"};
	private ArrayList<String> keywords;
	private String test = "{void foo (int a,int b) {int zzz; {     int x = 5;}{ int z = 7; int g = 5; {int y = 5;}}}} {int foo2() {int z;}}";
	
	public Parser(String tex)
	{
		this.text = test;
		
		keywords = new ArrayList<>();
		
		for (int i = 0; i < typeOfVar.length; i++) 
		{
			keywords.add(typeOfVar[i]);
		}
		

		
		this.text = text.replace("{", "{\n");
		this.text = text.replace("} ", "\n}\n");
		this.text = text.replace(";", ";\n");

		String arr[] = text.split("(?=[{;}]|\\n|\\r\\n|\\n)");
		
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = arr[i].trim().replaceAll("^ +| +$| (?= )", "");
			System.out.println("\""+arr[i]+"\"");
		}
		
		SymTab currentScope = new SymTab();
		int looper = 0;
		int funcFlag = 0;
		while(looper < arr.length)
		{
			funcFlag = 0;
			String stmt = arr[looper];
			//System.out.println("LOOPER=" + looper + arr[4]);
			
			if(stmt.contains("{")) 
			{
				currentScope = currentScope.enterScope(currentScope);
			}
			
			if(stmt.contains("}")) 
			{
				currentScope = currentScope.exitScope();
			}
			
			else 
			{
				if(stmt.matches("\\w\\s+=\\s+\\w") )
				{					
					String[] varReass = stmt.split("(?=[\\w|=])");
					boolean varDec = currentScope.checkBack(varReass[0]);
					if(varDec == false)
					{
						System.out.println("Variable " + varReass[0] + " not declared.");
						System.exit(-1);
					}
					
					looper++;
					continue;
				}
				
				for(String s:keywords)
				{
					if(stmt.contains(s))
					{

						
						String[] tmp = stmt.split("\\s+");
						String varName = tmp[1];
						String kind = "fun";
						String type = "int";
						

						if(keywords.contains(tmp[0]))
						{
							
							
							//For normal declarations
							kind="var";
							type=tmp[0];
							
							//For functions
							if(tmp.length >= 2 && stmt.matches("[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])") )
							{
								kind = "fun";
								
								//String[] func = stmt.split("(?=[\\n|\\r|\\r\\n|({})])");
								String[] func = stmt.split("\\(|\\)|,");
								if(func.length > 1)
								{
									ArrayList<String> funcParameters = new ArrayList<>();
									for (int i = 1; i < func.length; i++)
									{
										String[] funcParam = func[i].split("\\s+");
										funcParameters.add(funcParam[0]);
										currentScope.insert(funcParam[1], "var", funcParam[0]);
									}
								}
							}
						}	
						///System.out.println(type + " "+ varName + " " +  kind + " " +type);
						currentScope.insert(varName, kind, type);
						break;
					}
				}
				//For variable reassignment
				
			}
			looper++;
		}
	}
}
