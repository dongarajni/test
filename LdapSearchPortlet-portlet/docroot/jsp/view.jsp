<%@include file="/jsp/init.jsp"%>

<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:actionURL name="simpleSearch" var="simpleSearchURL" /> 
<portlet:actionURL name="advanceSearch" var="advanceSearchURL" />

<%-- <portlet:renderURL var="profileURL">
<portlet:param name="jspPage" value="/jsp/userprofile.jsp" />
</portlet:renderURL>
 --%>
  
  
<!-- <script type="text/javascript" src="../js/bootstrap.min.js"></script> -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<%

ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

%>


<portlet:renderURL var="editUserURL">
   <%-- <portlet:param name="jspPage" value="/jsp/user_Profile.jsp" /> --%>
   <portlet:param name="email" value="ron@gmail.com" />
   <portlet:param name="image" value="${image}" />
</portlet:renderURL>
 


<div role="tabpanel" class="search-tab">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
  	<c:choose>
  		<c:when test="${simpleChecked == null}">
  			<li role="presentation" id="simple" ><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Basic Search</a></li>
  		</c:when>
  		<c:otherwise>
  			<li role="presentation" class="active" id="simple"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Basic Search</a></li>
  		</c:otherwise>
  	</c:choose>
    
    <c:choose>
  		<c:when test="${advanceChecked == null}">
  			<li role="presentation"  id="advance"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Advance Search</a></li>	
  		</c:when>
  		<c:otherwise>
  			<li role="presentation" class="active" id="advance"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Advance Search</a></li>
  		</c:otherwise>
  	</c:choose>
    
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
    
    <!-- basic search form -->
    
	    <div class="span12">
			<form action="${simpleSearchURL}" method="POST">
				<div>		 				 
					<div class="search-form"  align="left">
						<span class="aui-search-bar">
					    	<aui:input  inlineField="<%= true %>" value="${keywords}" label="" name="keywords" size="30" title="search-entries" type="text" />
					        <input class="btn btn-primary" type="submit" value="Search"/>
							<%-- <a href="<%=profileURL %>">Redirect</a> --%>
						</span>
					</div>
				</div>
			</form>
		</div>	
	</div>
    
    <div role="tabpanel" class="tab-pane" id="profile">
    
    <!-- Advance search form -->

	<div class="span12 input-align-form">
	<form action="${advanceSearchURL}" method="POST">
	
		
		
		 <div >
		    <div class="search-form advance-search"  align="left">
		    	<div class="aui-search-bar">
		 			<div class="span2">
						<aui:input type="text" id="firstname" name="firstName" inlineLabel="true" value="${firstName}"/>
					</div>
					<div class="span2">
				    <aui:input type="text" id="lastname" name="lastName"  inlineLabel="true" value="${lastName}" />
					</div>
					<div class="span2">
				    <aui:input type="text" id="location" name="location" inlineLabel="true" value="${location}" />
					</div>
					<div class="span2">
				    <aui:input type="text" id="department" label="Department" name="department"  inlineLabel="true" value="${department}" />
					</div>
					<div class="span2">
				    <aui:input type="text" id="email"  name="email" inlineLabel="true" value="${email}" />				              
					</div>
			        <div class="span2">
			        	<label>&nbsp;</label>
			        	<input  class="btn btn-primary" type="submit" value="Search" />
			        </div>
			        
		        </div>
	    	</div>
	    </div>
	</form>
	</div>
	 <%
    
   		 String themeImagePath = themeDisplay.getPathThemeImages();
    %>
	    
    </div>
    
    <a href="${editUserURL}">www.google.com</a>
    <!-- table content starts -->
    <div class="tse-scrollable search-tab1 quickLinkScroll1 quick-links">
		 <div class="tse-content">

    <c:forEach items="${userDetailList}" var="userDetail">
    
<%-- <portlet:renderURL var="editUserURL">
   <portlet:param name="jspPage" value="/jsp/user_Profile.jsp" />
   <portlet:param name="email" value="${userDetail.userEmail}" />
    <portlet:param name="image" value="${image}" />
