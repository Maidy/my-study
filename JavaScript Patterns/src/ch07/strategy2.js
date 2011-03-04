var Validator = (function() {
    var messages=[]; // error messages

    // default checkers
    var checkers={
	isNonEmpty : {
	    validate: function(data) {
		return data !== '';
	    },
	    message: 'the value cannot be empty'
	},
	isNumber : {
	    validate: function(data) {
		return !isNaN(data);
	    },
	    message: 'the value can only be a valid number, e.g. 1, 3.14 or 2010'
	},
	isAlphaNum : {
	    validate: function(data) {
		return !/[^a-z0-9]/i.test(data);
	    },
	    message: 'the value can only contain characters and numbers, no special symbols'
	}
    };

    var inner=function(chks) {
	// merging checkers, override
	if (typeof chks !== 'undefined') {
	    for (var o in chks) {
		if (chks.hasOwnProperty(o)) {
		    checkers[o] = chks[o];
		}
	    }
	}
    };

    inner.prototype={
	getMessages : function(){
	    return messages;
	},
	hasError : function(){
	    return messages.length > 0;
	},
	validate : function(input){
	    var type, checker;

	    // 흠흠흠...
	    // reset error message
	    messages=[];
	    
	    var data=input.data;
	    var config=input.config;
	    
	    for (var o in data) {
		if (!data.hasOwnProperty(o)) { continue; }
		
		type = config[o];
		checker = checkers[type];
		
		if (!type) { continue; }
		if (!checker) {
		    throw new Error('No handler to validate type ' + type);
		}
		
		result = checker.validate(data[o]);
		if (!result) {
		    messages.push('Invalid value for *' + o + '*, '
				  + checker.message);
		}
	    }
	}
    };
    return inner;
})();

(function(){
    var input1 = {
	'data' : {
	    'first_name': 'Super',
	    'last_name': 'Man',
	    'age' : 'unknown',
	    'username' : 'o_O'
	},
	'config' : {
	    'first_name': 'isNonEmpty',
	    'last_name': 'isNumber',
	    'username': 'isAlphaNum'
	}
    };

    var v1 = new Validator();
    v1.validate(input1);
    if (v1.hasError()) {
	console.log('v1 has error');
	console.log(v1.getMessages().join('\n'));
    } else {
	console.log('OK!');
    }


    var input2 = {
	'data' : {
	    'first_name': '',
	    'email': 'a-a.b.c',
	    'phone': 'abc-123-1111',
	    'sex': 'X',
	    'name' : 'steve'
	},
	'config' : {
	    'first_name': 'isNonEmpty',
	    'email': 'isEmail',
	    'phone': 'isPhoneNum',
	    'sex': 'isSex'
	}
    };
    var checker2 = {
	isPhoneNum : {
	    validate: function(data) {
		return !/\d{3}-\d{4}-\d{4}/i.test(data);
	    },
	    message: 'the value cannot be empty'
	},
	isEmail : {
	    validate: function(data) {
		return !/\w*@\w*.\w*/i.test(data);
	    },
	    message: 'invalid email address'
	},
	isSex : {
	    validate: function(data) {
		return (data === 'M' || data === 'F');
	    },
	    message: 'invalid sex code. It must be M or F'
	}
    };

    var v2 = new Validator(checker2);
    v2.validate(input2);
    if (v2.hasError()) {
	console.log('v2 has error');
	console.log(v2.getMessages().join('\n'));
    } else {
	console.log('OK!');
    }
})();
