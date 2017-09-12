package symtab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Parser {
	private String text;
	private String kindOfVar[] = {"var", "fun", "par"};
	private String typeOfVar[] = {"int", "float", "bool", "double"};
	private ArrayList<String> keywords;
	private String test = "void foo() {     int x = 5; }{ int y = 7; g = 7; {int y = 5;}}";
	
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
		//this.text = text.replace(";", ";\n");

		String arr[] = text.split("(?=[{;}]|\\n|\\r\\n|\\n)");
		
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = arr[i].trim().replaceAll("^ +| +$| (?= )", "");
			System.out.println("\""+arr[i]+"\"");
		}
		
		SymTab currentScope = new SymTab();
		int looper = 0;
		while(looper < arr.length)
		{
			
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
				String[] tableEntry = stmt.split("\\r\\n");
				
				
				for(String s:keywords) {
					if(stmt.contains(s)) {
						//System.out.println(stmt);
						String[] tmp = stmt.split("\\s+");
						String varName = tmp[1];
						String kind = "fun";
						if(keywords.contains(tmp[0]))
						{
							kind="var";
						}
						String type=tmp[0];
						currentScope.insert(varName, kind, type);
					}
				}
				
//				for (int i = 0; i < tableEntry.length; i++) {
//					System.out.println(tableEntry[i]);
//				}
			}
			
			
			
			looper++;
		}
		
		
	}
	
	
	
}
