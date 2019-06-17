package com.wei.myproject.controller;

import com.wei.myproject.dao.UserDao;
import com.wei.myproject.entity.User;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: wei
 * @Date:2019-06-14
 * @Description:com.wei.myproject.controller
 * @Version:1.0
 */
@Controller
public class UserController {
    @Autowired
    UserDao userDao;
    @ResponseBody
    @RequestMapping("testLayui/findAll")
    public String testLayui(HttpServletRequest httpServletRequest){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","i am msg");
        map.put("count",userDao.count());
       // map.put("data",userDao.findAll());
        String limit = httpServletRequest.getParameter("limit");
        String page = httpServletRequest.getParameter("page");
        System.out.println("接收到的"+limit+"page"+page);
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(limit));
        List<User> content = userDao.findAll(pageRequest).getContent();
        map.put("data",content);
        return JSONObject.toJSONString(map);
    }


}
