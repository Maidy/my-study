//
//  NotifyingClass.m
//  TextApp
//
//  Created by suguni on 12. 11. 30..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import "NotifyingClass.h"
#import "MathUtilities.h"
#include <math.h>

@implementation NotifyingClass

- (id)init
{
    return self;
}

- (IBAction)displaySomeText:(id)sender
{
    float radius = 0.0;
    float circumference = [self generateValue:&radius];
    
    [textView insertText:[NSString
                          stringWithFormat:@"With the radius of %f, the circumference is: %f\n",
                          radius, circumference]];
}

- (float)generateValue:(float *)originalValue
{
    float radius = [textField floatValue];
    *originalValue = radius;
    return [MathUtilities circumferenceFromRadius:radius];
}

@end
