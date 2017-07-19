package jianshu.datalab.xin.dao.impl;

import jianshu.datalab.xin.dao.GenericDao;
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

    @Autowired
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
    public List<M> queryAll() {
        return sqlSession.selectList(getStatement("queryAll"));
    }

    @Override
    public List<M> queryList(String statement, Object parameter) {
        return sqlSession.selectList(getStatement("").concat(statement), parameter);
    }

    @Override
    public M queryById(ID id) {
        return sqlSession.selectOne(getStatement("queryById"), id);
    }

    @Override
    public M queryOne(String statement, Object parameter) {
        return sqlSession.selectOne(getStatement(statement), parameter);
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
    public void removeById(ID id) {
        sqlSession.delete(getStatement("removeById"), id);
    }

    @Override
    public void remove(M model) {
        sqlSession.delete(getStatement("remove"), model);
    }

    private String getStatement(String statement) {
        return namespace.concat(".").concat(statement);
    }
}
