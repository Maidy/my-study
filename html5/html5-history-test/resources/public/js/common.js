/*
 궁금한 부분...
 pushState를 하는 경우와 일반적인 페이지 로딩이 혼합되서 발생하면???
 pushState로 이동 후 다시 돌아가면... 제일 처음 로딩한 첫번째 화면이 보여야 하는거 아닌가?
*/
window.addEventListener("popstate", function(e) {
    console.log('pop state', e.state);
    if (e.state) {
	loadTab(e.state);
    }
}, false);

var loadTab=function(data){
    var menus = document.getElementById('menu').getElementsByTagName('li');
    for (var i=0,l=menus.length;i<l;i++) {
	var menu = menus[i];
	if (menu.id == data.menuId) { menu.className = 'selected'; }
	else { menu.className = ''; }
    }
    document.getElementById('contents').innerHTML = data.contents;
};

var clickMenuItem=function(id) {
    var a = document.getElementById(id).childNodes[0];
    var l = a.href + '.json';
    var client = new XMLHttpRequest();
    client.onreadystatechange = function(e) {
	if (this.readyState == 4 && this.status == 200) {
	    if(this.responseText != null) {
		try {
		    var data = {'menuId':id, 'contents':JSON.parse(this.responseText).contents};
		    loadTab(data);
		    history.pushState(data, '', a.pathname);
		} catch(e) {
		    console.log(e);
		}
	    }
	}
    };
    client.open("GET", l);
    client.send();
};

window.onload = function(){
    // 처음 로딩될때 상태를 저장해야 한다.
    // 그렇지 않으면 처음 페이지로 back 했을 때 데이터 로딩을 할 수 없다. 
    if (!history.state) {
	
	var s = {};
	var menus = document.getElementById('menu').getElementsByTagName('li');
	for (var i=0,l=menus.length;i<l;i++) {
	    var menu = menus[i];
	    if (menu.className.indexOf('selected') > -1) {
		s['menuId'] = menu.id;
		break;
	    }
	}
	s['contents'] = document.getElementById('contents').innerHTML;
	history.replaceState(s, null, null);
    }
};


