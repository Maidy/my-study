var server = require('../lib/todo_server').server;

module.exports = {
	'server running' : function(beforeExit, assert) {
		assert.response(server, {'url':'/', timeout:5000}, function(res) {
			assert.notEqual(res.body.indexOf('<h1>Todo list</h1>'), -1, 'Failure: server not responding');
		});

		beforeExit(function() {
			console.log('server running test exit!');
		});

	}
};
