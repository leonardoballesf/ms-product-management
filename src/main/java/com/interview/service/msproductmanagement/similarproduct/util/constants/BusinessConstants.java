package com.interview.service.msproductmanagement.similarproduct.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessConstants {
  public static final String RESILIENCE_LABEL = "default4j";
  public static final String COMMA = ",";
  public static final String PATTERN_STRING_TO_LIST = "[\\[\\]\\s]";
  public static final String CONTROLLER_START = "Get controller start ----> productId={}";
  public static final String CONTROLLER_ERROR = "Get controller error  ----> productId={}";
  public static final String CONTROLLER_SUCCESS =
      "Get controller success ---> productId={}";
}
