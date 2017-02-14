package com.norasoft.frontend.picture;

import org.springframework.stereotype.Component;

@Component
public class AsciiServiceFallbackClient implements AsciiServiceClient {
  public String generateAscii(String url) {
    return "failed to generate the ASCII representation, please try again later";
  }
}
