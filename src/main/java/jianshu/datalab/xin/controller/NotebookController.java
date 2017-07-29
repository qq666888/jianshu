package jianshu.datalab.xin.controller;

import jianshu.datalab.xin.model.Notebook;
import jianshu.datalab.xin.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***         2017/7/24 00:09
 */
@Controller
@RequestMapping("notebook")
public class NotebookController extends BaseController {
    
    private NotebookService notebookService;

    @Autowired
    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @RequestMapping("create")
    private String create(Notebook notebook) {
        notebookService.create(notebook);
        return null; // TODO: 2017/7/24  
    }
}
