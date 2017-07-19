package jianshu.datalab.xin.service;

import jianshu.datalab.xin.model.User;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:46
 * https://github.com/thu/jianshu/
 */
public interface UserService extends GenericService<User, Integer> {

    void signUp(User user);

    User queryUserByNick(String nick);

    User queryUserByMobile(String mobile);

    void signInUpdate(User user);

}
