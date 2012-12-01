//
//  NotifyingClass.m
//  TextApp2
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import "NotifyingClass.h"
#import "WonderfulNumber.h"
#import "MathUtilities.h"

@implementation NotifyingClass

- (id)init
{
    return self;
}

- (IBAction)displaySomeText:(id)sender
{
    WonderfulNumber *myWonderfulNumber = [WonderfulNumber wonderfulNumberWithFloat:M_PI];
    
    NSString *stringToOutput = @"The value is ";
    stringToOutput = [stringToOutput stringByAppendingString:[myWonderfulNumber storedNumberAsString]];
    stringToOutput = [stringToOutput stringByAppendingString:@"\n"];
    
    [textView insertText:stringToOutput];
}

- (float)generateValue:(float *)originalValue
{
    float radius = [textField floatValue];
    *originalValue = radius;
    return [MathUtilities circumferenceFromRadius:radius];
}

@end
