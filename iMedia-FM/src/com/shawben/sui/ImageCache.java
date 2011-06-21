package com.shawben.sui;

public class ImageCache {

	//“ª∏ˆÕº∆¨ª∫¥Ê¿‡
	private int id;
	private String name;
	private int hot;
	private String category;
	private String link;
	private String path;
	public int getId(){
		return id;
	}
	public int getHot(){
		return hot;
	}
	public String getName(){
		return name;
		
	}
	public String getCategory(){
		return category;
	}
	public String getLink(){
		return link;
	}
	public String getPath(){
		return path;
	}
	public void setName(String n){
		name = n;
	}
	public void setHot(int iHot){
		hot = iHot;
	}
	public void setId(int mId){
		id = mId;
	}
	public void setCategory(String n){
		category = n;
	}
	public void setLink(String l){
		link = l;
	}
	public void setPath(String P){
		path = P;
	}
}
