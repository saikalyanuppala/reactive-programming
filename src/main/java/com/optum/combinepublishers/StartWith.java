package com.optum.combinepublishers;

import com.optum.util.NameGenerator;
import com.optum.util.Util;

public class StartWith {
	public static void main(String[] args) {

	NameGenerator generator=new NameGenerator();
	     
	      generator
	      .generateNames()
		  .take(2)
		  .subscribe(Util.subscriber("Sam"));
	      
	      generator
	      .generateNames()
		  .take(2)
		  .subscribe(Util.subscriber("Mike"));
	      
	      generator
	      .generateNames()
		  .take(3)
		  .subscribe(Util.subscriber());
	      
	      generator
	      .generateNames()
	      .filter(e -> e.startsWith("A"))
		  .take(1)
		  .subscribe(Util.subscriber());
	}
}
