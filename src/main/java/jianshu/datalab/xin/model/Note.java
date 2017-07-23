package jianshu.datalab.xin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author mingfei.net@gmail.com
 *         2017/7/24 00:15
 *         https://github.com/thu/jianshu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Note extends BaseModel {

    private Integer id;
    private String title;
    private String markdown;
    private String content;
    private String time;
    int views;
    int notebookId;
}
