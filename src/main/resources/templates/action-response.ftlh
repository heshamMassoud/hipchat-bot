<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="nextAction" type="com.heshammassoud.models.NextAction" -->
{
<#if message??>"message": "${message}",</#if>
<#if error??>"error": "${error}",</#if>
<#if nextAction??>"nextAction": ${nextAction}</#if>
}