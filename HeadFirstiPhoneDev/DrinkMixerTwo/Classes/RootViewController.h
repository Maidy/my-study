//
//  RootViewController.h
//  DrinkMixerTwo
//
//  Created by suguni on 11. 6. 2..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RootViewController : UITableViewController {
	NSArray* drinks;
	IBOutlet UIBarButtonItem* addButton;
}

@property (nonatomic, retain) NSArray* drinks;
@property (nonatomic, retain) UIBarButtonItem* addButton;

- (IBAction)addButtonPressed:(id)sender;

@end
