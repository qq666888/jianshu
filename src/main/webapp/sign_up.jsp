<%--
    6/27/17 10:47
--%>
<!DOCTYPE html>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<%@ include file="commons/inc.jsp" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title>注册 - 简书</title>
    <link rel='shortcut icon' type='image/x-icon' href='favicon.ico'>
    <link rel='stylesheet' href='${ctx}/assets/css/bootstrap.min.css'>
    <link rel='stylesheet' href='${ctx}/assets/css/bootstrap-switch.min.css'>
    <style>
        @import ${ctx}'/assets/css/nav.css';
    </style>
    <style>
        body {
            background: #f1f1f1;
        }

        h3 a {
            display: inline-block;
            margin: 30px 10px;
        }

        #sign-up {
            color: #ea6f5a;
        }

        #form-box {
            border-radius: 5px;
            box-shadow: 1px 1px 1px #0f0f0f;
        }

        #form-box {
            background: #fff;
            padding: 50px;
        }

        #form-box div {
            margin-top: 15px;
        }

        #form-box button {
            margin: 30px 0 15px;
        }

        #nick-message,
        #mobile-message,
        #password-message {
            display: none;
        }
    </style>
    <script src='${ctx}/assets/js/jquery.min.js'></script>
    <script src='${ctx}/assets/js/bootstrap.min.js'></script>
    <script src='${ctx}/assets/js/bootstrap-switch.min.js'></script>
    <script src='${ctx}/assets/js/nav.js'></script>
    <script>

        var isNickValidated; // 昵称通过了验证
        var isMobileValidated; // 手机号通过了验证
        var isPasswordValidated; // 密码通过了验证

        function showMessage(element, text, removedClass, addedClass) {
            element.parent()
                .removeClass(removedClass[0])
                .addClass(addedClass[0]);
            element.parent().next('small')
                .text(text)
                .removeClass(removedClass[1])
                .addClass(addedClass[1])
                .fadeIn('slow');
        }

        function validate(async, field) {
            var element = $('#' + field);
            var name = (field === 'nick') ? '昵称' : '手机号';
            if (element.val().trim().length === 0) {
                showMessage(
                    element,
                    '请输入' + name,
                    ['has-success', 'text-success'],
                    ['has-error', 'text-danger']
                );
                isNickValidated = false;
                return;
            }
            $.ajax({
                url: '${ctx}/user/isNickOrMobileExisted',
                type: 'post',
                data: {'field': field, 'value': element.val()},
                dataType: 'json',
                async: async,
                success: function (result) {
                    var isExisted = result.isExisted;
                    console.log("isExisted: " + isExisted);
                    if (isExisted) {
                        showMessage(
                            element,
                            name + ' 已经被使用',
                            ['has-success', 'text-success'],
                            ['has-error', 'text-danger']
                        );
                        if (field === 'nick') {
                            isNickValidated = false;
                        } else {
                            isMobileValidated = false;
                        }
                    } else {
                        showMessage(
                            element,
                            name + ' 可以使用',
                            ['has-error', 'text-danger'],
                            ['has-success', 'text-success']
                        );
                        if (field === 'nick') {
                            isNickValidated = true;
                        } else {
                            isMobileValidated = true;
                        }
                    }
                },
                error: function (a, b, c) {
                    console.log(a);
                    console.log(b);
                    console.log(c);
                    window.location.href = '${ctx}/default.jsp?message=ERROR.';
                }
            });
        }

        function validatePassword() {
            var password = $('#password');
            if (password.val().length < 6) {
                showMessage(
                    password,
                    '密码 不能少于6个字符',
                    ['has-success', 'text-success'],
                    ['has-error', 'text-danger']
                );
                isPasswordValidated = false;
            } else {
                showMessage(
                    password,
                    '密码 可以使用',
                    ['has-error', 'text-danger'],
                    ['has-success', 'text-success']
                );
                isPasswordValidated = true;
            }
        }

        $(function () {
            $('#li-index').removeClass('active');

            $('#nick').blur(function () {
                console.log('nick blur');
                validate(true, 'nick');
            });
            $('#mobile').blur(function () {
                console.log('mobile blur');
                validate(true, 'mobile');
            });
            $('#password').blur(function () {
                console.log('pasword blur');
                validatePassword();
            });

            $('#sign-up-form').submit(function () {
                validate(false, 'nick');
                validate(false, 'mobile');
                validatePassword();
                if (!isNickValidated) {
                    $('#nick').focus();
                } else if (!isMobileValidated) {
                    $('#mobile').focus();
                } else {
                    $('#password').focus();
                }

                return isNickValidated && isMobileValidated && isPasswordValidated;
            });
        });
    </script>
</head>
<body>
<c:import url="${ctx}/nav.jsp"/>
<div class='container'>
    <div id='logo'><img src='${ctx}/assets/image/logo.png' alt='简书'></div>
    <div id='form-box' class='col-md-4 col-md-offset-4'>
        <h3 class='text-center'><a class='text-muted' href="sign_in.jsp">登录</a> · <a id='sign-up' href=''>注册</a></h3>
        <form id="sign-up-form" class='form-horizontal' action='${ctx}/user/signUp' method='post'>
            <div class='input-group'>
                <span class='input-group-addon'><i class='glyphicon glyphicon-user'></i></span>
                <input id='nick' name='nick' class='form-control input-lg' type='text' placeholder='你的昵称' autofocus>
            </div>
            <small id='nick-message'></small>
            <div class='input-group'>
                <span class='input-group-addon'><i class='glyphicon glyphicon-phone'></i></span>
                <input id="mobile" name='mobile' class='form-control input-lg' type='text' placeholder='手机号'>
            </div>
            <small id='mobile-message'></small>
            <div class='input-group'>
                <span class='input-group-addon'><i class='glyphicon glyphicon-lock'></i></span>
                <input id="password" name='password' class='form-control input-lg' type='password' placeholder='设置密码'>
            </div>
            <small id='password-message'></small>
            <button class='btn btn-success btn-lg btn-block'>注册</button>
            <p class='text-center'>
                <small>点击 “注册” 即表示您同意并愿意遵守简书</small>
            </p>
            <p class='text-center'>
                <small><a href=''>用户协议</a> 和 <a href=''>隐私政策</a> 。</small>
            </p>
        </form>
        <small class="text-danger">${requestScope.message}</small>
    </div>
</div>
</body>
</html>
