//
//  WonderfulNumber.h
//  TextApp
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface WonderfulNumber : NSObject {
    float storedNumber;
}

- (void)setStoredNumber:(float)newNumber;
- (float)storedNumber;

@end
