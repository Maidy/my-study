var events = require('events');

function Watcher(watchDir, processedDir) {
	this.watchDir = watchDir;
	this.processedDir = processedDir;
}

Watcher.prototype = new events.EventEmitter();

var fs = require('fs'),
	watchDir = './watch',
	processedDir = './done';

Watcher.prototype.watch = function() {
	var watcher = this;
	fs.readdir(this.watchDir, function(err, files) {
		if (err) throw err;
		for (index in files) {
			watcher.emit('process', files[index]);
		}
	})
};

Watcher.prototype.start = function() {
	var watcher = this;
	fs.watchFile(watchDir, function() {
		watcher.watch();
	});
};

var w = new Watcher(watchDir, processedDir);
w.on('process', function(file) {
	var wf = this.watchDir + '/' + file;
	var pf = this.processedDir + '/' + file.toLowerCase();

	fs.rename(wf, pf, function(err) {
		if (err) { throw err; }
	});
});

w.start();
