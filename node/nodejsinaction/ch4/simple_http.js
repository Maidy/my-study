var http = require('http');

http.createServer(function(req, res) {

/*
	var body = 'Hello World';
	res.setHeader('Content-Length', body.length);
	res.setHeader('Content-Type', 'text/plain');
	res.write(body);
	res.end();
*/

	var url = 'http://google.com',
		body = '<p>redirecting to <a href="' + url + '" >' + url + '</a></p>';

/*
	res.setHeader('Location', url);
	res.setHeader('Content-Length', body.length);
	res.setHeader('Content-Type', 'text/html');
	res.statusCode = 302;
*/
	res.writeHead(302, {
		'Location': url,
		'Content-Length': body.length,
		'Content-Type': 'text/html'
	});
	res.write(body);
	res.end();
	
}).listen(3000, '127.0.0.1');
