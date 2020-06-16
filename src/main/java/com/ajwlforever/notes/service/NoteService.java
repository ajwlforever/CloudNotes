package com.ajwlforever.notes.service;


import com.ajwlforever.notes.dao.NoteMapper;
import com.ajwlforever.notes.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;

    public List<Note> selectByNotebookId(String cnNoteBookId){
        return noteMapper.selectByNotebookId(cnNoteBookId);
    }
    public   int insertNote(Note note){
        return noteMapper.insertNote(note);
    }
    public Note findById(String cnNoteId)
    {
        return noteMapper.findById(cnNoteId);
    }
    public  int updateNote(String cnNoteId,String cnNoteTitle,String cnNoteBody,long cnNoteLastModifyTime)
    {
        return noteMapper.updateNote(cnNoteId,cnNoteTitle,cnNoteBody,cnNoteLastModifyTime);
    }
}
