package classes;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;import classes.CodeStatistics.fun;

public class Main {
	
	public static void main(String args[]) throws Exception {
		
		System.setIn(new FileInputStream(new File("test.py")));
		
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		
		Python3Lexer lexer = new Python3Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		ParseTree tree = parser.file_input();
		
		MyVisitor<Object> loader = new MyVisitor<Object>();
		loader.visit(tree);
		Collections.sort(loader.stats.funList, CodeStatistics.compfun);
		for (fun f : loader.stats.funList) {
			if(f.length >= 0.03*loader.stats.totalLen)    // PORCENTAJE A CAMBIAR 
				System.out.println("Funcion demasiado larga, \t linea" + f.line + ":" + f.col + "\t ID: " + f.name );
		}
		
		for (fun f : loader.stats.funList) {
			if(f.parameters >= 4)   
			System.out.println("Funcion con demasiados parametros (" + f.parameters + "), \t linea "  + f.line + ":" + f.col + "\t ID: " + f.name);
		}
		
		for (fun f : loader.stats.funList) {
			if(!loader.set.contains(f.name) && f.name.charAt(0) != '_')
			System.out.println("Funcion no usada en el codigo, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name);
		}
		
	}

}
