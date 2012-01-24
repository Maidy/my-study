var events = require('events');
var myEmitter = new events.EventEmitter();

// error 이벤트를 emit 했을때 listener가 없으면 stack trace 출력하고, 실행 종료된다.
// myEmitter.on('error', function(err) {
// 	console.log('ERROR: ' + err.message);
// });

// 프로세스에서 발생하는 모든 이벤트를 잡는 방법
process.on('uncaughtException', function(err) {
	console.error('uncaughted exception\nmessage: ', err.message, '\nstack', err.stack);
});

console.log('Error will fire');

myEmitter.emit('error', new Error('Something is wrong'));

console.log('fired');
