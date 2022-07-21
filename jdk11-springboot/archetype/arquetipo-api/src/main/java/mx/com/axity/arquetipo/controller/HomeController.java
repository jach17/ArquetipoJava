package mx.com.axity.arquetipo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author guillermo.segura@axity.com
 *
 */
@Controller
public class HomeController
{
  @Value("${spring.application.name}")
  private String applicationName;
  
  @GetMapping("/")
  public ModelAndView hello()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName( "index" );
    mv.addObject( "message", applicationName );
    return mv;
  }
  
}
