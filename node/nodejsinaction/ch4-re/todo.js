var http = require('http'),
	url = require('url');

var items = [], item = '';

/*
 * GET    : curl http://localhost:3000/ -v
 * POST   : curl -d 'Hello World' http://localhost:3000 -v
 * PUT    : curl -X PUT -d 'Hello World' http://localhost:3000/1 -v
 * DELETE : curl -X DELETE http://localhost:3000/2 -v
 */

var server = http.createServer(function(req, res) {
	switch (req.method) {
	case 'POST':
		item = '';
		req.setEncoding('utf8');
		req.on('data', function(chunk) {
			item += chunk;
		});
		req.on('end', function() {
			items.push(item);
			res.end('OK\n');
		});
		break;
	case 'PUT':
		item = '';
		req.setEncoding('utf8');
		req.on('data', function(chunk) {
			item += chunk;
		});

		req.on('end', function() {

			var path = url.parse(req.url).pathname,
			i = parseInt(path.slice(1), 10);

			if (isNaN(i)) {
				res.statusCode = 400;
				res.end('Invalid item id');
			} else if (!items[i]) {
				res.statusCode = 404;
				res.end('Item not found');
			} else {
				items[i] = item;
				console.log(items);
				res.end('OK\n');
			}
		});
		break;
	case 'GET':
		var body = items.map(function(item, i) {
			return i + ') ' + item;
		}).join('\n') + '\n';
		res.setHeader('Content-Length', Buffer.byteLength(body));
		res.setHeader('Content-Type', 'text/plain; charset="utf-8"');
		res.end(body);
		break;
	case 'DELETE':
		var path = url.parse(req.url).pathname,
			i = parseInt(path.slice(1), 10);

		if (isNaN(i)) {
			res.statusCode = 400;
			res.end('Invalid item id');
		} else if (!items[i]) {
			res.statusCode = 404;
			res.end('Item not found');
		} else {
			items.splice(i, 1);
			res.end('OK\n');
		}
		break;
	}
});

server.listen(3000);
