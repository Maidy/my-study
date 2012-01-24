var http = require('http'),
	querystring = require('querystring');

var items = [];

var server = http.createServer(function(req, res) {
	if ('/' == req.url) {
		switch (req.method) {
			case 'GET':
				show(res);
				break;

			case 'POST':
				add(req, res);
				break;
			
			default:
				badRequest(res);
				break;
		}
	} else {
		notFound(res);
	}
});
server.listen(3000);

var notFound = function(res) {
	res.statusCode = 404;
	res.setHeader('Content-Type', 'text/plain');
	res.end('Not Found');
};


var badRequest = function(res) {
	res.statusCode = 400;
	res.setHeader('Content-Type', 'text/plain');
	res.end('Bad Request');
};

var show = function(res) {
	
	var html = '<html><body><h1>Todo List</h1>'
		+ '<ul>'
		+ items.map(function(item) {
			return '<li>' + item + '</li>';
		}).join('')
		+ '</ul>'
		+ '<form method="post" action="/">'
		+ '<p><input type="text" name="item" /></p>'
		+ '<p><input type="submit" value="Add Item" /></p>'
		+ '</form></body></html>';

	res.writeHead({
		'Content-Length': Buffer.byteLength(html),
		'Content-Type': 'text/html; charset="utf-8"'});
	
	res.write(html);
	res.end();
}

var add = function(req, res) {
	var data = '';
	req.setEncoding('utf-8');
	req.on('data', function(chunk) { data += chunk; });
	req.on('end', function() {
		var q = querystring.parse(data);
		items.push(q.item);
		show(res);
	});
};
