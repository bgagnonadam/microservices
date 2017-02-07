package com.bgagnonadam.telephony.ws.client;

import java.util.List;

import com.bgagnonadam.telephony.ws.api.contact.dto.ContactDto;

public interface ContactApi {

  List<ContactDto> findAll();

  ContactDto findById(String id);

  void save(ContactDto contact);

  void update(ContactDto contact);

  void remove(String id);


}
