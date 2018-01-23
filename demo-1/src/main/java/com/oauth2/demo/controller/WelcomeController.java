package com.oauth2.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.oauth2.demo.model.Student;
import com.oauth2.demo.repo.StudentRepository;
import com.oauth2.demo.service.HelloWorldService;

/**
 * {@link Controller} for handling the CRUD operations.
 *
 * @author varunjai
 */
@Controller
public class WelcomeController {

  private final Logger logger = LoggerFactory
      .getLogger(WelcomeController.class);

  private final HelloWorldService helloWorldService;
  @Autowired
  private StudentRepository repository;

  @ModelAttribute("student")
  public Student getStudent() {
    return new Student();
  }

  @Autowired
  public WelcomeController(HelloWorldService helloWorldService) {
    this.helloWorldService = helloWorldService;
  }

  /**
   * This is the first page opened on login
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Map<String, Object> model) {
    logger.info("index() is executed");

    model.put("title", helloWorldService.getTitle(""));
    model.put("msg", helloWorldService.getDesc());

    return "home";
  }
  
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(Map<String, Object> model) {
    logger.info("index() is executed");

    model.put("title", helloWorldService.getTitle(""));
    model.put("msg", helloWorldService.getDesc());

    List<Student> students = repository.findAll();

    if (!students.isEmpty()) {
      model.put("students", students);
    }

    return "index";
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String register(Map<String, Object> model) {
    logger.info("register() is executed");

    model.put("title", helloWorldService.getTitle(""));
    model.put("msg", helloWorldService.getDesc());
    return "register";
  }

  @RequestMapping(value = "/persistStudent", method = RequestMethod.POST)
  public ModelAndView addStudent(HttpServletRequest request,
      HttpServletResponse response, @ModelAttribute("student") Student student)
      throws JAXBException, IOException, SAXException,
      ParserConfigurationException, TransformerException {

    logger.info("Persisting student");

    // persist Student object
    repository.save(student);

    ModelAndView model = new ModelAndView();
    model.setViewName("index");
    model.addObject("title", helloWorldService.getTitle(""));
    model.addObject("msg", helloWorldService.getDesc());

    List<Student> students = repository.findAll();

    if (!students.isEmpty()) {
      model.addObject("students", students);
    }

    return model;
  }

  /**
   * Update the student record by ID.
   *
   * @param request
   * @param response
   * @param student
   * @return
   * @throws JAXBException
   * @throws IOException
   * @throws SAXException
   * @throws ParserConfigurationException
   * @throws TransformerException
   */
  @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
  public ModelAndView updateStudentById(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("id") int id, String name,
      String age, String university, String course) {

    logger.info("Updating student: " + id);
    // new student
    Iterable<Student> itr = repository
        .findAll(Arrays.asList(String.valueOf(id)));

    if (itr == null || itr.iterator().hasNext() == false) {
      return new ModelAndView("error");
    }

    final Student student = itr.iterator().next();
    student.setAge(age);
    student.setUniversity(university);
    student.setCourse(course);

    ModelAndView model = new ModelAndView();
    model.setViewName("index");
    model.addObject("title", helloWorldService.getTitle(""));
    model.addObject("msg", helloWorldService.getDesc());

    List<Student> students = repository.findAll();

    if (!students.isEmpty()) {
      model.addObject("students", students);
    }

    return model;

  }

  /*
   * @RequestMapping(value = "/getUpdateStudent/{id}", method =
   * RequestMethod.GET) public ResponseEntity<Student>
   * getUpdateStudent(@PathVariable("id") String studentId) {
   * 
   * logger.info("update() is executed - $id {}");
   * 
   * // fetch student object from repo Iterable<Student> itr = repository
   * .findAll(Arrays.asList(String.valueOf(studentId)));
   * 
   * 
   * return new ResponseEntity<Student>(itr.iterator().next(), HttpStatus.OK); }
   */

  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String update(Map<String, Object> model) {

    logger.info("update() is executed");
    model.put("title", helloWorldService.getTitle(""));
    model.put("msg", helloWorldService.getDesc());

    return "update";
  }

  @PostMapping(value = "/getUpdateStudent")
  public String getUpdateStudent(@ModelAttribute("id") int id) {

    logger.info("update() is executed - $id {}");

    // fetch student object from repo
    Iterable<Student> itr = repository
        .findAll(Arrays.asList(String.valueOf(id)));

    return "index";
  }

}
