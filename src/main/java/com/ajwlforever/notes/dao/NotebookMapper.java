package com.ajwlforever.notes.dao;

import com.ajwlforever.notes.entity.Notebook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotebookMapper {


    List<Notebook> selectByUserId(String cnUserId);
    Notebook selectByNotebookId(String cnNotebookId);
    Notebook selectByName(String cnNotebookName);

    int insertNotebook(Notebook notebook);

    int updateNotebookTypeId(String cnNotebookId,String cnNotebookTypeId);


}
