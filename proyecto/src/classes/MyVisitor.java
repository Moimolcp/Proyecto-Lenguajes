package classes;

import java.util.HashSet;
import java.util.Set;

import classes.Python3Parser.*;
public class MyVisitor<T> extends Python3BaseVisitor<T> {

	
	public CodeStatistics stats = new CodeStatistics();
	public Set<String> set = new HashSet<String>();
	
	
	@Override
	public T visitFile_input(File_inputContext ctx) {
		//System.out.println("Primera regla");
		//System.out.println(ctx.getText().length());
		stats.totalLen = ctx.getSourceInterval().length();
		//System.out.println(ctx.getSourceInterval().length());
		return super.visitFile_input(ctx);
	}
	
	@Override
	public T visitFuncdef(FuncdefContext ctx) {
				
		int c = ctx.DEF().getSymbol().getCharPositionInLine();
		int l = ctx.DEF().getSymbol().getLine();
		//System.out.println("Entro a una funcion " + ctx.NAME() + " line " + l + ":" + c);
		//System.out.println(ctx.getText().length());
		//System.out.println(ctx.getSourceInterval().length());
		int count_parameters = 0;	
		if (ctx.parameters().typedargslist() != null) {
			count_parameters = ctx.parameters().typedargslist().getChildCount();
			//System.out.println( count_parameters + " ->  Numero de parametros");
		}
		int hash = 0;
		stats.addfun("" + ctx.NAME(), ctx.getSourceInterval().length(),count_parameters,hash,l,c);
		
		return super.visitFuncdef(ctx);	
	}
	
	@Override
	public T visitClassdef(ClassdefContext ctx) {
		int c = ctx.CLASS().getSymbol().getCharPositionInLine();
		int l = ctx.CLASS().getSymbol().getLine();
		//System.out.println("Entro a una clase " + ctx.NAME() + " line " + l + ":" + c);
		//System.out.println(ctx.getText().length());
		int count_parameters = 0;
		int hash = 0;	
		stats.addclas("" + ctx.NAME(), ctx.getSourceInterval().length(),count_parameters,hash,l,c);		
		
		return super.visitClassdef(ctx);
	}
	
	@Override
	public T visitAtom(AtomContext ctx) {
		if(ctx.NAME() != null) set.add(ctx.NAME().getText());
		return super.visitAtom(ctx);
	}
	
	@Override
	public T visitTrailer(TrailerContext ctx) {
		if(ctx.NAME() != null) set.add(ctx.NAME().getText());
		return super.visitTrailer(ctx);
	}
	
	

}
