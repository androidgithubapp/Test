package com.example.idost.pojo;


public class ContactBean 
{
	private String Name;
	private String Phn;
	
	public ContactBean(String name,String phn)
	{
		this.Name=name;
		this.Phn=phn;
	}
	
	public String getPhn()
	{
		return Phn;
	}
	
	public String getName()
	{
		return Name;
	}
}
