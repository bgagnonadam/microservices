package com.bgagnonadam.telephony.ws.api.calllog;

import java.util.List;

import com.bgagnonadam.telephony.ws.api.calllog.dto.CallLogDto;
import com.bgagnonadam.telephony.ws.client.CallLogApi;

public class CallLogResourceImpl implements CallLogResource {

  private CallLogApi callLogApi;

  public CallLogResourceImpl(CallLogApi callLogApi) {
    this.callLogApi = callLogApi;
  }

  @Override
  public List<CallLogDto> getCallLogs() {
    return callLogApi.findAll();
  }

  @Override
  public void deleteCallLog(String id) {
    callLogApi.remove(id);
  }
}
