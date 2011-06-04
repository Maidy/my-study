//
//  InstaTwitTwoAppDelegate.h
//  InstaTwitTwo
//
//  Created by suguni on 11. 5. 30..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@class InstaTwitTwoViewController;

@interface InstaTwitTwoAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    InstaTwitTwoViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet InstaTwitTwoViewController *viewController;

@end

