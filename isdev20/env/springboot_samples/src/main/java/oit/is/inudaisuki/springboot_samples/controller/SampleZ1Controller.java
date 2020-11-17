package oit.is.inudaisuki.springboot_samples.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.inudaisuki.springboot_samples.model.UserDetail;
import oit.is.inudaisuki.springboot_samples.model.UserDetailMapper;

@Controller
@RequestMapping("/sampleZ1")
public class SampleZ1Controller {

  @Autowired
  UserDetailMapper udMapper;

  @GetMapping("step1")
  public String sampleZ1(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    model.addAttribute("login_user", loginUser);
    UserDetail ud = udMapper.getUserDetaiById(loginUser);
    model.addAttribute("user_name", ud.getName());
    model.addAttribute("job_id", ud.getJobId());

    return "sampleZ1.html";
  }
}
