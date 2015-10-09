package hastable;

import java.util.ArrayList;

public class HashTable{
	private Slot[] m_slots;
	private static final int LARGEST_PRIME = 19;
	private static final int PRIME_A = 3;
	private static final int PRIME_B = 4;
	private static final int MAX_SIZE = 20;
	
	public HashTable()
	{
		m_slots = new Slot[MAX_SIZE];
	}
	
	public int UniverseHashFunction(int key)
	{
		return ((PRIME_A * key + PRIME_B) % LARGEST_PRIME) % key;
	}
	
	public int HashFunction(int key)
	{
		return key % LARGEST_PRIME;
	}
	
	// Has collision
		public int HashFunctionDivision(int val)
		{
			return (val);
		}
		
		public int HashFunctionMultiple(int val) {
			double cst = 0.2;
			double remain = (val * cst)  - Math.floor((val * cst));
			return (int) Math.floor(MAX_SIZE * remain);
		}

	public void insert(int key, int value) {
		int hashcode = HashFunction(key);
		Slot slot = m_slots[hashcode];
		if(slot != null) {
			ArrayList<Integer> list = slot.getList();
			list.add(value);
		}
		else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(value);
			
			slot = new Slot(list);
			m_slots[hashcode] = slot;
		}
	}
	
	public int search(int key) {
		int hashcode = HashFunction(key);
		Slot slot = m_slots[hashcode];
		
		if(slot == null)
			return -1;
		else
			return slot.getList().get(0); // get a value randomly
	}
	
	public void delete(int key) {
		int hashcode = HashFunction(key);
		Slot slot = m_slots[hashcode];
		if(slot == null)
			return;
		else {
			slot = null; // can not do deletion
		}
	}
}

