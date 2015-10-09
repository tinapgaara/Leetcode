package hastable;

public class OpenAddressHashTable{
	
	private Bucket[] m_buckets;
	private static final int MAX_SIZE = 20;
	private int m_size;

	public OpenAddressHashTable() {
		m_buckets = new Bucket[MAX_SIZE];
		m_size = 0;
	}
	
	public Bucket[] getBuckets()
	{
		return m_buckets;
	}
	
	
	public int HashFunction(int key, int i) {
		return key % MAX_SIZE;
	}
	
	
	// Eliminate Collision : Important !!!!
	public int OpenAddressLinearHashFunction(int key, int j) {
		int hash_1 = key % MAX_SIZE;
		return (hash_1 + j) % MAX_SIZE;
	}
	
	public int OpenAddressQuadradicHashFunction(int val, int j, int c1, int c2) {
		int hash_1 = val % MAX_SIZE;
		int key = hash_1 + c1* j + c2 * j * j;
		return key;
	}
	
	public int OpenAddressDoubleHashFunction(int val, int j) {
		int hash_1 = val % MAX_SIZE;
		int hash_2 = 2027 - (val % 2027);
		return (hash_1 + j*hash_2) % MAX_SIZE;
	}
	
	
	 public int insert(int key, int val) {

		 for(int i = 0; i < MAX_SIZE; i ++) {
			 int hashcode = OpenAddressLinearHashFunction(key, i); // i is the hashtable's size index
			 Bucket bucket = m_buckets[hashcode];
			 if (bucket == null) {
				 Bucket newbucket = new Bucket(key, val); // key -> value
				 m_buckets[hashcode] = newbucket;
				 m_size++;

				 return hashcode;
			 }
		 }
		 return -1;
	 }
	 
	 public int search(int key) {

		 for(int i = 0; i < MAX_SIZE; i ++) {
			 int hashcode = OpenAddressLinearHashFunction(key, i);
			 Bucket bucket = m_buckets[hashcode];
			 
			 if(bucket == null)
				 continue;
			 else {
				 if(bucket.getKey() == key) {
					 return bucket.getVal();
				 }
			 }
		 }
		 return -1;
	 }
	 
	 public void delete(int key) {

		 for(int i = 0;i < MAX_SIZE; i++) {
			 int hashcode = OpenAddressLinearHashFunction(key,i);
			 Bucket bucket = m_buckets[hashcode];
			 if(bucket == null)
				 continue;
			 else {
				 if(bucket.getKey() == key) {
					 for(int j = hashcode; j < m_size - 1; j++)
						 m_buckets[j] = m_buckets[j+1];
				 }
			 }
		 }
	 }
}

