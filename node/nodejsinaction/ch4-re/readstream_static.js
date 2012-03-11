var http = require('http'),
	parse = require('url').parse,
	join = require('path').join,
	fs = require('fs');

var root = __dirname;

var server = http.createServer(function(req, res) {
	var url = parse(req.url);
	var path = join(root, url.pathname);

	fs.stat(path, function(err, stat) {
		if (err) {
			if ('ENOENT' === err.code) {
				res.statusCode = 404;
				res.end('Not found\n');
			} else {
				res.statusCode = 500;
				res.end('Internal Server Error\n');
			}
		} else {
			res.setHeader('Content-Length', stat.size);
			res.setHeader('Last-Modified', stat.mtime);

			// [TODO] Cache Control!!!

			var t1 = stat.mtime;
			var t2 = req.headers['if-modified-since'];
//			console.log(req.headers);
			if (t2) {
				if (Date.parse(t1) > Date.parse(t2)) {
					var stream = fs.createReadStream(path);
					stream.pipe(res);
					// stream.on('data', function(chunk) {
					// 	res.write(chunk);
					// });
					// stream.on('end', function() {
					// 	res.end();
					// });
					stream.on('error', function() {
						res.statusCode = 500;
						res.end('Internal Server Error\n');
					});
				} else {
					res.statusCode = 304;
					res.end();
				}
			} else {
				var stream = fs.createReadStream(path);
				stream.pipe(res);
				// stream.on('data', function(chunk) {
				// 	res.write(chunk);
				// });
				// stream.on('end', function() {
				// 	res.end();
				// });
				stream.on('error', function() {
					res.statusCode = 500;
					res.end('Internal Server Error\n');
				});
			}
		}
	});

});

server.listen(3000);

