package com.bgagnonadam.telephony.ws.client;

import java.util.List;

import com.bgagnonadam.telephony.ws.api.calllog.dto.CallLogDto;

public interface CallLogApi {

  List<CallLogDto> findAll();

  void remove(String id) ;

}
