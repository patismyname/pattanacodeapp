package com.pattanacode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
  @Id
  private String id;

  private ERole name;

  public Role() {
	  this.id="";
	 
  }

  public Role(ERole name) {
    this.name = name;
  }

  public String getId() {
	  if(id==null) {
		  id="";
	  }//if
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }
}
