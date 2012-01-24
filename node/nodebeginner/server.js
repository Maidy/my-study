var http = require('http');
var url = require('url');

var start = function(route, handle) {

    var onrequest = function(req, res) {
	var pathname = url.parse(req.url).pathname;
	console.log('----');
	console.log('Request for ' + pathname + ' received.');
	route(handle, pathname, req, res);
    };

    http.createServer(onrequest).listen(8888);

    console.log("Server has started");
    
};

exports.start = start;
