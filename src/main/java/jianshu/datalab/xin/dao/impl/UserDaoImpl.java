package jianshu.datalab.xin.dao.impl;

import jianshu.datalab.xin.dao.UserDao;
import jianshu.datalab.xin.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by mingfei.net@gmail.com
 * 7/7/17 14:14
 * https://github.com/thu/jianshu/
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {
}