</portlet:renderURL> --%>
 
	    <div class="media" id="yui_patched_v3_11_0_1_1433768309435_514"> 
			<div class="media-left ">
				<a href="${editUserURL}">
				<c:choose>
  					<c:when test="${userDetail.userImage == null}">
  						<img src="<%=themeImagePath%>/trinet/user-icon.png"/>
					</c:when>
					<c:otherwise>
						<img src="data:image/png;base64,${userDetail.userImage}"/>
					</c:otherwise>
				</c:choose>
				</a> 
			</div> 
			<div id="yui_patched_v3_11_0_1_1433768309435_513" class="media-body">
		 
				<div class="span6">
				  	<h4 class="media-heading" id="media-heading">${userDetail.firstName} &nbsp; ${userDetail.lastName}</h4>
				  	<div class="content-text-dummy">
				 		<span>Designation : </span> ${userDetail.userDesignation}
				 	</div>
				</div>
				
				<div class="span6">
				  	<div class="content-text-dummy">
				    	<span>Department :</span> ${userDetail.userDepartment}
				    	<span> Email ID :</span> <a href="mailto:${userDetail.userEmail}">${userDetail.userEmail}</a>
				  	</div>
				  	<div class="content-text-dummy">
				  		<span>Location : </span><i id="yui_patched_v3_11_0_1_1433768309435_512">${userDetail.userLocation}</i>
				  	</div>
				 	
				</div>
			</div>
		</div>
	</c:forEach>
	
		</div>
	</div>
 <!-- table content ends --> 
   
  <!--  <div class="tse-scrollable search-tab1 quickLinkScroll1 quick-links">
 <div class="tse-content">

  
  <div class="media" id="yui_patched_v3_11_0_1_1433768309435_514"> 
    <div class="media-left ">
       <img src="http://trianzlr-640562673.us-east-1.elb.amazonaws.com/trinet-theme/images/trinet/user-icon.png"/>
    </div> 
    <div id="yui_patched_v3_11_0_1_1433768309435_513" class="media-body">
    
     <div class="span6">
      <h4 class="media-heading" id="media-heading">Bhavik &nbsp; Shah</h4> 
      <div class="content-text-dummy">
       <span>Designation : </span> Lead Server Administrator | India IT Services - Hydrabad
      </div>
      
     </div>
     
     <div class="span6">
      <div class="content-text-dummy">
       <span>Department :</span> SG & A
       <span> Email ID :</span> <a href="#">bhavik.shah@knowarth.com</a>
      </div>
      <div class="content-text-dummy">
       <span>Location : </span> <i id="yui_patched_v3_11_0_1_1433768309435_512">Ahmedabad</i>
      </div>
     </div>
    </div>
   </div>
   
   

  </div>
 </div>
 -->   
   
  </div>

</div> 


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script>

$(document).ready(function(){
	
//	$("#home").show();
//	$("#profile").hide();
	/* alert(document.getElementById('advanceshow').checked); */
	//document.getElementById('simple').className = "active";
	if(document.getElementById('simple').className == "active"){
		$("#home").show();
		$("#profile").hide();
	}
		
	if(document.getElementById('advance').className == "active"){
		$("#profile").show();
		$("#home").hide();
	} 
    $("#simple").click(function (){
    
    	
        $("#home").show();
        $("#profile").hide();
        
    });
    
    $("#advance").click(function (){
    
    	
        $("#home").hide();
        $("#profile").show();
    });
    
});
</script>





<%-- <c:forEach items="${userDetailList}" var="userDetail">
				<a href="mailto:${contact.emailAddress}">
					<div class="media">
						<div class="media-left">
							
								<img class="media-object" src="${contact.imageUrl}"/>
							
						</div>
	
						<div class="media-body">
							<h4 class="media-heading" id="media-heading">${contact.fullName}</h4>
							<i>${contact.jobTitle} - ${contact.city} </i>
	
						</div>
					</div>
				</a>
	    
	    
</c:forEach>


 --%>