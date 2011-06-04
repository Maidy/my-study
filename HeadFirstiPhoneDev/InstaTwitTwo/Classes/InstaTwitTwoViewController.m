//
//  InstaTwitTwoViewController.m
//  InstaTwitTwo
//
//  Created by suguni on 11. 5. 30..
//  Copyright 2011 다음 커뮤니케이션. All rights reserved.
//

#import "InstaTwitTwoViewController.h"

@implementation InstaTwitTwoViewController

@synthesize tweetPicker, notesField;

- (IBAction) sendButtonTapped:(id)sender {
	NSLog(@"%@, I'm %@ and feeling %@ about it.", notesField.text,
		  [activities objectAtIndex:[tweetPicker selectedRowInComponent:0]],
		  [feelings objectAtIndex:[tweetPicker selectedRowInComponent:1]]);
}

- (IBAction) textFieldDoneEditing:(id)sender {
	[sender resignFirstResponder];
}

- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
	return 2;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
	if (component == 0) {
		return [activities count];
	} else {
		return [feelings count];
	}
}

- (NSString*) pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
	switch (component) {
		case 0:
			return [activities objectAtIndex:row];
			break;
		default:
			return [feelings objectAtIndex:row];
			break;
	}
	return nil;
}

/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
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
	activities = [[NSArray alloc] initWithObjects:@"sleeping", @"eating", nil];
	feelings = [[NSArray alloc] initWithObjects:@"awesome", @"sad", @"happy", nil];
}


/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
	[activities release];
	[feelings release];
	[tweetPicker release];
	[notesField release];
    [super dealloc];
}

@end
