package jianshu.datalab.xin.dao;

import jianshu.datalab.xin.util.Pagination;

import java.io.Serializable;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:21
 * https://github.com/thu/jianshu/
 */
public interface GenericDao<M extends Serializable, ID extends Number> {

    void create(M model);

    void create(String statement, Object parameter);

    void removeById(ID id);

    void remove(String statement, Object parameter);

    void modify(M model);

    void modify(String statement, Object parameter);

    M queryById(ID id);

    M query(String statement, Object parameter);

    Pagination<M> queryAll(int currentPage);

    Pagination<M> query(String statement, Object parameter, int currentPage);
}
