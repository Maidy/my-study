//
//  iDecideViewController.h
//  iDecide
//
//  Created by suguni on 11. 5. 22..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface iDecideViewController : UIViewController {
	IBOutlet UILabel *decisionText;
}
@property (retain, nonatomic) UILabel *decisionText;

-(IBAction)buttonPressed:(id)sender;

@end

