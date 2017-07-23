package jianshu.datalab.xin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by mingfei.net@gmail.com
 * 6/30/17 09:06
 * https://github.com/thu/jianshu/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {

    private Integer id;
    private String nick;
    private String mobile;
    private String password;
    private String avatar;
    private int pay;
    private double money;
    private String lastIp;
    private String lastTime;
    private String signUpTime;
}
