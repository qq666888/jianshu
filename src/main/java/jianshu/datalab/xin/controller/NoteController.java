package jianshu.datalab.xin.controller;

import jianshu.datalab.xin.model.Note;
import jianshu.datalab.xin.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *         2017/7/24 00:09
 */
@Controller
@RequestMapping("note")
public class NoteController extends BaseController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @RequestMapping("create")
    private String create(Note note) {
        noteService.create(note);
        return null; // TODO: 2017/7/24  
    }
}
