package com.wei.myproject.controller;

import com.wei.myproject.dao.PostDao;
import com.wei.myproject.dao.UserDao;
import com.wei.myproject.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: wei
 * @Date:2019-05-07
 * @Description:com.wei.myproject.controller
 * @Version:1.0
 */
@Controller
public class PostController {
    @Autowired
    PostDao postDao;

  /*  @GetMapping("/posts")
    public String list(Model model){
        List<Post> all = personDao.findAll();
        model.addAttribute("all",all);
        return "list";
    }*/

  //根据分页查询
   @GetMapping("/posts/{pageNo}")
   public String list(Model model,@PathVariable("pageNo") Integer pageNo){
       Integer pageSize=3;
       if(pageNo==null||pageNo<0){
           pageNo=0;
       }


       PageRequest pageRequest = new PageRequest(pageNo, pageSize);
       Page<Post> all = postDao.findAll(pageRequest);
       int numberOfElements = all.getTotalPages();

       System.out.println("总页数"+numberOfElements);
       model.addAttribute("all",all);
       return "list";
   }
   //添加
    @PostMapping("/post")
    public String list(Post post){
        postDao.save(post);
        return "redirect:/posts/0";
    }
    //根据id获取
    @GetMapping("/post/{id}")
    public String queryOneById(@PathVariable("id") Integer id,Model model){
        Optional<Post> postDaoById = postDao.findById(id);
        model.addAttribute("list",postDaoById);
        return "update";
    }

    //修改
    @PutMapping("/post")
    public String update(Post post){
        Optional<Post> byId = postDao.findById(post.getId());
        Post post1 = byId.get();
       post1.setName(post.getName());
       post1.setMoney(post.getMoney());
       post1.setAddress(post.getAddress());
       post1.setLinkman(post.getLinkman());
       post1.setLinkphone(post.getLinkphone());
       post1.setMiaoshu(post.getMiaoshu());
        postDao.saveAndFlush(post1);
        return "redirect:/posts/0";
    }

    //删除
    @DeleteMapping("/post/{id}/{pageNum}")
    public String deleteOne(@PathVariable("id") Integer id,@PathVariable("pageNum")Integer pageNum){
        postDao.deleteById(id);
        return "redirect:/posts/"+pageNum;
    }


}
