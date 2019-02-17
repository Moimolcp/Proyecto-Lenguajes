package classes;

import java.util.*;



public class CodeStatistics {
	
	public class fun {
		public String name;
		public int length;
		public int parameters;
		public int hash;
		
		public fun(String name ,int lenght, int parameters, int hash) {
			this.name = name;
			this.length = lenght;
			this.parameters = parameters;
			this.hash = hash;
		}
		
	}
	
	public static Comparator<fun> compfun = new Comparator<fun>() {
        @Override public int compare(fun p1, fun p2) {
            return p1.hash - p2.hash; // Ascending
        }
    };
	
	
	public ArrayList<fun> funList = new ArrayList<CodeStatistics.fun>();
	public int totalLen = 0;  
	
	public void add(String name, int length, int parameters,int hash) {		
		funList.add(new fun(name,length,parameters,hash));
	}

}
