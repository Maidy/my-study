var redis = require('redis'),
	client = redis.createClient(6379, '127.0.0.1');

client.on('error', function(err) {
	console.log('Error ' + err);
});

// key/value pair
client.set('color', 'red', redis.print);
var keyValue = client.get('color', function(err, value) {
	if (err) { throw err; }
	console.log('Get: ' + value);
});

// console.log('============ hash =============');
client.hset('camping', 'shelter', '2-person tent', redis.print);
client.hset('camping', 'cooking', 'campstove', redis.print);
client.hget('camping', 'cooking', function(err, value) {
	console.log('Will be cooking with: ' + value);
});
client.hkeys('camping', function(err, keys) {
	if (err) { throw err; }
	keys.forEach(function(key, i) {
		console.log('   ' + key);
	});
});

// console.log('============ list =============');
/*
client.lpush('tasks', 'Paint the bikeshed red.', redis.print);
client.lpush('tasks', 'Paint the bikeshed green.', redis.print);
client.lpush('tasks', 'Paint the bikeshed blue.', redis.print);
*/
client.lrange('tasks', 0, -1, function(err, items) {
	if (err) { throw err; }
	items.forEach(function(item, i) {
		console.log('   ' + item);
	});
});

// set
client.sadd('ip_address', '204.10.37.96', redis.print);
client.sadd('ip_address', '204.10.37.96', redis.print);
client.sadd('ip_address', '72.32.231.8', redis.print);
client.smembers('ip_address', function(err, members) {
	if (err) { throw err; }
	console.log(members);
});

//publish
setTimeout(function() {

var clientA = redis.createClient(),
	clientB = redis.createClient();

clientA.on('message', function(channel, message) {
	console.log('Client A got message from channel %s: %s', channel, message);
});

clientA.on('subscribe', function(channel, count) {
	console.log('Client A got subscribed from channel %s: %s', channel, count);
	clientB.publish('main_chat_room', 'Hello world!');
	setTimeout(function() {
		clientB.publish('main_chat_room', 'HiHIHIHIH');
	}, 1000);

});

clientA.subscribe('main_chat_room');

}, 0);
