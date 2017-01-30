package com.bgagnonadam.telephony.ws.infrastructure.calllog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.bgagnonadam.telephony.ws.domain.calllog.CallLog;
import com.bgagnonadam.telephony.ws.domain.calllog.CallLogClient;
import com.bgagnonadam.telephony.ws.domain.calllog.UnableToRemoveCallLogException;

public class CallLogRestClient implements CallLogClient {
  
  private WebTarget callLogWs;

  public CallLogRestClient() {
    Client client = ClientBuilder.newClient();
    this.callLogWs = client.target("http://localhost:8081/api").path("calllogs");
  }

  @Override
  public List<CallLog> findAll() { 
    try{
      return callLogWs.request(MediaType.APPLICATION_JSON).get(new GenericType<List<CallLog>>(){});       
    }catch(ProcessingException exception){
      return new ArrayList<>();
    }
  }


  @Override
  public void remove(String id) throws UnableToRemoveCallLogException {
    try{
      callLogWs.path(id).request(MediaType.APPLICATION_JSON).delete();
    }catch(ProcessingException exception){
      throw new UnableToRemoveCallLogException();
  }
  }
}
