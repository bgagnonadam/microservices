package com.bgagnonadam.calllog.ws.domain;

import com.bgagnonadam.calllog.ws.api.dto.CallLogDto;

public class CallLogAssembler {
  public CallLogDto create(CallLog callLog) {
    CallLogDto callLogDto = new CallLogDto();
    callLogDto.id = callLog.getId();
    callLogDto.telephoneNumber = callLog.getTelephoneNumber();
    callLogDto.date = callLog.getDate();
    callLogDto.durationInSeconds = callLog.getDurationInSeconds();
    return callLogDto;
  }
}
