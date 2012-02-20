var assert = require('assert'),
	todo = require('../lib/todo'),
	todoDb = new todo.TodoDb();

module.exports = {
	'delete test' : function(beforeExit) {
		todoDb.delete(function() {
			todoDb.get({'skip':0, 'limit':25}, function(err, todos) {
				if (err) { throw err; }
				assert.equal(
					todos.length,
					0,
					'Failure: there should be no todos after deleting them.');
				todoDb.close();
			});
		});

		beforeExit(function() {
			console.log('delete test exit!');
		});
	}
};
