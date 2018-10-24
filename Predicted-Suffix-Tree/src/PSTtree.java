import java.util.*;
public class PSTtree<E> {
	ArrayList<E> data = new ArrayList<E>();
	ArrayList<ArrayList<E>> suffix = new ArrayList<ArrayList<E>>();
	ArrayList<E> temp = new ArrayList<E>();
	int length = 3;
	
	
	void addToTree(ArrayList<E> input)
	{
		
		
	}
	
	void createSuffix()
	{
		for(int i = 0; i<data.size()-length; i++)
		{
			for(int j = i; j < i+length; j++)
			{
				temp.add(data.get(j));
			}
			while (ifSuffix(temp) != true)
			{
				suffix.add(temp);
			}
			System.out.println(temp);
			temp.clear();
		}
	}
	
	void setData(ArrayList<E> input)
	{
		data = input;
	}
	
	void printData()
	{
		for (int i = 0; i < data.size(); i++)
		{
			System.out.println(data.get(i));
		}
	}
	
	void printSuffix()
	{
		for (int i = 0; i < suffix.size(); i++)
		{
			System.out.println(suffix.get(i));
		}
	}
	
	
	boolean ifSuffix(ArrayList<E> word)
	{
		if(suffix.contains(word))
		{
			return true;
		}
		return false;
	}
	
	

}
