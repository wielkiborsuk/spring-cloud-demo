package com.norasoft.frontend.picture;


import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient("ascii-generator")
public interface AsciiServiceClient {
  @RequestMapping(path="/ascii", method = RequestMethod.POST)
  String generateAscii(@RequestBody String url);
}
