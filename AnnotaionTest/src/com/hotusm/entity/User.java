package com.hotusm.entity;

import com.hotusm.annctation.Constraints;
import com.hotusm.annctation.DBTable;
import com.hotusm.annctation.SQLFloat;
import com.hotusm.annctation.SQLInteger;
import com.hotusm.annctation.SQLString;
/**
 * user vo  用来测试自定义的注解
 * @author Hotusm
 *
 */
@DBTable(name="user")
public class User{
	
	@SQLInteger(constraints=@Constraints(primaryKey=true))
	private Integer id;
	@SQLString(name="last_name")
	private String lastName;
	@SQLString(name="first_name")
	private String firstName;
	@SQLInteger
	private Integer age;
	@SQLFloat
	private float price;
	@SQLString(2)
	private String sex;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
