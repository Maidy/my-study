var http = require('http'),
	url = require('url'),
	join = require('path').join,
	fs = require('fs'),
	util = require('util');

var root = __dirname;
var server = http.createServer(function(req, res) {
	
	var path = root + url.parse(req.url).pathname;
	var stream = fs.createReadStream(path);
	stream.pipe(res);
	stream.on('error', function(err) {
		res.statusCode = 500;
		res.write(JSON.stringify(err));
		res.end('\nInternal Server Error\n');
	});

/*
	var stream = fs.createReadStream(path);
	stream.on('data', function(chunk) {
		res.write(chunk);
	});
	stream.on('end', function() {
		res.end();
	});
*/
});
server.listen(3000);
