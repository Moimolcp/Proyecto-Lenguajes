package classes;

import java.util.*;



public class CodeStatistics {
	
	public class fun {
		public String name;
		public int length;
		public int parameters;
		public int hash;
		public int line;
		public int col;
		
		public fun(String name ,int lenght, int parameters, int hash,int line,int col) {
			this.name = name;
			this.length = lenght;
			this.parameters = parameters;
			this.hash = hash;
			this.line = line;
			this.col = col;
		}
		
	}
	
	public class clas {
		public String name;
		public int length;
		public int parameters;
		public int hash;
		public int line;
		public int col;
		
		
		public clas(String name ,int lenght, int parameters, int hash,int line, int col) {
			this.name = name;
			this.length = lenght;
			this.parameters = parameters;
			this.hash = hash;
			this.line = line;
			this.col = col;
		}
		
	}
	
	public static Comparator<fun> compfun = new Comparator<fun>() {
        @Override public int compare(fun p1, fun p2) {
            return p1.length - p2.length; // Ascending
        }
    };
    
    public static Comparator<clas> compclas = new Comparator<clas>() {
        @Override public int compare(clas p1, clas p2) {
            return p1.length - p2.length; // Ascending
        }
    };
	
	public ArrayList<fun> funList = new ArrayList<CodeStatistics.fun>();
	public ArrayList<clas> clasList = new ArrayList<CodeStatistics.clas>();
	public int totalLen = 0;  
	
	public void addfun(String name, int length, int parameters,int hash,int l,int c) {		
		funList.add(new fun(name,length,parameters,hash,l,c));
	}
	
	public void addclas(String name, int length, int parameters,int hash,int l,int c) {		
		clasList.add(new clas(name,length,parameters,hash,l,c));
	}

}
