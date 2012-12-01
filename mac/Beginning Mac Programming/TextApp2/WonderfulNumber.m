//
//  WonderfulNumber.m
//  TextApp2
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import "WonderfulNumber.h"

@implementation WonderfulNumber

- (id)init
{
    return [self initWithNumber:42.0];
}

- (id)initWithNumber:(float)newNumber
{
    [super init];
    storedNumber = newNumber;
    NSLog(@"A WonderfulNumber object was initialized!");
    return self;
}

- (void)dealloc
{
    NSLog(@"A WonderfulNumber object was deallocated!");
    [super dealloc];
}

+ (id)wonderfulNumberWithFloat:(float)newNumber
{
    id numberToReturn = [[self alloc] initWithNumber:newNumber];
    return [numberToReturn autorelease];
}

- (float)storedNumber
{
    return storedNumber;
}

- (void)setStoredNumber:(float)newNumber
{
    storedNumber = newNumber;
}

- (NSString *)storedNumberAsString
{
    NSString *stringToReturn = [NSString stringWithFormat:@"%f", storedNumber];
    return stringToReturn;
}

@end
