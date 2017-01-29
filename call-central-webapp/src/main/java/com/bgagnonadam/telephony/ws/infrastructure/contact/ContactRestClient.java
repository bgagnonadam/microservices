package com.bgagnonadam.telephony.ws.infrastructure.contact;

import com.bgagnonadam.telephony.ws.domain.calllog.CallLog;
import com.bgagnonadam.telephony.ws.domain.contact.Contact;
import com.bgagnonadam.telephony.ws.domain.contact.ContactNotFoundException;
import com.bgagnonadam.telephony.ws.domain.contact.ContactClient;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class ContactRestClient implements ContactClient {

  private WebTarget contactWs;

  public ContactRestClient() {
    Client client = ClientBuilder.newClient();
    contactWs = client.target("http://localhost:8082/api").path("contacts");
  }
  @Override
  public List<Contact> findAll() {
    return contactWs.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Contact>>(){});
  }

  @Override
  public Contact findById(String id) {
    return contactWs.path(id).request(MediaType.APPLICATION_JSON).get(new GenericType<Contact>(){});
  }

  @Override
  public void update(Contact contact) throws ContactNotFoundException {
    Entity<Contact> entity = Entity.entity(contact, MediaType.APPLICATION_JSON_TYPE);
    
    contactWs.path(contact.getId()).request().put(entity);
  }

  @Override
  public void save(Contact contact) {
    Entity<Contact> entity = Entity.entity(contact, MediaType.APPLICATION_JSON_TYPE);
    
    contactWs.request().post(entity); 
  }

  @Override
  public void remove(String id) {
    contactWs.path(id).request().delete();
  }
}
