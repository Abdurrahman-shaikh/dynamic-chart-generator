let address = function(){
    let countryObj = document.getElementById("countryId");
    countryObj.addEventListener("click",()=>{
        let country_code = countryObj.value;
        let state_id = document.getElementById("stateId");
        state_id.innerHTML = "";
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4){
                if((xhr.status>=200&&xhr.status<300)||xhr.status===304){
                    var jsonObj = JSON.parse(xhr.responseText);
                    jsonObj.states.forEach(function(stateObject) {
                            var stateCode = parseInt(Object.keys(stateObject)[0]); // Convert to integer
                            var stateName = stateObject[stateCode];
                            var option = document.createElement("option");
                            option.value = stateCode;
                            option.text = stateName;

                            state_id.add(option);
                    });
                }
            }
        };
        xhr.open("get",`fc?type=model&page=GetStates&code=${country_code}`,true);
        xhr.send(null);
    });
};