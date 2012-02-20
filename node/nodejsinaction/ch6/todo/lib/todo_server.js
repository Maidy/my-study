var todo = require('./todo'),
	todoDb = new todo.TodoDb(),
	http = require('http'),
	qs = require('querystring');

exports.server = http.createServer(function(req, res) {
	if ('/' === req.url) {
		switch (req.method) {
		case 'GET':
			exports.show(res);
			break;
		case 'POST':
			exports.create(req, res);
			break;
		}
	}
});

exports.show = function(res) {
	var todos = todoDb.get({'skip':0, 'limit':25}, function(err, todos) {
		if (err) { throw err; }
		var html = '<h1>Todo list</h1><ul>';
		html += todos.map(function(todo) {
			return '<li>' + todo.item + '</li>';
		}).join('\n');
		
		html += '</ul><p></p>'
			+ '<form method="post" action="/">'
			+ '<p><input type="text" name="item" /></p>'
			+ '<p><input type="submit" value="Add Item" /></p>';
		res.setHeader('Content-Length', html.length);
		res.setHeader('Content-Type', 'text/html');
		res.end(html);
	});
};

exports.create = function(req, res) {
	var data = '';
	req.setEncoding('utf-8');
	req.on('data', function(chunk) { data += chunk; });
	req.on('end', function() {
	       var todo = qs.parse(data);
	       todoDb.add(todo, function(err, todo) {
		       exports.show(res);
	       });
	});
};

