package com.bgagnonadam.telephony.ws.domain.calllog;

import java.util.List;

public interface CallLogClient {

  List<CallLog> findAll();

  void remove(String id);
}
