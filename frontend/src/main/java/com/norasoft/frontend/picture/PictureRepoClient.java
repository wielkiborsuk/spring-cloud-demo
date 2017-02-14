package com.norasoft.frontend.picture;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("picture-repo")
public interface PictureRepoClient {
  @RequestMapping(path="/list")
  List<String> listPictures();
}
