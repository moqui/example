<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><#if html_title?has_content>${html_title}<#else><#if parentMenuName?has_content>${ec.resource.expand(parentMenuName, "")} - </#if><#if defaultMenuName?has_content>${ec.resource.expand(defaultMenuName, "")}</#if></#if></title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-components-web/10.0.0/material-components-web.min.css"
          integrity="sha512-itdbZWgBgwR2YX11ZRe22vFJkxxKwBqpwQRCiIsp1Qsa6JXEsm2ekGW7gGKTJJaVRXgQO4Unn6l15dVh+yRfUA==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons"/>
</head>
<body>
