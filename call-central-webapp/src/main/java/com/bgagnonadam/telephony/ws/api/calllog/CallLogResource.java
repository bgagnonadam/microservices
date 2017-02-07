package com.bgagnonadam.telephony.ws.api.calllog;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bgagnonadam.telephony.ws.api.calllog.dto.CallLogDto;

@Path("/telephony/calllogs")
public interface CallLogResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<CallLogDto> getCallLogs();

  @DELETE
  @Path("{id}")
  void deleteCallLog(@PathParam("id") String id);
}
