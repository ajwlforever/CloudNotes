package com.ajwlforever.notes.controller;

import com.ajwlforever.notes.annotation.LoginRequired;
import com.ajwlforever.notes.entity.Note;
import com.ajwlforever.notes.entity.Notebook;
import com.ajwlforever.notes.service.NoteService;
import com.ajwlforever.notes.service.NotebookService;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import com.ajwlforever.notes.utils.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
    @Autowired
    private NoteService noteService;
    @LoginRequired
    @RequestMapping(path = {"/edit","/"},method = RequestMethod.GET)
    public String edit(Model model)
    {
        return "/edit";
    }

    @LoginRequired
    @RequestMapping(path ="/book/loadbooks",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<List<Notebook>> loadBookList(  HttpServletRequest request)
    {
        String token = CloudNotesUtil.getCookies(request,"token");
        NoteResult<List<Notebook>> result = notebookService.loadBookList(token);
        return  result;
    }

    @LoginRequired
    @RequestMapping(path = "/book/add" ,method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<String> creatABook(String bookName,HttpServletRequest request)
    {
        String token = CloudNotesUtil.getCookies(request,"token");
        NoteResult<String> result = notebookService.createABook(token,bookName);
        return  result;
    }
    @LoginRequired
    @RequestMapping(path = "/loadNotes" , method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<List<Note>> loadNotes(String bookId )
    {

        List<Note> noteList = noteService.selectByNotebookId(bookId);
        NoteResult<List<Note>> result = new NoteResult<>();
        result.setStatus(0);
        result.setMsg("笔记查询成功");
        result.setData(noteList);
        return  result;
    }

    @LoginRequired
    @RequestMapping(path ="/loadNoteContent",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<Note> loadNoteContent(String noteId)
    {
        Note note = noteService.findById(noteId);
        NoteResult<Note> result = new NoteResult<>();

        result.setStatus(0);
        result.setMsg("加载笔记内容成功");
        result.setData(note);

        return  result;


    }

    @LoginRequired
    @RequestMapping(path ="/addNote",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<Note> addNote(String noteName, String bookId, @CookieValue("token")String token)
    {
        String userId = token.split(":",2)[1];
        Note note = noteService.createNoteByName(noteName,bookId,userId);
        NoteResult<Note> result = new NoteResult<>();
        result.setStatus(0);
        result.setMsg("创建笔记成功");
        result.setData(note);

        return result;
    }

    @LoginRequired
    @RequestMapping(path ="/saveNote",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<String> saveNote(String noteId,String noteTitle,String noteBody)
    {
        noteService.updateNote(noteId,noteTitle,noteBody,System.currentTimeMillis());
        NoteResult<String> result = new NoteResult<>();
        result.setStatus(0);
        result.setMsg("保存笔记成功!");
        return  result;
    }

    @LoginRequired
    @RequestMapping(path ="/recycleNote",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<String> recycleNote(String noteId)
    {
        noteService.RecycleNote(noteId,"2");
        NoteResult<String>  result = new NoteResult<> ();
        result.setStatus(0);
        result.setMsg("笔记回收成功！");
        return  result;
    }

    @LoginRequired
    @RequestMapping(path ="/recycleNotebook",method = RequestMethod.POST)
    @ResponseBody
    public NoteResult<String>  recycleNotebook(String bookId)
    {
        notebookService.deleteNotebook(bookId);
        NoteResult<String>  result = new NoteResult<> ();
        result.setStatus(0);
        result.setMsg("笔记本彻底删除成功！");
        return  result;
    }

}
