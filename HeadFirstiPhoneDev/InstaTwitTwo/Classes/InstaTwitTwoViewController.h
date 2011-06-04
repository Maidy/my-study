//
//  InstaTwitTwoViewController.h
//  InstaTwitTwo
//
//  Created by suguni on 11. 5. 30..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface InstaTwitTwoViewController : UIViewController
	<UIPickerViewDelegate, UIPickerViewDataSource> {

	IBOutlet UIPickerView* tweetPicker;
	IBOutlet UITextField* notesField;
	NSArray* activities;
	NSArray* feelings;
}

- (IBAction) sendButtonTapped:(id)sender;
- (IBAction) textFieldDoneEditing:(id)sender;


@property (nonatomic, retain) UIPickerView* tweetPicker;
@property (nonatomic, retain) UITextField* notesField;

@end

