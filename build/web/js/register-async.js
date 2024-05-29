let index = function(){
    let mbObj = document.getElementById("mb");
    mbObj.addEventListener("blur",()=>{
        let data = mbObj.value;
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4){
                if((xhr.status>=200&&xhr.status<300)||xhr.status===304){
                    document.getElementById("msid").innerHTML = xhr.responseText;
                }else{
                    document.getElementById("msid").innerHTML = "";
                }
            }
        };
        xhr.open("get",`fc?type=model&page=CheckModel&data=${mbObj.value}&id=mb`,true);
        xhr.send(null);
    });

    let emObj = document.getElementById("em");
    emObj.addEventListener("blur",()=>{
        let data = mbObj.value;
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4){
                if((xhr.status>=200&&xhr.status<300)||xhr.status===304){
                    document.getElementById("sid").innerHTML = xhr.responseText;
                }else{
                    document.getElementById("sid").innerHTML = "";
                }
            }
        };
        xhr.open("get",`fc?type=model&page=CheckModel&data=${emObj.value}&id=em`,true);
        xhr.send(null);
    });
};
