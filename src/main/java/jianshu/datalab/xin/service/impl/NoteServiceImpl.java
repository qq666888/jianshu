package jianshu.datalab.xin.service.impl;

import jianshu.datalab.xin.dao.GenericDao;
import jianshu.datalab.xin.model.Note;
import jianshu.datalab.xin.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:47
 * https://github.com/thu/jianshu/
 */
@Service
public class NoteServiceImpl extends GenericServiceImpl<Note, Integer> implements NoteService {

    @Override
    @Autowired
    @Qualifier("noteDaoImpl")
    public void setGenericDao(GenericDao<Note, Integer> genericDao) {
        super.genericDao = genericDao;
    }
}
