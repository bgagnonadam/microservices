package com.bgagnonadam.contact.ws.api.dto;

public class ContactDto {
  public String id;
  public String telephoneNumber;
  public String address;
  public String name;
  public String birthday;

  @Override
  public String toString() {
    return "ContactDto{" +
            "id='" + id + '\'' +
            ", telephoneNumber='" + telephoneNumber + '\'' +
            ", address='" + address + '\'' +
            ", name='" + name + '\'' +
            ", birthday='" + birthday + '\'' +
            '}';
  }
}
