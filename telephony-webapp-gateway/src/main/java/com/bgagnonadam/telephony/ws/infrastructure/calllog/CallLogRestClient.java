package com.bgagnonadam.telephony.ws.infrastructure.calllog;

import com.bgagnonadam.telephony.ws.domain.calllog.CallLog;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogClient;
import jersey.repackaged.com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class CallLogRestClient implements CallLogClient {
  
  private WebTarget callLogWs;

  public CallLogRestClient() {
    Client client = ClientBuilder.newClient();
    this.callLogWs = client.target("http://localhost:8082/api").path("calllogs");
  }

  @Override
  public List<CallLog> findAll() { 
    return callLogWs.request(MediaType.APPLICATION_JSON).get(new GenericType<List<CallLog>>(){});
  }


  @Override
  public void remove(String id) {
     callLogWs.path(id).request(MediaType.APPLICATION_JSON).delete();
  }
}
