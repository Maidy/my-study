//
//  InstatwitAppDelegate.h
//  Instatwit
//
//  Created by suguni on 11. 5. 22..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@class InstatwitViewController;

@interface InstatwitAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    InstatwitViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet InstatwitViewController *viewController;

@end

