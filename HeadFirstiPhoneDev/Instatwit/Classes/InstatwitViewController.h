//
//  InstatwitViewController.h
//  Instatwit
//
//  Created by suguni on 11. 5. 22..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface InstatwitViewController : UIViewController
<UIPickerViewDataSource, UIPickerViewDelegate> {
	IBOutlet UIPickerView* tweetPicker;
	NSArray* activities;
	NSArray* feelings;
	
}
@property (nonatomic, retain) UIPickerView* tweetPicker;
- (IBAction) sendButtonTapped:(id)sender;

@end

