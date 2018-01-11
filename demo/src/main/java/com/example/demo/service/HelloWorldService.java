package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloWorldService {

  private final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);
  
  public String getDesc(){
    logger.info("getDesc()");
    return "Demo Student Registration Application";
  }
  
  public String getTitle(String name){
    logger.info("getTitle() is executed! $name: {}", name);
    if(StringUtils.isEmpty(name)){
      return "Hello User";
    }
    
    return "Hello " + name;
  }
  
}
