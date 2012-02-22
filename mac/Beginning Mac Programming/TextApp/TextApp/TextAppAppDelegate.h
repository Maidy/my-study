//
//  TextAppAppDelegate.h
//  TextApp
//
//  Created by 유 승근 on 12. 2. 21..
//  Copyright 2012년 다음 커뮤니케이션. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface TextAppAppDelegate : NSObject <NSApplicationDelegate> {
    NSWindow *window;
}

@property (assign) IBOutlet NSWindow *window;

@end
