var vows = require('vows'),
	assert = require('assert'),
	todo = require('./todo/lib/todo'),
	todoDb = new todo.TodoDb();

// suite, batch
vows.describe('todo application').addBatch({
	// context
	'todo delettion' : {
		// topic
		topic : function() {
			var cb = this.callback;
			todoDb.delete(function() {
				todoDb.get({'skip': 0, 'limit': 25}, cb);
			});
		},
		// vows
		'get after deletion' : function(err, todos) {
			assert.equal(todos.length, 0);
			todoDb.close();
		}
	}
}).export(module);
// vows vows_test.js --spec
// test runner


// run();
// node vows_test.js
