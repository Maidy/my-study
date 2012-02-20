var mongoose = require('mongoose'),
	db = mongoose.connect('mongodb://localhost/todos'),
	Schema = mongoose.Schema,
	Todos = new Schema({item: String}),
	Todo = mongoose.model('Todo', Todos);

exports.TodoDb = function() {};

exports.TodoDb.prototype = {
	add: function(todoData, cb) {
		var todo = new Todo();
		todo.item = todoData.item;
		todo.save(function(err) {
			cb(err, todo);
		});
	},

	get: function(options, cb) {
		options.skip = options.skip || 0;
		options.limit = options.limit || 25;

		var todos = [];
		Todo.find({}).skip(options.skip).limit(options.limit).exec(function(err, todos) {
			cb(err, todos);
		});
	},

	delete: function(cb) {
		this.get({}, function(err, todos) {
		         for (var index in todos) {
			         todos[index].remove();
		         }
		         cb();
		});
	},

	close: function() {
		db.disconnect();
	},

	unfinished: function(cb) {
		this.get({}, function(err, todos) {
			cb(todos.length > 0);
		});
	}
};
