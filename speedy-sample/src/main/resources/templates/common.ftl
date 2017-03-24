<#import "tags.ftl" as tags>
<#macro head>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title><@tags.spring.message code="app.title" /></title>
</#macro>

<#macro nav>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <@tags.shiro.guest>
                    <li><a href="/login">登录</a></li>
                    <li><a href="/u/create">注册</a></li>
                </@tags.shiro.guest>
                <@tags.shiro.user>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"><@tags.shiro.principal/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/u/logout">退出</a></li>
                        </ul>
                    </li>
                </@tags.shiro.user>
            </ul>
        </div>
    </div>
</nav>
<div class="container" style="position: fixed; top: 40px; z-index: 99999">
    <div class="row">
        <#if flash_message_success?exists>
            <div class="fade in col-md-4 col-md-offset-5 alert alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                ${flash_message_success}
            </div>
        </#if>
        <#if flash_message_error?exists>
            <div class="fade in col-md-4 col-md-offset-5 alert alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                ${flash_message_error}
            </div>
        </#if>
        <#if flash_message_warning?exists>
            <div class="fade in col-md-4 col-md-offset-5 alert alert-warning">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                ${flash_message_warning}
            </div>
        </#if>
    </div>
</div>
</#macro>