//
//  AddDrinkViewController.m
//  DrinkMixer
//
//  Created by suguni on 11. 6. 4..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "AddDrinkViewController.h"
#import "DrinkConstants.h"

@implementation AddDrinkViewController

@synthesize drinks;

- (IBAction)save:(id)sender {
	NSLog(@"save");
	
	if (drink != nil) {
		[self.drinks removeObject:drink];
		self.drink = nil;
	}
	
	NSMutableDictionary* d = [[NSMutableDictionary alloc] init];
	
	[d setObject:nameTextField.text forKey:NAME_KEY];
	[d setObject:ingredientsTextView.text forKey:INGREDIENTS_KEY];
	[d setObject:directionsTextView.text forKey:DIRECTIONS_KEY];
	
	[drinks addObject:d];
	
	NSSortDescriptor* sorter = [[NSSortDescriptor alloc]
								initWithKey:NAME_KEY ascending:YES
								selector:@selector(caseInsensitiveCompare:)];
	
	[drinks sortUsingDescriptors:[NSArray arrayWithObject:sorter]];
	[d release];
	
	[self dismissModalViewControllerAnimated:YES];
}

- (IBAction)cancel:(id)sender {
	NSLog(@"cancel");
	[self dismissModalViewControllerAnimated:YES];
}

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	
	[ingredientsTextView setEditable:YES];
	[directionsTextView setEditable:YES];

	[[NSNotificationCenter defaultCenter]
	 addObserver:self selector:@selector(keyboardDidShow:)
	 name:UIKeyboardDidShowNotification object:nil];
	
	[[NSNotificationCenter defaultCenter]
	 addObserver:self selector:@selector(keyboardDidHide:) 
	 name:UIKeyboardDidHideNotification object:nil];
	
	keyboardVisible = NO;
	
	if (self.drink != nil) {
		[nameTextField setText:[drink objectForKey:NAME_KEY]];
		[ingredientsTextView setText:[drink objectForKey:INGREDIENTS_KEY]];
		[directionsTextView setText:[drink objectForKey:DIRECTIONS_KEY]];		
	}
	
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:animated];
	[[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)keyboardDidShow:(NSNotification*)notif {

	if (keyboardVisible) {
		NSLog(@"Keyboard is already visible. Ignoring notification.");
		return;
	}
	
	NSDictionary* info = [notif userInfo];

//	CGSize keyboardSize = [[info objectForKey:UIKeyboardBoundsUserInfoKey]
//						   CGRectValue].size;

	CGSize keyboardSize = [[info objectForKey:UIKeyboardFrameBeginUserInfoKey]
						   CGRectValue].size;

	CGRect viewFrame = self.view.frame;
	viewFrame.size.height -= keyboardSize.height;
	[scrollView setFrame:viewFrame];
	
// 	[scrollView setContentOffset:CGPointMake(0.0, activeField.frame.origin.y - keyboardSize.height) animated:YES];

	keyboardVisible = YES;
}

- (void)keyboardDidHide:(NSNotification*)notif {

	if (!keyboardVisible) {
		NSLog(@"Keyboard is already hidden. Ignoring notification.");
		return;
	}
	
	NSDictionary* info = [notif userInfo];
	
	CGSize keyboardSize = [[info objectForKey:UIKeyboardFrameBeginUserInfoKey]
						   CGRectValue].size;
	
	CGRect viewFrame = self.view.frame;
	viewFrame.size.height += keyboardSize.height;
	[scrollView setFrame:viewFrame];
	
	keyboardVisible = NO;
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


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	
	self.navigationItem.leftBarButtonItem = [[[UIBarButtonItem alloc]
											  initWithBarButtonSystemItem:UIBarButtonSystemItemCancel
											  target:self action:@selector(cancel:)]
											 autorelease];
	self.navigationItem.rightBarButtonItem = [[[UIBarButtonItem alloc]
											   initWithBarButtonSystemItem:UIBarButtonSystemItemSave
											   target:self action:@selector(save:)]
											  autorelease];
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
	[drinks release];
    [super dealloc];
}


@end
