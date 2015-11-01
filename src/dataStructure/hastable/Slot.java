package dataStructure.hastable;

import java.util.ArrayList;

public class Slot {

	private ArrayList<Integer> m_list ;
	
	public Slot() {
	}
	
	public Slot(ArrayList<Integer> list)
	{
		m_list = list;
	}
	
	public void setList(ArrayList<Integer> list)
	{
		m_list = list;
	}
	
	public ArrayList<Integer> getList()
	{
		return m_list;
	}


}
