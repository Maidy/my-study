var tips = require('../lib/tips'),
	should = require('should'),
	tax = 0.12,
	tip = 0.15;

var prices = [10, 20];
var withTipAndTax = tips.addPercentageToEach(prices, tip + tax);
withTipAndTax.should.eql([12.7, 25.4]);

var rounded = tips.sum(withTipAndTax).toFixed(2);
rounded.should.equal('38.10');

tips.dollarFormat(rounded).should.equal('$38.10');
tips.percentFormat(0.15).should.equal('15%');
