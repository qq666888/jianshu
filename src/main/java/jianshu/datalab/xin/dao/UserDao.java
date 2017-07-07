package jianshu.datalab.xin.dao;

import jianshu.datalab.xin.model.User;

/**
 * Created by mingfei.net@gmail.com
 * 7/7/17 14:09
 * https://github.com/thu/jianshu/
 */
public interface UserDao {

    void signUp(User user);

    User queryUserByNick(String nick);

    User queryUserByMobile(String mobile);

    void signInUpdate(User user);
}
