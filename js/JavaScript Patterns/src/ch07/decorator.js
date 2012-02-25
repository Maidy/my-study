var Sale = function(price) {
    this.price = price;
};

Sale.prototype = {
    getPrice: function() {
	return this.price;
    }
};

Sale.prototype.decorate = function(deco) {
    var F = function() {};
    var overrides = Sale.decorators[deco];
    var i, newobj;

    F.prototype = this;
    newobj = new F();
    newobj.uber = F.prototype;
    for (i in overrides) {
	if (overrides.hasOwnProperty(i)) {
	    newobj[i] = overrides[i];
	}
    }
    return newobj;
};

Sale.decorators = {
    fedtax : {
	getPrice: function() {
	    var price = this.uber.getPrice();
	    price += price * 5 / 100;
	    return price;
	}
    },
    quebec : {
	getPrice: function() {
	    var price = this.uber.getPrice();
	    price += price * 7.5 / 100;
	    return price;
	}
    },
    money : {
	getPrice: function() {
	    return '$' + this.uber.getPrice().toFixed(2);
	}
    },
    cdn : {
	getPrice: function() {
	    return 'CDN$' + this.uber.getPrice().toFixed(2);
	}
    }
};

