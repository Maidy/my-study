//
//  RootViewController.h
//  DrinkMixerTwo
//
//  Created by suguni on 11. 6. 2..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RootViewController : UITableViewController {
	NSMutableArray* drinks;
	IBOutlet UIBarButtonItem* addButton;
}

@property (nonatomic, retain) NSMutableArray* drinks;
@property (nonatomic, retain) UIBarButtonItem* addButton;

- (IBAction)addButtonPressed:(id)sender;
- (void)applicationDidEnterBackground:(NSNotification*)noti;
	
@end
