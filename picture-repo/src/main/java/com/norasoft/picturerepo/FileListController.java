package com.norasoft.picturerepo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class FileListController {

  @Autowired
  private ApplicationContext aC;

  @RequestMapping(path="list")
  public List<String> listAvailableFiles() throws IOException {
    return Arrays.asList(aC.getResources("classpath:/static/*")).stream()
      .map(r -> r.getFilename()).collect(Collectors.toList());
  }
}
