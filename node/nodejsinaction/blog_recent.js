var http = require('http');
var mysql = require('mysql');

var client = mysql.createClient({'user':'', 'password':''});
client.useDatabase('test');

http.createServer(function(req, res) {
    if (req.url == '/') {
	client.query('SELECT * FROM posts',
		     function(err, results, fields) {
			 if (err) { throw err; }
			 var output = '<html><head></head><body>' +
			     '<h1>Posts</h1>' +
			     '<ul>';
			 for (var i in results) {
			     output += '<li>' + results[i].title + '</li>';
			 }
			 output += '</ul></body></html>';
			 res.writeHead(200, {'Content-Type':'text/html'});
			 res.end(output);
		     });
    }	     
}).listen(8000, '127.0.0.1');
