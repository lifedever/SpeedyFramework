<#import "tags.ftl" as tags>
<!DOCTYPE html>
<html>
<head>
    <@tags.common.head/>
</head>
<body>
<@tags.common.nav/>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form method="post" action="/account/save-user">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <div class="form-group">
                    <label for="realname">真实姓名</label>
                    <input type="text" class="form-control" id="realname" name="realname">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword2">确认密码</label>
                    <input type="password" class="form-control" id="exampleInputPassword2" name="confirm_password">
                </div>
                <button type="submit" class="btn btn-primary">注册</button>
            </form>

        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>