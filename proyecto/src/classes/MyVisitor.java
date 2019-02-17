package classes;

import classes.Python3Parser.File_inputContext;
import classes.Python3Parser.FuncdefContext;
import classes.Python3Parser.Single_inputContext;
import classes.Python3Parser.TfpdefContext;

public class MyVisitor<T> extends Python3BaseVisitor<T> {

	
	public CodeStatistics stats = new CodeStatistics();
	
	@Override
	public T visitFile_input(File_inputContext ctx) {
		System.out.println("Primera regla");
		System.out.println(ctx.getText().length());
		stats.totalLen = ctx.getText().length();
		return super.visitFile_input(ctx);
	}
	
	@Override
	public T visitFuncdef(FuncdefContext ctx) {		
		System.out.println("Entro a una funcion " + ctx.NAME() + " line " + ctx.DEF().getSymbol().getLine() + ":" + ctx.DEF().getSymbol().getCharPositionInLine() );
		System.out.println(ctx.getText().length());
		int count_parameters = 0;	
		if (ctx.parameters().typedargslist() != null) {
			count_parameters = ctx.parameters().typedargslist().getChildCount();
			System.out.println( count_parameters + " ->  Numero de parametros");
		}
		int hash = 0;
		stats.add("" + ctx.NAME(), ctx.getText().length(),count_parameters,hash);
		
		return super.visitFuncdef(ctx);	
	}
	
	
	@Override
	public T visitTfpdef(TfpdefContext ctx) {
		
		return super.visitTfpdef(ctx);
	}

}
