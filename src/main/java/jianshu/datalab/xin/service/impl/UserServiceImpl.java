package jianshu.datalab.xin.service.impl;

import jianshu.datalab.xin.dao.GenericDao;
import jianshu.datalab.xin.model.User;
import jianshu.datalab.xin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 7/19/17 17:47
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
        genericDao.create("signUp", user);
    }

    @Override
    public User queryUserByNick(String nick) {
        return genericDao.query("queryUserByNick", nick);
    }

    @Override
    public User queryUserByMobile(String mobile) {
        return genericDao.query("queryUserByMobile", mobile);
    }

    @Override
    public void signInUpdate(User user) {
        genericDao.modify("signInUpdate", user);
    }
}
