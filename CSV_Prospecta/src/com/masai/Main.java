package com.masai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void solve(String[] arr)
	{
		
		Map<String,String> map = new LinkedHashMap<>();
		
		for(int k=0; k<arr.length; k++)
		{
			
			String column = arr[k];
			String key = "";
			
			for(int i=0; i<column.length(); i++)
			{
				if(column.charAt(i) == ':')
					break;
				key += column.charAt(i);
			}
			
//		  taking value of the key :
			String value = "";
			int ind = 0;
			for(int i =0; i<column.length(); i++)
			{
				if(column.charAt(i) == ' ')
				{
					ind = i;
					break;
				}
			}
			value = column.substring( ind+1, column.length());
			
//			adding key values to the map ;
				map.put(key, value);

		}
		
//		solving the map now
		Set<String> keys = map.keySet();
		
		for(String key : keys)
		{
			
			String value = map.getOrDefault(key, "0");
			int ind = 1;
			int prevNumber = 0;
			
			while(ind<value.length() && value.length() > 1)
			{
				
				if(!Character.isDigit(value.charAt(ind)) && value.length() > 1)
				{
					
					switch(value.charAt(ind))
					{
					case '+':
						if(Character.isDigit(value.charAt(ind+1)))
						{
							
							int newNumber = Integer.parseInt(String.valueOf(value.charAt(ind+1)));
							int solve = sum1(prevNumber,newNumber);
							prevNumber = solve;
							ind = ind+2;
	
						}
						else
						{
							int newNumber = Integer.parseInt(map.get(value.substring(ind+1,ind+3)));
							int solve = sum1(prevNumber,newNumber);
							prevNumber = solve;
							ind = ind+3;
						}
						break;
					default :
						if(Character.isDigit(value.charAt(ind)))
						{
//							digit could more then 9 so it is recommended to take loop here also :
							prevNumber = Integer.parseInt(String.valueOf(value.charAt(ind))); 
							ind++;
						}
						else
						{
//							column could be more then 2 characters so taking loop till airthmetic operator :
							int y = ind;
							while(value.charAt(y) != '+' && value.charAt(y) != '-' && value.charAt(y) != '/'&& value.charAt(y) != '*')
							{
								y++;
							}

							prevNumber =  Integer.parseInt(map.get(value.substring(ind,y)));	
							ind = y;
							
						}
						break;
					}
					
				}
				else
				{
					prevNumber = Integer.parseInt(String.valueOf(value.charAt(ind)));
					ind++;
				}
				
			}
			if(prevNumber != 0)
				map.put(key,prevNumber+"");
			
		}
		
		System.out.println();
		System.out.println("==============================after solving===============================");
		System.out.println(map);
		
		
	}
	
	public static void main(String[] args) {
		
		File file = new File("src\\prospecta.csv");

		 try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String[] arr = reader.readLine().split(",");
			System.out.println("=================================given==============================");
			System.out.println();
			System.out.println(Arrays.toString(arr));
			solve(arr);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private static int minus1(int sum, int parseInt) {
		// TODO Auto-generated method stub
		return sum - parseInt;
	}

	private static int divide1(int sum, int parseInt) {
		// TODO Auto-generated method stub
		return sum / parseInt;
	}

	private static int multiple1(int sum, int parseInt) {
		// TODO Auto-generated method stub
		return sum * parseInt;
	}

	private static int sum1(int sum, int parseInt) {
		// TODO Auto-generated method stub
		return sum + parseInt;
	}
	
	
}
