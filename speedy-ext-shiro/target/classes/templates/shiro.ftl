<#ftl strip_whitespace=true>
<#--需配合 Session['shiro_ext_user_session_key'] 使用-->
<#if Session['shiro_ext_user_session_key']?exists>
    <#assign shiroUser=Session['shiro_ext_user_session_key']/>
</#if>

<#assign shiroUserIsLogined=shiroUser?exists/>

<#--
    用户没有登录时，显示的信息
-->
<#macro guest>
    <#if !shiroUserIsLogined>
        <#nested>
    </#if>
</#macro>

<#--
    用户登录时，显示的信息
-->
<#macro user>
    <#if shiroUserIsLogined>
        <#nested>
    </#if>
</#macro>

<#--显示当前登录用户的名字，为空则显示guestName-->
<#macro principal guset="">
    <#if shiroUserIsLogined>
    ${shiroUser.principal}
    <#else>
    ${guset}
    </#if>
</#macro>

<#--当前用户有角色显示的内容-->
<#macro hasRole role>
    <#if shiroUser?exists && shiroUser.roles?seq_contains(role)>
        <#nested>
    </#if>
</#macro>

<#--没有角色显示的内容-->
<#macro lacksRole role>
    <#if !shiroUser?exists || !shiroUser.roles?seq_contains(role)>
        <#nested>
    </#if>
</#macro>

<#--有权限显示的内容-->
<#macro hasPermission permission>
    <#if shiroUser?exists && shiroUser.permissions?seq_contains(permission)>
        <#nested>
    </#if>
</#macro>

<#--没有权限显示的内容-->
<#macro lacksPermission permission>
    <#if !shiroUser?exists || !shiroUser.permissions?seq_contains(permission)>
        <#nested>
    </#if>
</#macro>