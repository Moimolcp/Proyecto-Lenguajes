package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import classes.CodeStatistics.clas;
import classes.CodeStatistics.fun;

public class Main {
	
	public static void main(String args[]) throws Exception {
		
		System.setIn(new FileInputStream(new File("code.py")));
		
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		
		Python3Lexer lexer = new Python3Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ParseTree tree = parser.file_input();
		
		String fileName = "reporte";
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));	     
	    
		MyVisitor<Object> loader = new MyVisitor<Object>();
		loader.visit(tree);
		Collections.sort(loader.stats.funList, CodeStatistics.compfun);
		String txt;
		
		//writer.write(str);
		
		for (fun f : loader.stats.funList) {
			if(f.length >= 0.2*loader.stats.totalLen) {
				txt = "Funcion demasiado larga, \t linea" + f.line + ":" + f.col + "\t ID: " + f.name;
				writer.write(txt);
			}			
			if(f.parameters >= 4)   {
				txt = "Funcion con demasiados parametros (" + f.parameters + "), \t linea "  + f.line + ":" + f.col + "\t ID: " + f.name;
				writer.write(txt);
			}				
			if(!loader.set.contains(f.name) && f.name.charAt(0) != '_') {
				txt = "Funcion no usada en el codigo, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name;
				writer.write(txt);
			}				
			if(f.name.length() <= 3) {
				txt = "Funcion con nombre demasiado corto, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name;
				writer.write(txt);
			}				
			if(f.name.length() >= 25) {
				txt = "Funcion con nombre demasiado largo, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name;
				writer.write(txt);
			}
				
		}

		for (clas f : loader.stats.clasList) {
			
		}
			
		writer.close();		
		
	}

}
