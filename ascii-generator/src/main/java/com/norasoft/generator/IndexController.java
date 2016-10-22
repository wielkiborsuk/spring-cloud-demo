package com.norasoft.generator;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import com.norasoft.generator.generator.AsciiService;
import com.norasoft.generator.generator.HtmlAscii;
import com.norasoft.generator.picture.Picture;
import com.norasoft.generator.picture.PictureRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    private static final ModelAndView NOT_FOUND = new ModelAndView("/404.html");
    private final PictureRepository pictureRepository;
    private final AsciiService asciiService;

    @Autowired
    public IndexController(PictureRepository pictureRepository, AsciiService asciiService) {
        this.pictureRepository = pictureRepository;
        this.asciiService = asciiService;
    }


    @RequestMapping(value="/pictures/{id}", method=RequestMethod.GET)
    public ModelAndView picture(@PathVariable Long id ) {
        return pictureRepository.findOne(id)
                .map(picture -> new ModelAndView("details", ImmutableMap.of("picture", picture)))
                .orElseGet(() -> NOT_FOUND);
    }


    @ResponseBody
    @RequestMapping(value="/pictures/{id}/ascii", method=RequestMethod.GET)
    public ResponseEntity<String> ascii(@PathVariable Long id) {
        return pictureRepository.findOne(id)
                .map(picture -> asciiService.generateAscii(picture.getUrl()))
                .map(generatedAscii -> new ResponseEntity<>(generatedAscii.asString(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView(
                "index",
                ImmutableMap.of("pictures", pictureRepository.findAll()));
    }
}
