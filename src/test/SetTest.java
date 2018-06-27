package test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		
		
		Set<String> setStr=new HashSet<>();
		setStr.add("adc");
		setStr.add("aee");
		setStr.add("111");
		String s="111";
		setStr.add(s);
		setStr.add("abc");
		System.out.println("test");
		Iterator<String> iter=setStr.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}
}
