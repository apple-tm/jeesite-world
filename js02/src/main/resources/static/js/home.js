$(document).ready(function () {
    var menuList = localStorage.getItem("userInfo");
    menuList = JSON.parse(menuList);
    var menuUl = $("#menuUl");
    menuUl.empty();

    for(let i = 0;i < menuList.length; i++){

        menuUl.append(
            "<li id ="+ menuList[i].menuCode +" class='menu_li'>"+menuList[i].menuName+"</li>"
        );
    }
});