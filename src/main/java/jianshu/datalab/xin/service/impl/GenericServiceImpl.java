package jianshu.datalab.xin.service.impl;

import jianshu.datalab.xin.dao.GenericDao;
import jianshu.datalab.xin.service.GenericService;
import jianshu.datalab.xin.util.Pagination;

import java.io.Serializable;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:41
 * https://github.com/thu/jianshu/
 */
public abstract class GenericServiceImpl<M extends Serializable, ID extends Number> implements GenericService<M, ID> {

    GenericDao<M, ID> genericDao;

    @SuppressWarnings("unused")
    public abstract void setGenericDao(GenericDao<M, ID> genericDao);

    @Override
    public void create(M model) {
        genericDao.create(model);
    }

    @Override
    public void create(String statement, Object parameter) {
        genericDao.create(statement, parameter);
    }

    @Override
    public void removeById(ID id) {
        genericDao.removeById(id);
    }

    @Override
    public void remove(String statement, Object parameter) {
        genericDao.remove(statement, parameter);
    }

    @Override
    public void modify(M model) {
        genericDao.modify(model);
    }

    @Override
    public void modify(String statement, Object parameter) {
        genericDao.modify(statement, parameter);
    }

    @Override
    public M queryById(ID id) {
        return genericDao.queryById(id);
    }

    @Override
    public M query(String statement, Object parameter) {
        return genericDao.query(statement, parameter);
    }

    @Override
    public Pagination<M> queryAll(int currentPage) {
        return genericDao.queryAll(currentPage);
    }

    @Override
    public Pagination<M> query(String statement, Object parameter, int currentPage) {
        return genericDao.query(statement, parameter, currentPage);
    }
}
