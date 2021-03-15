<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.crm.springdemo.util.SortUtils" %>

<!DOCTYPE html>
<html>
	<head>
		<title>List Customers</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<div id="content">

				<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
					<input type="button" value="Add Customer"
						   onclick="window.location.href='showFormForAdd'; return false;"
						   class="add-button"
					/>
				</security:authorize>

				<form:form action="search" method="GET">
					Search Customer : <input type="text" name="searchName" />
					<input type="submit" value="Search" class="add-button" />
				</form:form>
				
				<c:url var="sortLinkFirstName" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"></c:param>
				</c:url>
				<c:url var="sortLinkLastName" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"></c:param>
				</c:url>
				<c:url var="sortLinkEmail" value="/customer/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"></c:param>
				</c:url>
				
				<table>
					<tr>
						<th><a href="${sortLinkFirstName}">First Name</a></th>
						<th><a href="${sortLinkLastName}">Last Name</a></th>
						<th><a href="${sortLinkEmail}">Email</a></th>
						<th>Action</th>
					</tr>
					<c:forEach var="tempCustomer" items="${customers}">
						<!-- c:url is not showing in the page -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id}"></c:param>
						</c:url>
					
						<tr>
							<td> ${tempCustomer.firstName} </td>
							<td> ${tempCustomer.lastName} </td>
							<td> ${tempCustomer.email} </td>
							<td>
								<security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
									<a href="${updateLink}">Update</a>
								</security:authorize>
								|
								<security:authorize access="hasAnyRole('ADMIN')">
									<a href="${deleteLink}"
									   onclick="if (!(confirm('Are you sure you wanna delete this customer ?'))) return false">Delete</a>
								</security:authorize>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>