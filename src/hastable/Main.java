package hastable;

import java.util.Hashtable;
import java.util.Iterator;

public class Main {
	
	public static void main(String[] args)
	{
		HashTable map = new HashTable();
		
		map.insert(1);
		map.insert(2);
		map.insert(2);
		System.out.println("****************** Hash Table Using List(Collision)*************");
		System.out.println("After Insertion:");
		Slot[] vals = map.getSlots();
		for(int i=0;i<vals.length;i++)
		{
			if(vals[i] != null)
				System.out.println(i+":"+vals[i].getList());
		}
		
		System.out.println("Search:"+map.search(1));
		System.out.println("Search:"+map.search(2));
		
		map.delete(2);
		System.out.println("After Deletion:");
		vals = map.getSlots();
		for(int i=0;i<vals.length;i++)
		{
			if(vals[i] != null)
				System.out.println(i+":"+vals[i].getList());
		}
		
		
		OpenAdHashTable map_2 = new OpenAdHashTable();
		
		map_2.insert(1);
		map_2.insert(2);
		map_2.insert(2);
		System.out.println("****************** Hash Table Using Open Addressing *************");
		System.out.println("After Insertion:");
		Bucket[] buckets = map_2.getBuckets();
		for(int i=0;i<buckets.length;i++)
		{
			if(buckets[i] != null)
				System.out.println(i+":"+buckets[i].getVal());
		}
		
		System.out.println("Search Location:"+map_2.search(1));
		System.out.println("Search Location:"+map_2.search(2));
		
		map_2.delete(2);
		System.out.println("After Deletion:");
		buckets = map_2.getBuckets();
		for(int i=0;i<buckets.length;i++)
		{
			if(buckets[i] != null)
				System.out.println(i+":"+buckets[i].getVal());
		}
		

	}

}
