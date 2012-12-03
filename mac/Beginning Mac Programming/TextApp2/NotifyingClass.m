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
    NSString *firstObject = @"Milk";
    NSString *secondObject = @"Eggs";
    NSString *thirdObject = @"Butter";
    
    NSArray *shoppingListArray = [NSArray
                                  arrayWithObjects:firstObject, secondObject, thirdObject, nil];
    
    NSString *typedValue = [textField stringValue];
    shoppingListArray = [shoppingListArray arrayByAddingObject:typedValue];
    
    NSString *stringToOutput = @"The shopping list is ";
    stringToOutput = [stringToOutput
                      stringByAppendingString:[shoppingListArray
                                               componentsJoinedByString:@", "]];
    stringToOutput = [stringToOutput stringByAppendingString:@"."];
    [textView insertText:stringToOutput];
    
    stringToOutput = @"\n\nThe first item in the list is: ";
    stringToOutput = [stringToOutput
                      stringByAppendingString:[shoppingListArray objectAtIndex:0]];
    [textView insertText:stringToOutput];
    
    int indexOfObject = (int)[shoppingListArray indexOfObject:secondObject];
    stringToOutput = [NSString stringWithFormat:@"\n\nIndex of the second object is: %i", indexOfObject];
    [textView insertText:stringToOutput];
    
    int numberOfItems = (int)[shoppingListArray count];
    stringToOutput = [NSString stringWithFormat:@"\n\nThere are %i items in the shopping list", numberOfItems];
    [textView insertText:stringToOutput];
    
    NSMutableArray *changingArray = [NSMutableArray array];
    [changingArray addObject:@"The first string"];
    [changingArray addObject:@"The second string"];
    [textView insertText:[NSString stringWithFormat:@"\n\n%@", changingArray]];
}

- (float)generateValue:(float *)originalValue
{
    float radius = [textField floatValue];
    *originalValue = radius;
    return [MathUtilities circumferenceFromRadius:radius];
}

@end
