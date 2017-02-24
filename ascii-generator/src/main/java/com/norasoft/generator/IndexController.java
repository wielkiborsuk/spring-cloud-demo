package com.norasoft.generator;


import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;


import com.norasoft.generator.generator.AsciiService;

@Controller
public class IndexController {
    private final AsciiService asciiService;

    @Autowired
    public IndexController(AsciiService asciiService) {
        this.asciiService = asciiService;
    }


    @ResponseBody
    @RequestMapping(value="/ascii", method=RequestMethod.POST)
    public ResponseEntity<String> asciiByUrl(@RequestBody String urlString) {
      try {
        URL url = new URL(urlString);
        return new ResponseEntity<>(asciiService.generateAscii(url).asString(), HttpStatus.OK);
      }
      catch (MalformedURLException e) {
        return new ResponseEntity<>("malformed url passed", HttpStatus.BAD_REQUEST);
      }
    }
}
