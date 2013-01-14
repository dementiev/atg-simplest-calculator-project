<%@ taglib uri="/dspTaglib" prefix="dsp"%>
<%@ page import="atg.servlet.*"%>
<dsp:importbean bean="/calculator/CalcFormHandler" />
<dsp:page>

	<dsp:importbean bean="/atg/dynamo/Configuration" />

	<head>
	<title>calculator-web JSP Index Page</title>
	</head>
	<body>
	<h1>calculator-web Test JSP Page</h1>
	
	<dsp:droplet name="/atg/dynamo/droplet/ErrorMessageForEach">
		<dsp:oparam name="output">
			<li><dsp:valueof param="message" /></li>
		</dsp:oparam>
		<dsp:oparam name="outputStart">
			<font color="red">I encountered problems processing this form:
			<ul>
		</dsp:oparam>
		<dsp:oparam name="outputEnd">
			</ul>
			</font>
		</dsp:oparam>
	</dsp:droplet>
	<div id='calcform'><dsp:form action="index.jsp" method="post">
		<p>Number1: <dsp:input type="text" bean="CalcFormHandler.number1"
			required="<%=true%>" />
		<p>
		<p>Number2: <dsp:input type="text" bean="CalcFormHandler.number2"
			required="<%=true%>" />
		<p><dsp:input type="submit" value="Do calculation"
			bean="CalcFormHandler.calculate" /> &nbsp; <dsp:input
			bean="/calculator/CalcFormHandler.cancel" type="Submit"
			value="Cancel" /> <dsp:input type="hidden"
			bean="CalcFormHandler.validateSuccessURL" value="calcResult.jsp" />
	</dsp:form></div>
	</body>
</dsp:page>
<%-- @version $Id: //product/Eclipse/main/plugins/atg.project/templates/index.jsp#1 $$Change: 425088 $--%>
