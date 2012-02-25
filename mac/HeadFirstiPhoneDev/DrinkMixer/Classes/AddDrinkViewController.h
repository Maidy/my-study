//
//  AddDrinkViewController.h
//  DrinkMixer
//
//  Created by suguni on 11. 6. 4..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DrinkDetailViewController.h"
#import "DrinkConstants.h"

@interface AddDrinkViewController : DrinkDetailViewController {
	BOOL keyboardVisible;
	NSMutableArray* drinks;
}

- (IBAction)save:(id)sender;
- (IBAction)cancel:(id)sender;

- (void)keyboardDidShow:(NSNotification*)notif;
- (void)keyboardDidHide:(NSNotification*)notif;

@property (nonatomic, retain) NSMutableArray* drinks;

@end
