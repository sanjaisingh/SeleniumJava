package seleniumJava.Java;

import java.util.HashMap;
import java.util.Map;

public class CharacterOccurances {

	public static void main(String[] args) 
	{
		String chCount = "Hello Java";
		Map<Character, Integer> map = new HashMap<Character, Integer>();		
		for(int itr = 0; itr < chCount.length(); itr++)
		{
			char ch = chCount.charAt(itr);
			if(!map.containsKey(ch))
			{
				map.put(ch, 1);
			}
			else
			{
				map.put(ch, map.get(ch)+1);
			}
		}
		System.out.println(map);
		//*******************************************************
		String str = "java is good";
		str = str.replace(" ", "");
		char[] ch = str.toCharArray();
		int i, j;
		System.out.println(ch);
		for(i = 0; i < ch.length; i++)
		{
			int counter = 0;
			for(j = 0; j < ch.length; j++)
			{
				//System.out.println("i:"+i +",j:"+j+",ch["+i+"]:"+ch[i]+",ch["+j+"]:"+ch[j]);
				if(j < i && ch[i] == ch[j])
					break;
				if(ch[i] == ch[j])
					counter++;
				if(j == ch.length - 1)
					System.out.println(ch[i]+" is: "+counter+" times");
			}
		}
	}
}
