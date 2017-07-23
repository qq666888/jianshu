package jianshu.datalab.xin.dao.impl;

import jianshu.datalab.xin.dao.NoteDao;
import jianshu.datalab.xin.model.Note;
import org.springframework.stereotype.Repository;

/**
 * Created by mingfei.net@gmail.com
 * 7/7/17 14:14
 * https://github.com/thu/jianshu/
 */
@Repository
public class NoteDaoImpl extends GenericDaoImpl<Note, Integer> implements NoteDao {
}
