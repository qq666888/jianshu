package jianshu.datalab.xin.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:21
 * https://github.com/thu/jianshu/
 */
public interface GenericDao<M extends Serializable, ID extends Number> {

    void create(M model);

    void create(String statement, Object parameter);

    List<M> queryAll();

    List<M> queryList(String statement, Object parameter);

    M queryById(ID id);

    M queryOne(String statement, Object parameter);

    void modify(M model);

    void modify(String statement, Object parameter);

    void removeById(ID id);

    void remove(M model);
}
