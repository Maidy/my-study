//
//  AddDrinkViewController.h
//  DrinkMixerTwo
//
//  Created by suguni on 11. 6. 9..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DrinkDetailViewController.h"

@interface AddDrinkViewController : DrinkDetailViewController {
	BOOL keyboardVisible;
	NSMutableArray* drinkArray;
}

@property (nonatomic, retain) NSMutableArray* drinkArray;

- (IBAction)save:(id)sender;
- (IBAction)cancel:(id)sender;

- (void)keyboardDidShow:(NSNotification*)noti;
- (void)keyboardDidHide:(NSNotification*)noti;
	
@end
