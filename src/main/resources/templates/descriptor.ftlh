<#-- @ftlvariable name="appName" type="java.lang.String" -->
<#-- @ftlvariable name="serviceConfig" type="com.atlassian.stride.spring.config.model.ServiceConfig" -->
{
  "name": "${appName}",
  "baseUrl": "${serviceConfig.externalUrl}",
  "key": "ctinator",
  "modules": {
    "chat:bot": [{
        "key": "ctinator-mention",
        "mention": {
          "url": "/bot-mention"
        },
        "directMessage": {
          "url": "/bot-direct-message"
        }
    }]
  }
}