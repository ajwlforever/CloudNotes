package com.ajwlforever.notes.dao;

import com.ajwlforever.notes.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteMapper {
    List<Note>  selectByNotebookId(String cnNoteBookId);

    int insertNote(Note note);
     Note findById(String cnNoteId);

    int updateNote(String cnNoteId,String cnNoteTitle,String cnNoteBody,long cnNoteLastModifyTime);
    int updateNoteTypeId(String cnNoteId,String cnNoteTypeId);
}
