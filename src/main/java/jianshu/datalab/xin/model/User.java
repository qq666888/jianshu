package jianshu.datalab.xin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by mingfei.net@gmail.com
 * 6/30/17 09:06
 * https://github.com/thu/jianshu/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable { // 标识接口

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
