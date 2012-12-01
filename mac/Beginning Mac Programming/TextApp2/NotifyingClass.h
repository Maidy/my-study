//
//  NotifyingClass.h
//  TextApp2
//
//  Created by suguni on 12. 12. 1..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NotifyingClass : NSObject {
    IBOutlet NSTextView *textView;
    IBOutlet NSTextField *textField;
}

- (IBAction)displaySomeText:(id)sender;
- (float)generateValue:(float *)originalValue;

@end
