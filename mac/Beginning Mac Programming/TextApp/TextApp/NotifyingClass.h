//
//  NotifyingClass.h
//  TextApp
//
//  Created by suguni on 12. 11. 30..
//  Copyright (c) 2012년 YuiWorld. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NotifyingClass : NSObject {
    
    IBOutlet NSTextView *textView;
    
}

- (IBAction)displaySomeText:(id)sender;

@end
