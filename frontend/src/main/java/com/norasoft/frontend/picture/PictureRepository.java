package com.norasoft.frontend.picture;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

import com.norasoft.frontend.picture.Picture;
import com.norasoft.frontend.picture.PictureRepoClient;

@Component
public class PictureRepository {

  private Map<Long, Picture> cache = new HashMap<>();
  private Long counter = 1l;
  private boolean runOnce = false;

  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private PictureRepoClient pictureRepoClient;

  private void init() {
    if (runOnce) {
      return;
    }
    runOnce = true;

    List<String> list = pictureRepoClient.listPictures();

    for (String filename : list) {
      save(new Picture(getImageName(filename), getResourceURL(filename)));
    }
  }

  public Optional<Picture> findOne(Long id) {
    init();
    return Optional.ofNullable(cache.getOrDefault(id, null));
  }

  public Iterable<Picture> findAll() {
    init();
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
    ServiceInstance serviceInstance = discoveryClient.getInstances("picture-repo").get(0);

    try {
      URI uri = new URI("http", null, serviceInstance.getHost(), serviceInstance.getPort(), "/"+path, null, null);
      return uri.toURL();
    }
    catch (Exception e) {
      throw new RuntimeException("error during URL creation, path: " + path);
    }
  }
}
