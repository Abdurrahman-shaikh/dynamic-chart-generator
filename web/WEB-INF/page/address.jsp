<%-- 
    Document   : coun_s_ajaxDemo
    Created on : Nov 25, 2023, 12:41:20 PM
    Author     : Abdur Rahman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onload="address()">
    <form action="" method="post">
        <!-- Dropdown for selecting country -->
        <select id="country" name="country" id="countryId">
            <!-- Option to select country -->
            <option value="">Select Country</option>
            <c:forEach var="country" items="${countries}">
                <option value="${country.getCountryCode()}">${country.getCountryName()}</option>
            </c:forEach>
        </select>
        <select name="states" id="stateId">
            <option>Select State</option>
        </select>
    </form>
</body>
</html>

<script>
    
//let country = function() {
//    let country = document.getElementById('country');
//    country.addEventListner('change',function(){
//        let country
//    });
//};
    
let address = function() {
    let countryObj = document.getElementById("countryId");
    countryObj.addEventListener("change", function() {
        let country_code = countryObj.value;
        let state_id = document.getElementById("stateId");
        state_id.innerHTML = "";
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status >= 200 && xhr.status < 300 || xhr.status === 304) {
                    // Parse JSON response
                    var jsonObj = JSON.parse(xhr.responseText);
                    // Loop through states and add options to state dropdown
                    jsonObj.states.forEach(function(stateObject) {
                        var stateCode = parseInt(Object.keys(stateObject)[0]);
                        var stateName = stateObject[stateCode];
                        var option = document.createElement("option");
                        option.value = stateCode;
                        option.text = stateName;
                        state_id.add(option);
                    });
                }
            }
        };
        xhr.open("GET", `fc?type=model&page=GetStates&code=${country_code}`, true);
        xhr.send();
    });
};
</script>
