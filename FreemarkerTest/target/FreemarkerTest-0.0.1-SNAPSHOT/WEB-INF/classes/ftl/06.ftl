<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ޱ����ĵ�</title>
</head>

<body>
${user.id}-------${user.name}------${user.group!}  <#-- !��Ϊ�վͲ����  -->
<#--${user.group.name!}--><#-- �������ϵķ�ʽ��! freemarker����ֻ���ж�group.name�ǲ��ǿ�ֵ -->
${(user.group.name)!"1234"} 

${(a.b)!"û��a.bԪ��"}

<#--
!:ָ��ȱʧ������Ĭ��ֵ 
??:�ж�ĳ�������Ƿ����,����booleanֵ 
-->
<#if (a.b)??> <#--if���ü�$-->
    ��Ϊ��
<#elseif (a.b)!"û��">
    Ϊ��
</#if>
</body>
</html>