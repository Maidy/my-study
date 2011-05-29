//
//  DrinkMixerAppDelegate.h
//  DrinkMixer
//
//  Created by suguni on 11. 5. 28..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DrinkMixerAppDelegate : NSObject <UIApplicationDelegate> {
    
    UIWindow *window;
    UINavigationController *navigationController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UINavigationController *navigationController;

@end

