<#--���б����ĸ�ֵ-->
<#assign name="hotusm">
<#assign age="22">
<#assign username="baby">
����:${name}
<#--�Զ����ǩ-->
<@content name="${name}" age="${age}">
	${output}
	${append}
</@content>
<#--�����ݰ�������-->
<#include "test.html">
<#include "01.ftl">