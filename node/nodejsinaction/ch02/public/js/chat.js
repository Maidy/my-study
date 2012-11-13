var Chat = function(socket) {
    this.socket = socket;
};

Chat.prototype.sendMessage = function(room, text) {
    this.socket.emit('message', {
        room : room,
        text : text
    });
};

Chat.prototype.changeRoom = function(currentRoom, newRoom) {
    this.socket.emit('join', {
        previousRoom : currentRoom,
        newRoom : newRoom
    });
};

Chat.prototype.processCommand = function(currentRoom, command) {
    var words = command.split(' '),
        message = false,
        name = '',
        newRoom = '';

    command = words[0].substring(1, words[0].length).toLowerCase();

    switch (command) {
    case 'join':
        words.shift();
        newRoom = words.join(' ');
        this.changeRoom(currentRoom, newRoom);
        break;
    case 'nick':
        words.shift();
        name = words.join(' ');
        this.socket.emit('nameAttemp', name);
        break;
    default:
        message = 'Unrecognized command.';
    }

    return message;
    

};