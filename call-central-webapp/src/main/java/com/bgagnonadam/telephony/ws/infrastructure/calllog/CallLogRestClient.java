package com.bgagnonadam.telephony.ws.infrastructure.calllog;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.bgagnonadam.telephony.ws.api.calllog.dto.CallLogDto;
import com.bgagnonadam.telephony.ws.client.CallLogApi;

public class CallLogRestClient implements CallLogApi {

  private WebTarget callLogWs;

  public CallLogRestClient(String url) {
    Client client = ClientBuilder.newClient();
    this.callLogWs = client.target(url).path("calllogs");
  }

  @Override
  public List<CallLogDto> findAll() {
    try {
      return callLogWs.request(MediaType.APPLICATION_JSON).get(new GenericType<List<CallLogDto>>() {
      });
    } catch (ProcessingException exception) {
      return new ArrayList<>();
    }
  }

  @Override
  public void remove(String id) {
    callLogWs.path(id).request(MediaType.APPLICATION_JSON).delete();
  }
}
