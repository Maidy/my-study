/*
 * JavaScript Patterns
 * -- chapter 7. Design patterns
 * ---- observer pattern sample 1
 */

var publisher = {
    subscribers: {
	any: []
    },

    subscribe: function(fn, type){
	type = type || 'any';
	if (typeof this.subscribers[type] === 'undefined') {
	    this.subscribers[type]=[];
	}
	this.subscribers[type].push(fn);
    },

    unsubscribe: function(fn, type){
	this.visitSubscribers('unsubscribe', fn, type);
    },

    publish: function(publication, type){
	this.visitSubscribers('publish', publication, type);
    },

    visitSubscribers: function(action, arg, type) {
	type = type || 'any';
	var subscriber = this.subscribers[type];

	for (var i=0,l=subscriber.length; i<l; i++) {
	    switch(action) {
	    case 'publish':
		subscriber[i](arg);
		break;
	    case 'unsubscribe':
		if (subscriber[i] === arg) {
		    subscriber.splice(i, 1);
		}
		break;
	    }
	}
    }
};

var makePublisher=function(p){
    for (var o in publisher) {
	if (publisher.hasOwnProperty(o) && typeof publisher[o] === 'function'){
	    p[o] = publisher[o];
	}
    }
    p.subscribers={ any:[] };
};


console.log('==== paper -> joe ====');

var paper={
    daily: function(){
	this.publish('News arrived!');
    },
    monthly: function(){
	this.publish('New magazine arrived!', 'montly');
    }
};

makePublisher(paper);

var joe={
    drinkCoffee: function(paper){
	console.log('Just read ' + paper);
    },
    sundayPreNap: function(monthly){
	console.log('About to fall asleep reading this ' + monthly);
    }
};

paper.subscribe(joe.drinkCoffee);
paper.subscribe(joe.sundayPreNap, 'montly');

paper.daily();
paper.daily();
paper.daily();
paper.monthly();

console.log('==== joe -> paper ====');

// joe - publisher, paper - subscriber 입장 바꾸기
makePublisher(joe);

joe.tweet=function(msg){
    this.publish(msg);
};

paper.readTweet=function(tweet) {
    console.log('Call big meeting! Someone ' + tweet + '.');
};
joe.subscribe(paper.readTweet);

joe.tweet('hated the paper today');
