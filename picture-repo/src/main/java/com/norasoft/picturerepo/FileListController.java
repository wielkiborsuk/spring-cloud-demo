package com.norasoft.picturerepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class FileListController {

  @RequestMapping(path="list")
  public List<String> listAvailableFiles() throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("static");
    BufferedReader br = new BufferedReader(new InputStreamReader(is));

    String resource;
    List<String> res = new ArrayList<String>();

    while( (resource = br.readLine()) != null ) {
      res.add(resource);
    }

    return res;
  }
}
