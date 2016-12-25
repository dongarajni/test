<%@include file="../jsp/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<portlet:actionURL name="addUserProfile" var="fileUpload" />

<aui:form action="${fileUpload}"  method="post" name="fm" enctype="multipart/form-data">
	
	
	<aui:input name="profileId" type="file" label="User_Profile" />
	<aui:button-row>
       <aui:button type="submit" value="Save"/>
    </aui:button-row>
</aui:form>
