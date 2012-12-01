//
//  MathUtilities.m
//  TextApp
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import "MathUtilities.h"

@implementation MathUtilities

+ (float)circumferenceFromRadius:(float)radius
{
    float circumference = 2 * M_PI * radius;
    return circumference;
}

@end
