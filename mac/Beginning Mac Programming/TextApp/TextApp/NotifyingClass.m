//
//  NotifyingClass.m
//  TextApp
//
//  Created by 유 승근 on 12. 2. 22..
//  Copyright 2012년 다음 커뮤니케이션. All rights reserved.
//

#import "NotifyingClass.h"

@implementation NotifyingClass

- (id)init
{
    self = [super init];
    if (self) {
        // Initialization code here.
    }
//    NSLog(@"Hello World! I'm a new NotifyingClass instance!");
    return self;
}

- (IBAction)displaySomeText:(id)sender
{
//    int ourVariable;
//    ourVariable = 'a';
//    ourVariable = 35;
//    ourVariable = 49;
//    ourVariable = 58;
    
//    int anotherIntegerVariable;
//    anotherIntegerVariable = ourVariable + 10;
    
    int anInt = 15;
    float aFloat = -35.2444;
    
    [textView insertText:
     [NSString stringWithFormat:@"address of anInt: %p\n",
      &anInt]];

    [textView insertText:
     [NSString stringWithFormat:@"size of anInt: %i bytes\n",
      sizeof(anInt)]];

    [textView insertText:
     [NSString stringWithFormat:@"address of aFloat: %p\n",
      &aFloat]];
    
    int *pointerToAnInt = &anInt;
    
    [textView insertText:
     [NSString stringWithFormat:@"value of pointerToAnInt: %p\n", pointerToAnInt]];

    [textView insertText:
     [NSString stringWithFormat:@"value of pointerToAnInt address: %i\n", *pointerToAnInt]];

    [textView insertText:
     [NSString stringWithFormat:@"address of textView is: %p\n", textView]];

//    [textView insertText:
//     [NSString stringWithFormat:@"The value of the variable is: %i\n",
//      ourVariable]];

//    [textView insertText:@"displaySomeText just got called!\n"];
//    [sender setTitle:@"Clicked"];
//    [sender setEnabled:NO];
}

@end
