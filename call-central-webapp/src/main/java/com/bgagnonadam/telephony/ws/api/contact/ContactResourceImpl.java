package com.bgagnonadam.telephony.ws.api.contact;

import java.util.List;

import com.bgagnonadam.telephony.ws.api.contact.dto.ContactDto;
import com.bgagnonadam.telephony.ws.client.ContactApi;

public class ContactResourceImpl implements ContactResource {

  private ContactApi contactApi;

  public ContactResourceImpl(ContactApi contactApi) {
    this.contactApi = contactApi;
  }

  @Override
  public List<ContactDto> getContacts() {
    return contactApi.findAll();
  }

  @Override
  public ContactDto getContact(String id) {
    return contactApi.findById(id);
  }

  @Override
  public void addContact(ContactDto contactDto) {
    contactApi.save(contactDto);
  }

  @Override
  public void updateContact(String id, ContactDto contactDto) {
    contactApi.update(contactDto);
  }

  @Override
  public void deleteContact(String id) {
    contactApi.remove(id);
  }
}
