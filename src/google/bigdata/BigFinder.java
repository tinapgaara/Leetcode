package google.bigdata;

public class BigFinder {
	
	private final int SIZE = 0x20000000; // 512M
	
	private byte[] mBitmap;
	private byte[] mBits = new byte[] { (byte) 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};
	
    public static void main(String[] args) {
    	BigFinder bigFinder = new BigFinder();
    	bigFinder.read(0);
    	bigFinder.read(5);
    	bigFinder.read(8);
    	bigFinder.read(11);
    	bigFinder.read(16);
    	
    	for (int i = 0; i < 33; i++) {
    	    System.out.println(bigFinder.find(i) ? ("found: " + i) : ("not-found: " + i) );
    	}
    }
    
	public BigFinder() {
		mBitmap = new byte[SIZE];
	}
	
	public void read(long num) {
		int index = (int) (num >> 3);
		int offset = (int) (num % 8);
		
		mBitmap[index] = (byte) (mBitmap[index] | mBits[offset]);
	}

	public boolean find(long num) {
		int index = (int) (num >> 3);
		int offset = (int) (num % 8);
		
		return ( ((mBitmap[index] & mBits[offset])) != 0 );
	}
	
}
