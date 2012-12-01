//
//  WonderfulNumber.h
//  TextApp2
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface WonderfulNumber : NSObject {
    float storedNumber;
}

- (id)initWithNumber:(float)newNumber;

+ (id)wonderfulNumberWithFloat:(float)newNumber;

- (float)storedNumber;
- (void)setStoredNumber:(float)newNumber;
- (NSString *)storedNumberAsString;

@end
