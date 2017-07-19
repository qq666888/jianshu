package jianshu.datalab.xin.service.impl;

import jianshu.datalab.xin.dao.GenericDao;
import jianshu.datalab.xin.model.User;
import jianshu.datalab.xin.service.UserService;
import jianshu.datalab.xin.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:47
 * https://github.com/thu/jianshu/
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    @Override
    @Autowired
    @Qualifier("userDaoImpl")
    public void setGenericDao(GenericDao<User, Integer> genericDao) {
        super.genericDao = genericDao;
    }

    @Override
    public void signUp(User user) {
        genericDao.create("sign", user);
    }

    @Override
    public User queryUserByNick(String nick) {
        return genericDao.queryOne("queryUserByNick", nick);
    }

    @Override
    public User queryUserByMobile(String mobile) {
        return genericDao.queryOne("queryUserByMobile", mobile);
    }

    @Override
    public void signInUpdate(User user) {
        genericDao.modify("signInUpdate", user);
    }
}
