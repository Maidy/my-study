var http = require('http');

var items = [];

var server = http.createServer(function(req, res) {
	
	switch (req.method) {
		case 'POST':
			var item = '';
			req.setEncoding('utf-8');
			req.on('data', function(chunk) { item += chunk; });
			req.on('end', function() {
				items.push(item);
				res.end('OK\n');
			});
			break;

		case 'GET':
			var body = items.map(function(item, i) {
				return i + ') ' + item + '\n';
			}).join();
			res.writeHead({
				'Content-Length': Buffer.byteLength(body),
				'Content-Type': 'text/plain; charset="utf-8"'});
			res.write(body);
			res.end();
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
				res.end('OK');
			}
			
			break;
	}

}).listen(3000);
