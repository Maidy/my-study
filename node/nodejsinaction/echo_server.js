var net = require('net');

net.createServer(function(socket) {
  // socket.on('data', function(data) {
  //   socket.write(data);
  // });
  socket.once('data', function(data) {
    socket.write(data);
  });
}).listen(8000);
