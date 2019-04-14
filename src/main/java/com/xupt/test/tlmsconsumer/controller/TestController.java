package com.xupt.test.tlmsconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xupt.tlms.pojo.*;
import com.xupt.tlms.service.*;
import com.xupt.tlms.vojo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController {

    @Reference
    private UserService userService;

    @Reference
    private TeamService teamService;

    @Reference
    private ResearchService researchService;

    @Reference
    private LiteratureService literatureService;

    @Reference
    private NotestService notestService;

    @Reference CommentService commentService;

    @GetMapping("/test/{name}")
    public User getPeople(@PathVariable("name") String name) {
        System.out.println("~~~~~");
        System.out.println(name);
        return userService.findByName(name);
    }
    @GetMapping("/login/")
    public User login() {

        return userService.login("test123","123456");
    }


    @GetMapping("/team/{id}")
    public TeamVo getTeam(@PathVariable("id") int id) {
        System.out.println("~~~~~");
        System.out.println(id);
        return teamService.findTeam(id);
    }

    @GetMapping("/addTeam")
    public Team add(){
        Team team = new Team();
        team.setBrief("大数据探索小队伍～～");
        team.setName("专注队");
        team.setCreaterId(1);
        team.setCreateTime(System.currentTimeMillis() / 1000);
        team = teamService.creatTeam(team);
        return team;
    }

    @GetMapping("/getResearch/{userId}")
    public List<ResearchVo> getResearchByUserId(@PathVariable("userId") int userId){
        return researchService.findByUserId(userId);
    }

    @GetMapping("/getTeam/{userId}")
    public List<TeamVo> getTeamByUserId(@PathVariable("userId") int userId){
        return teamService.findAllTeamByUserId(userId);
    }

    @GetMapping("/getLiterature/{researchId}")
    public List<Literature> getLiteratureByResearchId(@PathVariable("researchId") int researchId){
        return literatureService.getLiteratureByResearchId(researchId);
    }
    @GetMapping("/getNotes/{userId}")
    public List<NotesVo> getNotes(@PathVariable("userId") int userId){
       return  notestService.getSelfNotes(userId);
    }

    @GetMapping("/getLiteratureInfo/{literatureId}")
    public LiteratureVo getLiterature(@PathVariable("literatureId") int literatureId){
        return  literatureService.getLiteratureInfoById(literatureId);
    }
    @GetMapping("/addLiterature")
    public boolean addLiterature(){
//        Literature literature = new Literature();
//        literature.setName("大数据审美");
//        literature.setAuthor("大明");
//        literature.setPath("～～～～");
//        literature.setTime(System.currentTimeMillis());
//        literature.setYear("2015");
//        literature.setRemarks("如何以审美的眼光去看待大数据～～～");
//        List<KeyWord> list = new ArrayList<>();
//        KeyWord keyWord = new KeyWord();
//        keyWord.setId(1);
//        KeyWord keyWord1 = new KeyWord();
//        keyWord1.setId(3);
//        list.add(keyWord);
//        list.add(keyWord1);
//        literatureService.inputLiterature(literature,list,1);
       return  literatureService.getLiteratureByNameAndResearchId("大数据审美",1);
    }
    @GetMapping("/addNotes")
    public void addNotest(){
        Notes notes = new Notes();
        notes.setContent("大数据真美～～～");
        notes.setUserId(2);
        notes.setLiteratureId(4);
        notes.setCreateTime(System.currentTimeMillis());
        notes.setStatus(1);
        notestService.writeNotes(notes);
    }

    @GetMapping("/addComment")
    public void addComment(){
        Comment notes = new Comment();
        notes.setContent("你说大数据真美，求你给我讲讲怎么美了？该怎么看");
        notes.setUserId(2);
        notes.setCreateTime(System.currentTimeMillis()/1000);
        notes.setStatus(1);
        notes.setNotesId(2);
      commentService.wirteComment(notes);
    }

    @GetMapping("/getCommentByNotesId/{notesId}")
    public List<CommentVo> getCommentByNotesId(@PathVariable("notesId")int notesId){

        return commentService.getMyComment(notesId);
    }

}
