package com.norasoft.generator.picture;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

@Component
public class PictureRepository {

  private Map<Long, Picture> cache = new HashMap<>();
  private Long counter = 1l;
  private static final String pictureHost = "localhost";
  private static final String pictureProtocol = "http";
  private static final Integer picturePort = 7777;

  @PostConstruct
  private void init() throws MalformedURLException {
    RestTemplate restTemplate = new RestTemplate();
    Class<List<String>> clazz = (Class<List<String>>) (Class<?>) List.class;
    ResponseEntity<List<String>> listResponse = restTemplate.getForEntity(getResourceURL("list").toString(), clazz);
    List<String> list = listResponse.getBody();

    for (String filename : list) {
      save(new Picture(getImageName(filename), getResourceURL(filename)));
    }
  }

  public Optional<Picture> findOne(Long id) {
    return Optional.ofNullable(cache.getOrDefault(id, null));
  }

  public Iterable<Picture> findAll() {
    return cache.values();
  }

  public void save(Picture picture) {
    if (picture.getId() == null) {
      picture.setId(counter);
    }
    cache.put(counter++, picture);
  }

  private String getImageName(String input) {
    if (input.contains(".")) {
      return input.split("\\.")[0];
    }
    else {
      return input;
    }
  }

  private URL getResourceURL(String path) {
    try {
      URI uri = new URI(pictureProtocol, null, pictureHost, picturePort, "/"+path, null, null);
      return uri.toURL();
    }
    catch (Exception e) {
      throw new RuntimeException("error during URL creation, path: " + path);
    }
  }
}
