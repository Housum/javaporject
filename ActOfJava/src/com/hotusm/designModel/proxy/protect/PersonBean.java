package com.hotusm.designModel.proxy.protect;

public class PersonBean implements IPersonBean{

	private String name;
	private String gender;
	private String interests;
	private int rating;
	private int ratingCount=0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public int getHotOrNotRating(){
		if(rating==0)return 0;
		
		return (rating/ratingCount);
	}
	
	public void setHotOrNotRating(int rating){
		
		this.rating+=rating;
		ratingCount++;
	}
}
