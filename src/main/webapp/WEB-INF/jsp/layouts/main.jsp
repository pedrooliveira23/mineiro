<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><tiles:insertAttribute name="title" /></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="<c:url value="/css/vendor/bootstrap/bootstrap.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/bootstrap/bootstrap.min.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/bootstrap/bootstrap-responsive.min.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/fa/css/font-awesome.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/fa/css/font-awesome.min.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/style.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/jquery.ui/jquery-ui-1.10.2.custom.min.css" />">
        <link rel="stylesheet" href="<c:url value="/css/vendor/jquery.ui/jquery.timepicker.css" />">
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
        <link rel="stylesheet" href="<%=request.getContextPath()%><tiles:insertAttribute name="css"/>">
        
       
        
		<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon" />
                <script type="text/javascript" src="<c:url value="/js/vendor/highcharts/js/jquery.min.js" />"></script>
          <script type="text/javascript" src="<c:url value="/js/vendor/highstock/jquery.min.js" />"></script>
          

    </head>
    <body>
		<input type="hidden" id="input-urlRaiz" value="<c:url value="/" />"/>
		
		<tiles:insertAttribute name="navigation-bar" />
       

        <div class="container">

            <tiles:insertAttribute name="primary-content" />

            <hr>

			<tiles:insertAttribute name="footer-content" />
            

        </div> <!-- /container -->
 

        
	<script type="text/javascript" src="<c:url value="/js/vendor/modernizr/modernizr-2.6.1-respond-1.1.0.min.js"/>"></script>
       	<script type="text/javascript" src="<c:url value="/js/vendor/jquery/jquery-1.9.0.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/vendor/jquery/jquery-migrate-1.1.1.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/vendor/jquery.ui/jquery-ui-1.10.2.custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/vendor/jquery.ui/jquery.ui.datepicker-pt-BR.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/vendor/jquery.ui/jquery.timepicker.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/vendor/jquery.ui/jquery.mask.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/vendor/bootstrap/bootstrap.min.js" />"></script>
        <!--<script type="text/javascript" src='https://www.google.com/jsapi?autoload={"modules":[{"name":"visualization","version":"1","packages":["columnchart","piechart"]}]}'></script> 
		--><script type="text/javascript" src="<c:url value="/js/vendor/jquery.tmpl/jquery.tmpl.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/vendor/jquery.autosize/jquery.autosize.min.js" />"></script>
         
                 <script type="text/javascript" src="<c:url value="/js/vendor/highstock/js/highstock.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/vendor/highstock/js/modules/exporting.js" />"></script>
                
                
                       <script type="text/javascript" src="<c:url value="/js/vendor/highcharts/js/adapters/standalone-framework.js" />"></script>
                <script type="text/javascript" src="<c:url value="/js/vendor/highcharts/js/highcharts.js" />"></script>
		
                
           
                
              

               
                
                
                
		<script type="text/javascript" src="<%=request.getContextPath()%><tiles:insertAttribute name="js"/>"></script>
    </body>
</html>
