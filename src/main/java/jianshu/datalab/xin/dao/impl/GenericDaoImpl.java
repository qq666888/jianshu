package jianshu.datalab.xin.dao.impl;

import jianshu.datalab.xin.dao.GenericDao;
import jianshu.datalab.xin.util.Constant;
import jianshu.datalab.xin.util.Pagination;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by mingfei.net@gmail.com
 * 7/19/17 17:24
 * https://github.com/thu/jianshu/
 */
public class GenericDaoImpl<M extends Serializable, ID extends Number> implements GenericDao<M, ID> {

    @Autowired // TODO: 2017/7/23 warning
    private SqlSession sqlSession;
    private String namespace;

    GenericDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
        namespace = StringUtils.uncapitalize(clazz.getSimpleName());
    }

    @Override
    public void create(M model) {
        sqlSession.insert(getStatement("create"), model);
    }

    @Override
    public void create(String statement, Object parameter) {
        sqlSession.insert(getStatement(statement), parameter);
    }

    @Override
    public void removeById(ID id) {
        sqlSession.delete(getStatement("removeById"), id);
    }

    @Override
    public void remove(String statement, Object parameter) {
        sqlSession.delete(getStatement(statement), parameter);
    }

    @Override
    public void modify(M model) {
        sqlSession.update(getStatement("modify"), model);
    }

    @Override
    public void modify(String statement, Object parameter) {
        sqlSession.update(getStatement(statement), parameter);
    }

    @Override
    public M queryById(ID id) {
        return sqlSession.selectOne(getStatement("queryById"), id);
    }

    @Override
    public M query(String statement, Object parameter) {
        return sqlSession.selectOne(getStatement(statement), parameter);
    }

    @Override
    public Pagination<M> queryAll(int currentPage) {
        return getPagination("queryAll", null, currentPage);
    }

    @Override
    public Pagination<M> query(String statement, Object parameter, int currentPage) {
        return getPagination(getStatement(statement), parameter, currentPage);
    }

    private Pagination<M> getPagination(String statement, Object parameter, int currentPage) {
        int pageSize = Constant.PAGE_SIZE;
        int totalRows = sqlSession.selectList(getStatement(statement), parameter).size();
        int totalPages = totalRows / pageSize + (totalRows % pageSize) == 0 ? 0 : 1;
        RowBounds rowBounds = new RowBounds(pageSize * (currentPage - 1), pageSize);
        List<M> list = sqlSession.selectList(statement, parameter, rowBounds);
        return new Pagination<>(list, statement, pageSize, totalRows, totalPages, currentPage);
    }

    private String getStatement(String statement) {
        return namespace.concat(".").concat(statement);
    }
}
