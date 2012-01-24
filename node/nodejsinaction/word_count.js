var fs = require('fs');

var path = './text';
var tasks = [];
var wordCounts = {};
var completed = 0;

function countWords(text) {
	var words = text
		.toString()
		.toLowerCase()
		.split(/\W+/)
		.sort();
	for (var i in words) {
		var w = words[i];
		wordCounts[w] = wordCounts[w] ? wordCounts[w] + 1 : 1;
	}
}

function checkCompleted() {
	completed++;
	if (completed === tasks.length) {
		for (var i in wordCounts) {
			console.log(i + ': ' + wordCounts[i]);
		}
	}
}

fs.readdir(path, function(err, files) {
	
	if (err) { throw err; }

	for (var i in files) {
		var task = (function(file) {
			return function() {
				fs.readFile(file, function(err, data) {
					if (err) { throw err; }
					countWords(data);
					checkCompleted();
				});
			};
		})(path + '/' + files[i]);
		tasks.push(task);
	}
	for (var t in tasks) { tasks[t](); }
});

