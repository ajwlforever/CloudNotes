package com.ajwlforever.notes;

import com.ajwlforever.notes.entity.Note;
import com.ajwlforever.notes.service.NoteService;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CloudnotesApplication.class)

public class NoteTest {


    @Autowired
    private  NoteService noteService;


    @Test
    public  void testSelectNotes()
    {
        String noteBookId="0cd94778-4d52-486d-a35d-263b3cfe6de9";
        List<Note> list= noteService.selectByNotebookId(noteBookId);
        System.out.println(list);
    }
    @Test
    public  void testInsertNotes()
    {
        Note note =  new Note();
        note.setCnNoteId(CloudNotesUtil.generateUUID());
        note.setCnUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
        note.setCnNoteBookId("0cd94778-4d52-486d-a35d-263b3cfe6de9");

        note.setCnNoteStatusId("1");
        note.setCnNoteTypeId("1");
         note.setCnNoteTitle("sdsd");
        note.setCnNoteBody("dasdgiyagsdigauydgasuduyasgduyasd");
        note.setCnNoteCreateTime(System.currentTimeMillis());
        note.setCnNoteLastModifyTime(System.currentTimeMillis());

        noteService.insertNote(note);
    }

    @Test
    public  void updateNotes()
    {
        String cnNoteId = "eb08f452-2bce-498c-b32c-75103252718b";
        String newTitle = "kard";
        String body="真是个好团，somi棒棒哒！";
        long modify = System.currentTimeMillis();
        noteService.updateNote(cnNoteId,newTitle,body,modify);
    }


}
