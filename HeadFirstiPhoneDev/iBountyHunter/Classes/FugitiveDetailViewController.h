//
//  FugitiveDetailViewController.h
//  iBountyHunter
//
//  Created by suguni on 11. 6. 13..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Fugitive.h"


@interface FugitiveDetailViewController : UIViewController {
	Fugitive* fugitive;
	UILabel* nameLabel;
	UILabel* idLabel;
	UITextView* descTextView;
	UILabel* bountyLabel;
	
	UISegmentedControl *capturedToggle;
	UILabel* capturedDateLabel;
}

@property (nonatomic, retain) Fugitive* fugitive;
@property (nonatomic, retain) IBOutlet UILabel* nameLabel;
@property (nonatomic, retain) IBOutlet UILabel* idLabel;
@property (nonatomic, retain) IBOutlet UITextView* descTextView;
@property (nonatomic, retain) IBOutlet UILabel* bountyLabel;
@property (nonatomic, retain) IBOutlet UILabel* capturedDateLabel;
@property (nonatomic, retain) IBOutlet UISegmentedControl* capturedToggle;

- (IBAction)capturedToggleChanged:(id)sender;

@end
