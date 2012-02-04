var fs = require('fs'),
    path = require('path'),
    args = process.argv.splice(2),
    command = args.shift(),
    taskDescription = args.join(' '),
    file = path.join(process.cwd(), '/.tasks');

// $ node cli_tasks.js a b c d e
//            args : [ 'b', 'c', 'd', 'e' ]
//         command : 'a'
// taskDescription : 'b c d e'

switch (command) {
    case 'list':
	listTasks(file);
	break;

    case 'add':
	addTask(file, taskDescription);
	break;

    default:
	console.log('Usage: ' + process.argv[0]
	    + ' list|add [taskDescription]');
};

function listTasks(f) {
    getTasks(f, function(tasks) {
	for (var i=0; i<tasks.length; i++) {
	    console.log(i+1, tasks[i]);
	}
    });
};

function addTask(f, task) {
    getTasks(f, function(tasks) {
	tasks.push(task);
	storeTasks(f, tasks);
    });
};

function getTasks(f, cb) {
    path.exists(f, function(exists) {
	if (exists) {
	    fs.readFile(f, 'utf8', function(err, data) {
		if (err) { throw err; }
		cb(JSON.parse(data.toString()));
	    });
	} else {
	    cb([]);
	}
    });
};

function storeTasks(f, tasks) {
    fs.writeFile(f, JSON.stringify(tasks), 'utf8', function(err) {
	if (err) { throw err; }
	console.log('Saved.');
    });
};
