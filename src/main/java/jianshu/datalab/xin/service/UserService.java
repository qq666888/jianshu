package jianshu.datalab.xin.service;

import jianshu.datalab.xin.model.User;

/**
 * 7/19/17 17:46
 */
public interface UserService extends GenericService<User, Integer> {

    void signUp(User user);

    User queryUserByNick(String nick);

    User queryUserByMobile(String mobile);

    void signInUpdate(User user);

}
