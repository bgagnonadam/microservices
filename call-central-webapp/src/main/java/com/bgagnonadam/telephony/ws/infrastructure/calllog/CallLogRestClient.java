package com.bgagnonadam.telephony.ws.infrastructure.calllog;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.bgagnonadam.telephony.ws.domain.calllog.CallLog;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogClient;

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
