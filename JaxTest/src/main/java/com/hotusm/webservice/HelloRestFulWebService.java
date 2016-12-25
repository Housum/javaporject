package com.hotusm.webservice;


import java.util.UUID;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hotusm.entity.User;
import com.hotusm.utils.JsonMapper;

@Path("/demoresful")
public class HelloRestFulWebService {

	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String webDemo(){
		User user=new User();
		user.setAge(22);
		String id=UUID.randomUUID().toString().replace("-", "");
		user.setId(id);
		user.setName("hotusm");
		
		return JsonMapper.toJsonString(user);
	}
	@POST
	@Path("/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertUser(@PathParam(value="userId") String userId){
		System.out.println("传进来的是:"+userId);
		return "运行成功";
	}
//	@POST
//	@Path("/test")
//	@Consumes(value={"text/xml", "application/json"})
//	public <T> T save(@WebParam(name="entity")T entity){
//		
//		return null;
//	}
}
