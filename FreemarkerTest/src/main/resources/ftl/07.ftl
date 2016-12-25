<#--进行变量的赋值-->
<#assign name="hotusm">
<#assign age="22">
<#assign username="baby">
姓名:${name}
<#--自定义标签-->
<@content name="${name}" age="${age}">
	${output}
	${append}
</@content>
<#--将内容包含进来-->
<#include "test.html">
<#include "01.ftl">