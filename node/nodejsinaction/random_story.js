var path = require('path'),
	fs = require('fs'),
	request = require('request'),
	htmlparser = require('htmlparser');

var tasks = [ // 1
	function() {
		var configFilename = './rss_feeds.txt';
		path.exists(configFilename, function(exists) { // 2
			if (!exists) {
				next('Create a list of RSS feeds in the file ./rss_feeds.txt.');
			} else {
				next(false, configFilename);
			}
		});
	},

	function(configFilename) {
		fs.readFile(configFilename, function(err, feedList) { // 3
			if (err) {
				next(err.message);
			} else {
				feedList = feedList // 4
					.toString()
					.replace(/^\s+|\s+$/g, '')
					.split('\n');
				var random = Math.floor(Math.random() * feedList.length); // 5
				next(false, feedList[random]);
			}
		});
	},

	function(feedUrl) {
		request({uri:feedUrl}, function(err, response, body) { // 6
			if (err) {
				next(err.message);
			} else if (response.statusCode == 200) {
				next(false, body);
			} else {
				next('Abnormal request status code.');
			}
		});
	},

	function(rss) {
		var handler = new htmlparser.RssHandler();
		var parser = new htmlparser.Parser(handler); // 7
		parser.parseComplete(rss);

		if (handler.dom.items.length) {
			var item = handler.dom.items.shift();
			console.log(item.title); // 8
			console.log(item.link);
		} else {
			next('No RSS items found.');
		}
	}
];

function next(err, result) { // 9
	if (err) { throw new Error(err); } // 10

	var currentTask = tasks.shift(); // 11

	if (currentTask) {
		currentTask(result); // 12
	}
}

next();  // 13
