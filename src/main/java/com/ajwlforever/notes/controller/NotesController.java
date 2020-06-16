package com.ajwlforever.notes.controller;

import com.ajwlforever.notes.annotation.LoginRequired;
import com.ajwlforever.notes.entity.Notebook;
import com.ajwlforever.notes.service.NotebookService;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import com.ajwlforever.notes.utils.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class NotesController {

    @Autowired
    private NotebookService notebookService;

    @LoginRequired
    @RequestMapping(path = "/edit",method = RequestMethod.GET)
    public String edit(Model model)
    {
        return "/edit";
    }


    @RequestMapping(path ="/book/loadbooks",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<List<Notebook>> loadBookList(  HttpServletRequest request)
    {
        String token = CloudNotesUtil.getCookies(request,"token");
        NoteResult<List<Notebook>> result = notebookService.loadBookList(token);
        return  result;
    }

    @RequestMapping(path = "/book/add" ,method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<String> creatABook(String bookName,HttpServletRequest request)
    {
        String token = CloudNotesUtil.getCookies(request,"token");
        NoteResult<String> result = notebookService.createABook(token,bookName);
        return  result;


    }


//    @RequestMapping(path ="/notes",method = RequestMethod.POST)
//    @ResponseBody
//    public NoteResult
}
