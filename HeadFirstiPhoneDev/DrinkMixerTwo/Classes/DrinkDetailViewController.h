//
//  DrinkDetailViewController.h
//  DrinkMixerTwo
//
//  Created by suguni on 11. 6. 2..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface DrinkDetailViewController : UIViewController {

	IBOutlet UITextField* nameTextField;
	IBOutlet UITextView* ingredientTextView;
	IBOutlet UITextView* directionTextView;
}

@property (nonatomic, retain) UITextField* nameTextField;
@property (nonatomic, retain) UITextView* ingredientTextView;
@property (nonatomic, retain) UITextView* directionTextView;

@end
