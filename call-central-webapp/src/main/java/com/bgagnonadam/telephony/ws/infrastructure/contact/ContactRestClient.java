package com.bgagnonadam.telephony.ws.infrastructure.contact;

import com.bgagnonadam.telephony.ws.domain.contact.Contact;
import com.bgagnonadam.telephony.ws.domain.contact.ContactNotFoundException;
import com.bgagnonadam.telephony.ws.domain.contact.ContactClient;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.WebTarget;

public class ContactRestClient implements ContactClient {

  private WebTarget contactWs;

  @Override
  public List<Contact> findAll() {
    // TODO should use the contactWs to fetch all contacts
    return Lists.newArrayList();
  }

  @Override
  public Contact findById(String id) {
    // TODO should use the contactWs to find the contact with ID
    return new Contact();
  }

  @Override
  public void update(Contact contact) throws ContactNotFoundException {
    // TODO should use the contactWs to update this contact
  }

  @Override
  public void save(Contact contact) {
    // TODO should use the contactWs to create contact
  }

  @Override
  public void remove(String id) {
    // TODO should use the contactWs to remove contact
  }
}
