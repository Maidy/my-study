var http = require('http');
var server = http.createServer(function(req, res) {
	var body = '<p>Hello World</p>';
	res.setHeader('Content-Length', body.length);
	res.setHeader('Content-Type', 'text/html');
//	res.statusCode = 302;
	res.write(body);
	res.end();
}).listen(3000);

/*
 * setHeader를 write 다음에 실행하면 exception
 * statusCode 속성은 write 다음에 set 해도 exception이 발생되진 않지만, 응답에서 무시된다.
 */
