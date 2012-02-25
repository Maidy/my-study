    //
//  AddDrinkViewController.m
//  DrinkMixerTwo
//
//  Created by suguni on 11. 6. 9..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "AddDrinkViewController.h"
#import "DrinkConstant.h"

@implementation AddDrinkViewController

@synthesize drinkArray;

- (void)keyboardDidShow:(NSNotification*)noti {
	NSLog(@"keyboard show");

	if (keyboardVisible) {
		return;
	}
	
	NSDictionary *info = [noti userInfo];
	NSValue* val = [info objectForKey:UIKeyboardFrameBeginUserInfoKey];
	CGSize kbsize = [val CGRectValue].size;
	
	CGRect viewFrame = self.view.frame;
	viewFrame.size.height -= kbsize.height;
	
	scrollView.frame = viewFrame;
	keyboardVisible = YES;
	
}

- (void)keyboardDidHide:(NSNotification*)noti {
	NSLog(@"keyboard hide");
	
	if (!keyboardVisible) {
		return;
	}

	NSDictionary *info = [noti userInfo];
	NSValue* val = [info objectForKey:UIKeyboardFrameBeginUserInfoKey];
	CGSize kbsize = [val CGRectValue].size;
	
	CGRect viewFrame = self.view.frame;
	viewFrame.size.height -= kbsize.height;
	
	scrollView.frame = viewFrame;
	keyboardVisible = NO;
	
}

- (IBAction)save:(id)sender {
	NSLog(@"Save");
	
	NSMutableDictionary* d = [[NSMutableDictionary alloc] init];
	[d setValue:nameTextField.text forKey:NAME_KEY];
	[d setValue:ingredientTextView.text forKey:INGREDIENTS_KEY];
	[d setValue:directionTextView.text forKey:DIRECTIONS_KEY];
	
	[drinkArray addObject:d];
	
	NSSortDescriptor *sort = [[NSSortDescriptor alloc] initWithKey:NAME_KEY
														 ascending:YES 
														  selector:@selector(caseInsensitiveCompare:)];
	[drinkArray sortUsingDescriptors:[NSArray arrayWithObject:sort]];
	
	[sort release];
	[d release];
	
	[self dismissModalViewControllerAnimated:YES];
}

- (IBAction)cancel:(id)sender {
	NSLog(@"Cancel");
	[self dismissModalViewControllerAnimated:YES];
}


// The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
/*
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization.
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/

- (void)viewWillAppear:(BOOL)animated {
	
	[super viewWillAppear:animated];
	
	[[NSNotificationCenter defaultCenter] addObserver:self
											 selector:@selector(keyboardDidShow:)
												 name:UIKeyboardDidShowNotification
											   object:nil];
	[[NSNotificationCenter defaultCenter] addObserver:self
											 selector:@selector(keyboardDidHide)
												 name:UIKeyboardDidHideNotification
											   object:nil];
	keyboardVisible = NO;
}

- (void)viewWillDisappear:(BOOL)animated {
	[[NSNotificationCenter defaultCenter] removeObserver:self];
}

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	
	self.scrollView.contentSize = self.view.frame.size;
	
	self.navigationItem.leftBarButtonItem = [[[UIBarButtonItem alloc]
											  initWithBarButtonSystemItem:UIBarButtonSystemItemCancel
											  target:self action:@selector(cancel:)] autorelease];
	self.navigationItem.rightBarButtonItem = [[[UIBarButtonItem alloc]
											   initWithBarButtonSystemItem:UIBarButtonSystemItemSave
											   target:self action:@selector(save:)] autorelease];
	self.directionTextView.editable = YES;
	self.ingredientTextView.editable = YES;
}

/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations.
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc. that aren't in use.
}

- (void)viewDidUnload {
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[drinkArray release];
    [super dealloc];
}


@end
