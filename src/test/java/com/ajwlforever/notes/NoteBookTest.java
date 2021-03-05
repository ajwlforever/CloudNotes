package com.ajwlforever.notes;


import com.ajwlforever.notes.entity.Notebook;
import com.ajwlforever.notes.service.NotebookService;
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
public class NoteBookTest {

    @Autowired
    private NotebookService notebookService;

    @Test
    public void testList()
    {
       List<Notebook> list = notebookService.selectByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
        System.out.println(list);
    }

    @Test
    public void delete()
    {
        notebookService.deleteNotebook("282898ab-84e4-48c8-a09d-4002c513438f");
    }
}
