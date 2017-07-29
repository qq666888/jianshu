package jianshu.datalab.xin.controller;

import com.google.code.kaptcha.Constants;
import jianshu.datalab.xin.model.User;
import jianshu.datalab.xin.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*** 6/27/17 15:25*/
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;

    private final StrongPasswordEncryptor encryptor;

    @Autowired
    public UserController(UserService userService, StrongPasswordEncryptor encryptor) {
        this.userService = userService;
        this.encryptor = encryptor;
    }

    @RequestMapping("signUp")
    private String signUp(User user) {

        if (user.getNick().length() == 0) {
            request.setAttribute("message", "请输入昵称");
            return "sign_up.jsp";
        }

        if (user.getMobile().length() == 0) {
            request.setAttribute("message", "请输入手机号");
            return "sign_up.jsp";
        }

        if (user.getPassword().length() < 6) {
            request.setAttribute("message", "密码不能少于6个字符");
            return "sign_up.jsp";
        }

        if (isNickExisted(user.getNick())) {
            request.setAttribute("message", "昵称 已经被使用");
            return "sign_up.jsp";
        }

        if (isMobileExisted(user.getMobile())) {
            request.setAttribute("message", "手机号 已经被使用");
            return "sign_up.jsp";
        }

        String password = encryptor.encryptPassword(user.getPassword());
        String lastIp = request.getRemoteAddr();
        user.setPassword(password);
        user.setLastIp(lastIp);

        userService.signUp(user);

        return "redirect:/sign_in.jsp";
    }

    private User checkSignIn(User user) {
        String plainPassword = user.getPassword();
        user = userService.queryUserByMobile(user.getMobile());
        if (user != null) {
            String encryptedPassword = user.getPassword();
            if (encryptor.checkPassword(plainPassword, encryptedPassword)) {
                String lastIp = request.getRemoteAddr();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String lastTime = format.format(new Date());
                user.setLastIp(lastIp);
                user.setLastTime(lastTime);
                userService.signInUpdate(user);
                return user;
            }
        }
        return null;
    }

    /**
     * 处理 Android 客户端请求
     */
    @ResponseBody
    @RequestMapping("signInApi")
    private Map<String, Object> signInApi(User user) {
        user = checkSignIn(user);
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            map.put("canSignIn", true);
            map.put("user", user);
        } else {
            map.put("canSignIn", false);
            map.put("user", null);
        }
        return map;
    }

    @RequestMapping("signIn")
    private String signIn(User user, String kaptchaReceived) {
//        if (!checkValidCode(kaptchaReceived)) {
//            request.setAttribute("message", "验证码错误");
//            return "/sign_in.jsp";
//        }

        user = checkSignIn(user);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return "redirect:/default.jsp";
        }
        request.setAttribute("message", "登录失败，手机号/邮箱或密码错误");
        return "/sign_in.jsp";
    }

    @RequestMapping("signOut")
    private void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("default.jsp");
    }

    /**
     * for signUp
     */
    private boolean isNickExisted(String nick) {
        try {
            return isExisted("nick", nick);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return false; // TODO: 7/19/17
    }

    /**
     * for signUp
     */
    private boolean isMobileExisted(String mobile) {
        try {
            return isExisted("mobile", mobile);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return false; // TODO: 7/19/17
    }

    /**
     * for AJAX
     */
    @ResponseBody
    @RequestMapping("isNickOrMobileExisted")
    private Map<String, Boolean> isNickOrMobileExisted(String field, String value) throws ServletException, IOException {
        boolean isExisted = isExisted(field, value);
        Map<String, Boolean> map = new HashMap<>();
        map.put("isExisted", isExisted);
        return map;
    }

    private boolean isExisted(String field, String value) throws ServletException, IOException {
        boolean isNickExisted = false;
        boolean isMobileExisted = false;

        if (field.equals("nick")) {
            User user = userService.queryUserByNick(value);
            isNickExisted = (user != null);
        } else {
            User user = userService.queryUserByMobile(value);
            isMobileExisted = (user != null);
        }
        return isNickExisted || isMobileExisted;
    }

    @ResponseBody
    @RequestMapping("checkValidCode")
    private Map<String, Boolean> checkValidCode(String kaptchaReceived) {
        String kaptchaExpected = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        Map<String, Boolean> map = new HashMap<>();

        if (kaptchaExpected.equalsIgnoreCase(kaptchaReceived)) {
            map.put("isValid", true);
        } else {
            map.put("isValid", false);
        }
        return map;
//        return map.get("isValid"); // TODO: 2017/7/23
    }
}
