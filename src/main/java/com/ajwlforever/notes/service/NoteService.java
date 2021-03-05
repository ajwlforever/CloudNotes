package com.ajwlforever.notes.service;


import com.ajwlforever.notes.dao.NoteMapper;
import com.ajwlforever.notes.entity.Note;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;

    public Note createNoteByName(String noteName,String bookId,String userId)
    {
        Note note =  new Note();
        note.setCnNoteId(CloudNotesUtil.generateUUID());
        note.setCnUserId(userId);
        note.setCnNoteBookId(bookId);

        note.setCnNoteStatusId("1");
        note.setCnNoteTypeId("1");
        note.setCnNoteTitle(noteName);
        note.setCnNoteBody("");
        note.setCnNoteCreateTime(System.currentTimeMillis());
        note.setCnNoteLastModifyTime(System.currentTimeMillis());

        insertNote(note);
        return  note;
    }
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
    public int RecycleNote(String cnNoteId,String cnNoteTypeId)
    {
        return noteMapper.updateNoteTypeId(cnNoteId,cnNoteTypeId);
    }
}
