package jianshu.datalab.xin.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *         2017/7/23 00:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<M extends Serializable> {
    private List<M> list;
    private String statement;
    private int pageSize;
    private int totalRows;
    private int totalPages;
    private int currentPage;
}
