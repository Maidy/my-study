/*
 * Form 유효성 검사 객체
 * JavaScript로 구현한 strategy 패턴에 대한 예제
 */

var validator = {
    checkers : {},
    config : {},
    messages : [],
    hasError : function(){
	return this.messages.length > 0;
    },
    validate : function(data){

	var type, checker;
	this.messages = [];

	for (var o in data) {
	    if (!data.hasOwnProperty(o)) { continue; }

	    type = this.config[o];
	    checker = this.checkers[type];

	    if (!type) { continue; }
	    if (!checker) {
		throw {
		    name: 'ValidatorError',
		    message: 'No handler to validate type ' + type
		};
	    }

	    result = checker.validate(data[o]);
	    if (!result) {
		this.messages.push('Invalid value for *' + o + '*, '
				   + checker.message);
	    }
	}
    }
};


(function(){
    validator.checkers = {
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

    var data = {
	first_name: 'Super',
	last_name: 'Man',
	age: 'unknown',
	username: 'o_O'
    };

    validator.config = {
	first_name: 'isNonEmpty',
	age: 'isNumber',
	username: 'isAlphaNum'
    };

    validator.validate(data);
    if (validator.hasError()) {
	console.log(validator.messages.join('\n'));
    } else {
	console.log('OK!');
    }

})();
