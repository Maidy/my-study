/*
 * JavaScript Patterns Chapter 7. Observer pattern 2
 * keypress game
 */
var publisher = {

    subscribers: {
	any: []
    },

    on: function(type, fn, context) {
	type = type || 'any';
	fn = typeof fn === 'function' ? fn : context[fn];

	if (typeof this.subscribers[type] === 'undefined') {
	    this.subscribers[type]=[];
	}
	this.subscribers[type].push({ fn: fn, context: context || this });
    },

    remove: function(type, fn, context) {
	type = type || 'any';
	var subscribers = this.subscribers[type];
	var len = subscribers ? subscribers.length : 0;

	for (var i=0; i < len; i++) {
	    if (subscribers[i].fn === fn && subscribers[i].context === context) {
		subscribers.splice(i, 1);
	    }
	}
    },

    fire: function(type, arg) {
	type = type || 'any';
	var subscribers = this.subscribers[type];
	var len = subscribers ? subscribers.length : 0;
	
	for (var i=0; i < len; i++) {
	    subscribers[i].fn.call(subscribers[i].context, arg);
	}
    }
};

var Player=function(name, key) {
    this.name = name;
    this.key = key;
    this.points = 0;

    this.fire('newplayer', this);
};

Player.prototype = {
    play: function() {
	this.points++;
	this.fire('play', this);
    }
};

var game = {

    keys: { },

    addPlayer: function(player) {
	var key = player.key.toString().charCodeAt(0);
	this.keys[key] = player;
    },

    handleKeypress: function(e) {
	e = e || window.event;
	var key = e.which;
	if (game.keys[key]) {
	    game.keys[key].play();
	}
    },

    handlePlay: function(player) {
	var score = { }, players = this.keys;
	for (var i in players) {
	    if (players.hasOwnProperty(i)) {
		score[players[i].name] = players[i].points;
	    }
	}
	this.fire('scorechange', score);
    }
};

var scoreboard = {
    update: function(score) {
	var output='';
	for (var i in score) {
	    if (score.hasOwnProperty(i)) {
		output += i + ': ' + score[i] + '\n';
	    }
	}
	console.log(output);
    }
};

var makePublisher = function(obj) {
    for (var o in publisher) {
	if (publisher.hasOwnProperty(o) && typeof publisher[o] === 'function') {
	    obj[o] = publisher[o];
	}
     }
    obj.subscribers={ any:[] };
};

(function() {
     makePublisher(game);
     makePublisher(Player.prototype);

     Player.prototype.on('newplayer', 'addPlayer', game);
     Player.prototype.on('play', 'handlePlay', game);
     game.on('scorechange', scoreboard.update, scoreboard);
     
     window.onkeypress = game.handleKeypress;

     var player1 = new Player('p1-p', 'p'); // .charCodeAt(0));
     var player2 = new Player('p2-q', 'q'); // .charCodeAt(0));
     
})();
