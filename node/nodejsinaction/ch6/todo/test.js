var assert = require('assert'),
	todo = require('./lib/todo'),
	todoDb = new todo.TodoDb(),
	testsStarted = 0,
	testsCompleted = 0;

function deleteTest(cb) {
	testsStarted++;
	todoDb.delete(function() {
		todoDb.get({'skip':0, 'limit':25}, function(err, todos) {
			if (err) {
				throw err;
			}
			assert.equal(
				todos.length,
				0,
				'Failure: there should be no todos after deleting them.');
			testsCompleted++;
			cb();
		});
	});
};

function addTest(cb) {
	testsStarted++;
	todoDb.delete(function() {
		todoDb.add({'item':'feed bobcat'}, function(err, todo) {
			if (err) { throw err; }
			todoDb.get({'skip':0, 'limit':25}, function(err, todos) {
				if (err) { throw err; }
				assert.notEqual(
					todos.length,
					0,
					'Failure: there should be one todo.');
				testsCompleted++;
				cb();
			});
		});
	});
};

function todosUnfinishedCheckTest(cb) {
	testsStarted++;
	todoDb.delete(function(){
		todoDb.add({'item':'feed bobcat'}, function(err, todo) {
			if (err) { throw err; }
			todoDb.unfinished(function(unfinished) {
				assert.ok(unfinished, 'Failure: todos should be unfinished.');
				testsCompleted++;
				cb();
			});
		});
	});
};

// Run tests;
deleteTest(function() {
	addTest(function() {
	        todosUnfinishedCheckTest(function() {
		        console.log('Completed ' + testsCompleted + ' of ' + testsStarted + ' tests.');
		        todoDb.close();
	        });
	});
});
