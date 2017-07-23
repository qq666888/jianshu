package jianshu.datalab.xin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mingfei.net@gmail.com
 *         2017/7/24 00:01
 *         https://github.com/thu/jianshu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notebook implements Serializable {

    private Integer id;
    private String title;
    private String time;
    private int userId;
}
