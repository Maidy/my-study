//
//  NotifyingClass.m
//  TextApp
//
//  Created by suguni on 12. 11. 30..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import "NotifyingClass.h"

@implementation NotifyingClass

- (id)init
{
    return self;
}

- (IBAction)displaySomeText:(id)sender
{
    [textView insertText:@"displaySomeText just got called.\n"];
    
    [sender setTitle:@"Clicked"];
    [sender setEnabled:NO];
}
;
@end
