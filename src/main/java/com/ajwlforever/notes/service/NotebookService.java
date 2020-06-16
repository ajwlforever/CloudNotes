package com.ajwlforever.notes.service;


import com.ajwlforever.notes.dao.NotebookMapper;
import com.ajwlforever.notes.entity.Notebook;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import com.ajwlforever.notes.utils.NoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotebookService {

    @Autowired
    private NotebookMapper notebookMapper;


    public NoteResult<List<Notebook>> loadBookList(String token)
    {
        String userId = token.split(":",2)[1];
        List<Notebook> notebookList = selectByUserId(userId);

        NoteResult<List<Notebook>> result = new NoteResult<>();

         result.setStatus(0);
         result.setMsg("查找笔记本列表成功！");
         result.setData(notebookList);
         return result;
    }
    public NoteResult<String> createABook(String token,String bookName)
    {
        String userId = token.split(":",2)[1];
        String bookId = CloudNotesUtil.generateUUID();
        Notebook notebook = new Notebook();
        notebook.setCnNotebookId(bookId);
        notebook.setCnNotebookCreateTime(new Date());
        notebook.setCnNotebookTypeId("5");
        notebook.setCnUserId(userId);
        notebook.setCnNotebookName(bookName);
        insertNotebook(notebook);

        NoteResult<String> result = new NoteResult<>();
        result.setStatus(0);
        result.setMsg("创建笔记本成功！");
        result.setData(bookId);
        return result;

    }
    public List<Notebook> selectByUserId(String userId)
    {
        return notebookMapper.selectByUserId(userId);
    }
    public Notebook selectByNotebookId(String id){
        return notebookMapper.selectByNotebookId(id);
    }
    public Notebook selectByName(String notebookName)
    {
        return notebookMapper.selectByName(notebookName);
    }

    public int insertNotebook(Notebook notebook)
    {
        return notebookMapper.insertNotebook(notebook);
    }

    public int updateNotebookTypeId(String id,String typeId)
    {
        return notebookMapper.updateNotebookTypeId(id,typeId);
    }
}
