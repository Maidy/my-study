var todo = require('./lib/todo_server.js'),
	http = require('http'),
	port = 3000;

todo.server.listen(port);
console.log('Listening on port ' + port);
