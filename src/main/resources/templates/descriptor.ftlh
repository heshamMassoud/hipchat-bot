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
    }],
    "chat:configuration": [
      {
        "key": "ctinator-config",
        "page": {
          "target": "ctinator-config-dialog"
        }
      }
    ],
    "chat:dialog": [
      {
        "key": "ctinator-config-dialog",
        "title": {
        "value": "CTinator Configuration"
      },
      "options": {
        "size": {
          "width": "500px",
          "height": "300px"
        },
        "primaryAction":  {
          "key": "action-save",
          "name": {
            "value": "Save"
          }
        },
        "secondaryActions": [
          {
            "key": "action-close",
            "name": {
              "value": "Close"
            }
          }
        ]
      },
      "url": "/configuration",
      "authentication": "jwt"
      }
    ],
    "chat:actionTarget" : [
          {
           "key": "commercetoolsMenu",
           "callService": {
             "url": "/commercetools-menu"
           }
          },
          {
           "key": "tableTennisMenu",
           "callService": {
             "url": "/table-tennis-menu"
           }
          },
          {
           "key": "commercetoolsMenuList",
           "callService": {
             "url": "/commercetools-menu-list"
           }
          },
          {
           "key": "tableTennisMenuList",
           "callService": {
             "url": "/table-tennis-menu-list"
           }
          }
      ]
  }
}