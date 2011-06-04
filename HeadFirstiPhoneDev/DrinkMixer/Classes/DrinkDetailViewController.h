//
//  DrinkDetailViewController.h
//  DrinkMixer
//
//  Created by suguni on 11. 5. 29..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DrinkDetailViewController : UIViewController {
	IBOutlet UITextField* nameTextField;
	IBOutlet UITextView* ingredientsTextView;
	IBOutlet UITextView* directionsTextView;
	
	NSDictionary* drink;
}

@property (nonatomic, retain) UITextField* nameTextField;
@property (nonatomic, retain) UITextView* ingredientsTextView;
@property (nonatomic, retain) UITextView* directionsTextView;

@property (nonatomic, retain) NSDictionary* drink;

@end
