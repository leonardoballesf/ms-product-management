package com.interview.service.msproductmanagement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static <T> T readAsJsonDataFromResourceFile(String fileName, Class<T> tdClass) {
    try {
      return mapper.readValue(
          TestUtil.class.getClassLoader().getResourceAsStream(fileName), tdClass);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}

