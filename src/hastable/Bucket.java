package hastable;

import java.util.ArrayList;

public class Bucket {
	
	private int m_val;
	private int m_key;
	
	public Bucket() {
		//nothing to do here
	}
	
	public Bucket(int val, int key) {
		m_val = val;
		m_key = key;
	}
	
	public void setVal(int val)
	{
		m_val = val;
	}
	
	public int getVal()
	{
		return m_val;
	}
	
	public void setKey(int key)
	{
		m_key = key;
	}
	
	public int getKey()
	{
		return m_key;
	}

}
