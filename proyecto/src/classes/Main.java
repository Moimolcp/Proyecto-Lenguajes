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
		
		String fileName = "Reporte.txt";
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));	     
	    
		MyVisitor<Object> loader = new MyVisitor<Object>();
		loader.visit(tree);
		Collections.sort(loader.stats.funList, CodeStatistics.compfun);
		String txt;
		
		//writer.write(str);
		boolean trigger = true;
		for (fun f : loader.stats.funList) {
			if(f.length >= 0.2*loader.stats.totalLen) {
				txt = "Funcion demasiado extensa, \t linea" + f.line + ":" + f.col + "\t ID: " + f.name + "\r\n";
				writer.write(txt);
				trigger = false;
			}
		}
		if (trigger) {
			txt = "No se encontraron funciones extensas \r\n";
			writer.write(txt);
		}
		trigger = true;	
		writer.write("-----------------------------------------------------------------------\r\n");
		for (fun f : loader.stats.funList) {
			if(f.parameters >= 4)   {
				txt = "Funcion con demasiados parametros (" + f.parameters + "), \t linea "  + f.line + ":" + f.col + "\t ID: " + f.name  + "\r\n";
				writer.write(txt);
				trigger = false;
			}
		}
		if (trigger) {
			txt = "No se encontraron funciones con demasiados parametros\r\n";
			writer.write(txt);
		}
		trigger= true;
		writer.write("-----------------------------------------------------------------------\r\n");
		for (fun f : loader.stats.funList) {
			if(!loader.set.contains(f.name) && f.name.charAt(0) != '_') {
				txt = "Funcion no usada en el codigo, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name  + "\r\n";
				writer.write(txt);
				trigger= false;
			}
		}
		if (trigger) {
			txt = "No se encontraron funciones inalcanzables\r\n";
			writer.write(txt);
		}
		trigger= true;
		writer.write("-----------------------------------------------------------------------\r\n");
		for (fun f : loader.stats.funList) {
			if(f.name.length() <= 3) {
				txt = "Funcion con nombre demasiado corto, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name  + "\r\n";
				writer.write(txt);
				trigger= false;
			}
		}
		if (trigger) {
			txt = "No se encontraron funciones con nombre demasiado corto\r\n";
			writer.write(txt);
		}
		trigger= true;
		writer.write("-----------------------------------------------------------------------\r\n");		
		for (fun f : loader.stats.funList) {
			if(f.name.length() >= 25) {
				txt = "Funcion con nombre demasiado largo, \t linea " + f.line + ":" + f.col + "\t ID: " + f.name  + "\r\n";
				writer.write(txt);
				trigger= false;
			}				
		}
		if (trigger) {
			txt = "No se encontraron funciones con nombre demasiado largo \r\n";
			writer.write(txt);
		}
		trigger= true;
		writer.write("-----------------------------------------------------------------------\r\n");
		for (clas f : loader.stats.clasList) {
			if(f.length >= 0.2*loader.stats.totalLen) {
				txt = "Clase demasiado extensa, \t linea" + f.line + ":" + f.col + "\t ID: " + f.name + "\r\n";
				writer.write(txt);
				trigger= false;
			}
		}
		if (trigger) {
			txt = "No se encontraron clases demasiado extensas\r\n";
			writer.write(txt);
		}trigger= true;
		writer.write("-----------------------------------------------------------------------\r\n");
			
		writer.close();		
		
	}

}
