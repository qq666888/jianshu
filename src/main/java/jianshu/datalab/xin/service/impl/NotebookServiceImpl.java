package jianshu.datalab.xin.service.impl;

import jianshu.datalab.xin.dao.GenericDao;
import jianshu.datalab.xin.model.Notebook;
import jianshu.datalab.xin.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:47
 * https://github.com/thu/jianshu/
 */
@Service
public class NotebookServiceImpl extends GenericServiceImpl<Notebook, Integer> implements NotebookService {

    @Override
    @Autowired
    @Qualifier("notebookDaoImpl")
    public void setGenericDao(GenericDao<Notebook, Integer> genericDao) {
        super.genericDao = genericDao;
    }
}
