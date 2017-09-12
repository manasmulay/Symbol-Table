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
	private String typeOfVar[] = {"int", "float", "bool", "double", "void"};
	private ArrayList<String> keywords;
	private String test = "{void foo() {     int x = 5;}{ int z = 7; int g = 5; void zz ; g = 7; {int y = 5;}}}";
	
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
				for(String s:keywords)
				{
					if(stmt.contains(s))
					{

						funcFlag = 1;
						String[] tmp = stmt.split("\\s+");
						String varName = tmp[1];
						String kind = "fun";
						if(keywords.contains(tmp[0]))
						{
							kind="var";
						}
						String type=tmp[0];
						System.out.println(type + varName + kind + type);
						currentScope.insert(varName, kind, type);
					}
				}
				
				if(funcFlag == 0)
				{
					
				}

			}
			
			
			
			looper++;
		}
		
		
	}
	
	
	
}
