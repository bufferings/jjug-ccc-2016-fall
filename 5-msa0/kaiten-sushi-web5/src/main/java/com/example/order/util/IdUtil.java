package com.example.order.util;

import java.util.UUID;
import java.util.regex.Pattern;

public class IdUtil {

  public static final Pattern ID_PATTERN = Pattern
      .compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

  public static String generateId() {
    return UUID.randomUUID().toString().toLowerCase();
  }

  public static boolean matchesIdFormat(String target) {
    if (target == null) {
      return false;
    }
    return ID_PATTERN.matcher(target).matches();
  }

}
